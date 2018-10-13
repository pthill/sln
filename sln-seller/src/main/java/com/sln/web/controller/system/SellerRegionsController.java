package com.sln.web.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.StringUtil;
import com.sln.entity.system.Regions;
import com.sln.service.system.IRegionsService;
import com.sln.vo.system.RegionsVO;
import com.sln.web.controller.BaseController;
import com.google.gson.Gson;

/**
 * 地区controller
 * 
 * @Filename: SellerRegionsController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/system/regions")
public class SellerRegionsController extends BaseController {

    @Resource
    private IRegionsService regionsService;

    Logger                  log = Logger.getLogger(this.getClass());

    /**
     * 获取省<br>
     * 查询一次后不再查询数据库，放入ServletContext以减少数据库访问
     * @param request
     * @param response
     * @return 返回json形式的数据
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getProvince", method = { RequestMethod.GET })
    public void getProvince(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        ServletContext sc = request.getSession().getServletContext();

        List<Regions> list = new ArrayList<Regions>();

        Object pros = sc.getAttribute("provinceList");
        if (pros == null) {
            log.debug("没有缓存，查询数据库。。。");
            list = regionsService.getProvince();
            sc.setAttribute("provinceList", list);
        } else {
            log.debug("有缓存，直接返回。。。");
            list = (List<Regions>) pros;
        }

        Gson obj = new Gson();
        String json = obj.toJson(list);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以上级地区id获取其子地区<br>
     * @param request
     * @param response
     * @param p 上级地区id
     * @param type 地区类型
     * @return 返回json形式的数据
     */
    @RequestMapping(value = "getChildrenArea", method = { RequestMethod.GET })
    public void getCity(HttpServletRequest request, HttpServletResponse response, String p,
                        String type) {
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        if (StringUtil.isEmpty(p))
            return;
        List<Regions> list = regionsService.getChildrenArea(Integer.valueOf(p), type);

        Gson obj = new Gson();
        String json = obj.toJson(list);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            pw.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "getAllArea", method = { RequestMethod.GET })
    public String getAllArea(HttpServletRequest request, Map<String, Object> dataMap) {
        List<RegionsVO> allArea = this.regionsService.getAllArea();
        dataMap.put("allArea", allArea);
        return "seller/operate/sellertransport/all_area";
    }
}
