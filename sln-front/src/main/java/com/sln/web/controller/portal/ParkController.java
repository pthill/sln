package com.sln.web.controller.portal;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.Park;
import com.sln.entity.portal.ParkAdvantage;
import com.sln.entity.portal.PortalActive;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IParkAdvantageService;
import com.sln.service.portal.IPortalActiveService;
import com.sln.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/park")
public class ParkController extends BaseController {
    @Resource
    private IPortalActiveService portalActiveService;
    @Resource
    private IParkAdvantageService parkAdvantageService;
    @Resource
    private IParkService parkService;

    //园区切换
    @RequestMapping(value = "/parkMap.html",method = {RequestMethod.GET})
    public String parkMap(Map<String,Object> dataMap, HttpServletRequest request){
        String parkId=request.getParameter("parkId");
        if(parkId==null){
            this.head(null,dataMap,request);
        }else{
            this.head(Integer.parseInt(parkId),dataMap,request);
        }
        ServiceResult<List<String>> list=parkService.getArea();
        Map<String,List<Park>>map=new HashMap<String, List<Park>>();
        for(String s:list.getResult()){
            ServiceResult<List<Park>> parks=parkService.getParkByArea(s);
            if(parks.getSuccess()){
                map.put(s,parks.getResult());
            }else{
                throw new BusinessException(parks.getMessage());
            }
        }
        ServiceResult<List<Park>> parkList=parkService.getParkList();
        dataMap.put("parkList",parkList.getResult());
        dataMap.put("AreaMap",map);
        return "front/portal/mappage";
    }
    //园区活动列表
    @RequestMapping(value = "/activeList.html",method = { RequestMethod.GET})
    public String activeList(Map<String,Object>dataMap,HttpServletRequest request){
        this.head(0,dataMap,request);
        Integer parkId=super.stParkId;
        PagerInfo page = WebUtil.handlerPagerInfo(request, dataMap, 8);
        Map<String,String> map=new HashMap<>();
        map.put("q_status","1");
        map.put("parkId",parkId.toString());
        ServiceResult<List<PortalActive>> actives=portalActiveService.page(map,page);
        if(!actives.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(actives.getCode())) {
                throw new RuntimeException(actives.getMessage());
            } else {
                throw new BusinessException(actives.getMessage());
            }
        }
        ServiceResult<Park> park=parkService.getParkById(parkId);
        if(!park.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(park.getCode())) {
                throw new RuntimeException(park.getMessage());
            } else {
                throw new BusinessException(park.getMessage());
            }
        }
        dataMap.put("page",page);
        dataMap.put("activeList",actives.getResult());
        return "front/portal/parkActive";
    }
    //园区活动详情
    @RequestMapping(value = "/activeDetail/{activeId}.html",method = {RequestMethod.GET})
    public String activeDetail(HttpServletRequest request,Map<String,Object>dataMap,@PathVariable Integer activeId){
        this.head(0,dataMap,request);
        Integer parkId=super.stParkId;
        ServiceResult<PortalActive>active=portalActiveService.getPortalActiveById(activeId);
        if(!active.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(active.getCode())) {
                throw new RuntimeException(active.getMessage());
            } else {
                throw new BusinessException(active.getMessage());
            }
        }
        dataMap.put("parkId",parkId);
        dataMap.put("active",active.getResult());
        return "front/portal/details-actives";
    }
    //园区优势
    @RequestMapping(value = "/advantage.html",method = {RequestMethod.GET})
    public String advantage(Map<String,Object>dataMap,HttpServletRequest request){
        this.head(0,dataMap,request);
        Integer parkId=super.stParkId;
        ServiceResult<List<ParkAdvantage>>advantage=parkAdvantageService.getByParkId(parkId);
        if(!advantage.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(advantage.getCode())) {
                throw new RuntimeException(advantage.getMessage());
            } else {
                throw new BusinessException(advantage.getMessage());
            }
        }
        dataMap.put("advantage",advantage.getResult());
        return "front/portal/advantage";
    }

}
