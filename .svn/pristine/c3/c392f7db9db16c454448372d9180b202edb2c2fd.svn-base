package com.sln.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sln.core.HttpClientUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.operate.Park;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.portal.PortalService;
import com.sln.entity.portal.RecommendService;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalMenuService;
import com.sln.service.portal.IPortalServiceService;
import com.sln.service.portal.IRecommendServiceService;
import com.sln.util.RedisClient;
import com.sln.web.util.WebFrontSession;

/**
 * 控制层基类， 所有controller类都需要继承此类        
 */
public class BaseController {
    @Resource
    private IRecommendServiceService recommendServiceService;
    @Resource
    private IPortalMenuService       portalMenuService;
    @Resource
    private IPortalServiceService    portalServiceService;
    @Resource
    private IParkService             parkService;
    @Resource
    private RedisClient              redisClient;
    protected static Integer stParkId=0;
    //百度地图获取城市信息的url
    private static String mapUrl="http://api.map.baidu.com/location/ip?ak=GwWNvPoShr9gEqEh0NAiCwFxRfBMSsZq&ip=";
    protected static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                     .getLogger(BaseController.class);

    protected boolean isNull(Object... objs) {
        boolean flag = false;
        for (Object obj : objs) {
            if (obj == null || "".equals(obj)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //date,datetime
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtil.isEmpty(value)) {
                    setValue(null);
                    return;
                }
                try {
                    if (value.length() == 10) {
                        setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                    } else if (value.length() == 8) {
                        setValue(new SimpleDateFormat("HH:mm:ss").parse(value));
                    } else if (value.length() == 16) {
                        setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value));
                    } else {
                        setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
                    }

                } catch (ParseException e) {
                    log.error("Can not convert '" + value + "' to java.util.Date", e);
                }
            }

            public String getAsText() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
            }

        });
        //int
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtil.isEmpty(value)) {
                    setValue(0);
                    return;
                }
                setValue(Integer.parseInt(value));
            }

            public String getAsText() {
                return getValue().toString();
            }

        });

        //long
        binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtil.isEmpty(value)) {
                    setValue(0);
                    return;
                }
                setValue(Long.parseLong(value));
            }

            public String getAsText() {
                return getValue().toString();
            }

        });

        //double
        binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (StringUtil.isEmpty(value)) {
                    setValue(0);
                    return;
                }
                setValue(Double.parseDouble(value));
            }

            public String getAsText() {
                return getValue().toString();
            }

        });
    }

    public ModelAndView redirectHandlerFor301(String sURL) {
        RedirectView rv = new RedirectView(sURL);
        rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        rv.setUrl(sURL);
        ModelAndView mv = new ModelAndView(rv);
        return mv;
    }

    /**
     * 调用此方法说明你的controller方法将要转到显示系统消息页面
     * @param stack
     * @param message
     * @param links
     */
    public void showMessage(Map<String, Object> stack, String message,
                            List<Map<String, String>> links) {
        if (links == null || links.size() == 0) {
            links = new ArrayList<Map<String, String>>();
            Map<String, String> link = new HashMap<String, String>();
            link.put("text", "返回上一页");
            link.put("url", "javascript:history.back()");
            links.add(link);
        }
        stack.put("messageInfo", message);
        stack.put("links", links);
        stack.put("msgType", "message");
    }


    public Integer getParkId(HttpServletRequest request){
        String ip = WebUtil.getIpAddr(request);
        ServiceResult<List<Park>> parks=parkService.getParkList();
        Integer parkId=0;
        if(parks.getSuccess()&&parks.getResult()!=null&&parks.getResult().size()>0){
            parkId=parks.getResult().get(0).getId();
        }else{
            throw new BusinessException("当前没有园区,请添加园区");
        }
        try {
            String result = HttpClientUtil.sendGet(mapUrl+ip);
            if(result==null || StringUtils.isEmpty(result)){
                log.warn("无法获取定位信息");
                return parkId;
            }
            JSONObject jo = JSONObject.fromObject(result);
            if(jo==null){
                log.warn("转换成json类型失败");
                return parkId;
            }
            JSONObject content = jo.getJSONObject("content");
            if(content==null || content.isNullObject()){
                log.warn("百度地图定位失败");
                return parkId;
            }
            //log.info("百度地图的详细信息为"+content.toString());
            String address  = content.getString("address");
            if(address==null || StringUtils.isEmpty(address)){
                log.warn("百度地图获取所在城市信息失败");
                return parkId;
            }
            //log.info("地址为"+address);
            if(parks.getResult()!=null||parks.getResult().size()>0){
                for(Park park:parks.getResult()){//如果没有匹配到数据库的园区则返回第一条数据
                    if(address.indexOf(park.getProvince())!=-1){
                        if(address.indexOf(park.getCity())!=-1){
                            parkId= park.getId();
                            break;
                        }else{
                            continue;
                        }
                    }else{
                        continue;
                    }
                }
            }
        }catch (BusinessException be){
            be.printStackTrace();
            log.warn("园区列表获取失败"+parks.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            log.warn("获取定位信息失败"+e.getMessage());
        }
        return  parkId;
    }

    //redis缓存门户头部信息
    public void head(Integer parkId,Map<String,Object>dataMap,HttpServletRequest request){
        if (parkId == null || parkId == 0) {
            String portalParkId = redisClient.getString("portalParkId"+stParkId);
            if (portalParkId == null || portalParkId.equals("0")) {
                parkId = this.getParkId(request);
                stParkId=parkId;
                redisClient.set("portalParkId"+parkId, parkId, 60);
                //log.info("当前园区id" + parkId);
            }else{
                parkId=Integer.parseInt(portalParkId);
            }
        }else{
           ServiceResult<Park> parkServiceResult=parkService.getParkById(parkId);
           if(!parkServiceResult.getSuccess()||parkServiceResult.getResult()==null){
                parkId=stParkId;
           }else{
               stParkId=parkId;
               redisClient.set("portalParkId"+parkId,parkId,60);
           }
        }
        Member member = WebFrontSession.getLoginedUser(request);
        dataMap.put("user",member);
        try {
            List<PortalMenu> menuList = redisClient.getBeanList("portalMenus"+parkId,PortalMenu.class);
            ServiceResult<List<PortalMenu>> menusResult=portalMenuService.getMenusByParkId(parkId);
            if(menuList == null || menuList.size() == 0){
            /*获取当前园区的菜单集合*/
                redisClient.set("portalMenus"+parkId,menusResult.getResult(),60);
                dataMap.put("menus",menusResult.getResult());
            }else{
                dataMap.put("menus",menuList);
            }
            /*获取菜单下的服务类和服务项*/
            Map<String,Object> map=redisClient.getBean("portalMap"+parkId,Map.class);
            if(map==null||map.size()==0){
                ServiceResult<List<PortalService>> services;
                Map<String,Object> objectMap=new HashMap<>();
                for(PortalMenu p:menusResult.getResult()){
                    List<PortalService> portalServices=(List<PortalService>) objectMap.get(p.getId().toString());
                    if(portalServices==null||portalServices.size()==0){
                        services=portalServiceService.getServicesByMenuId(p.getId());
                        objectMap.put(p.getId().toString(),services.getResult());
                    }else{
                    }
                }
                redisClient.set("portalMap"+parkId,objectMap,60);
                dataMap.put("map",objectMap);
            }else {
                dataMap.put("map",map);
            }
            //门户搜索的下拉框
            List<PortalService>pid=redisClient.getBeanList("portalSor"+parkId,PortalService.class);
            if(pid==null||pid.size()==0){
                ServiceResult<List<PortalService>> pidResult=portalServiceService.ListService(parkId);
                redisClient.set("portalSor"+parkId,pidResult.getResult(),60);
                dataMap.put("pid",pidResult.getResult());
            }else {
                dataMap.put("pid",pid);
            }
            //门户的推荐服务
            List<RecommendService>recommendList=redisClient.getBeanList("portalRecommendList"+parkId,RecommendService.class);
            if(recommendList==null||recommendList.size()==0){
                ServiceResult<List<RecommendService>> result=recommendServiceService.getByParkId(parkId);
                redisClient.set("portalRecommendList"+parkId,result.getResult(),60);
                dataMap.put("recommendList",result.getResult());
            }else{
                dataMap.put("recommendList",recommendList);
            }
            Park park=redisClient.getBean("portalPark"+parkId,Park.class);
            if(park==null){
                ServiceResult<Park> parkServiceResult=parkService.getParkById(parkId);
                redisClient.set("portalPark"+parkId,parkServiceResult.getResult(),60);
                dataMap.put("park",parkServiceResult.getResult());
            }else {
                dataMap.put("park",park);
            }
        }catch (BusinessException be){
            be.printStackTrace();
            log.error(be.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }

    }

}
