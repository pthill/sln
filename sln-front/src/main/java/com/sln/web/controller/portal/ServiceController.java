package com.sln.web.controller.portal;

import com.sln.service.portal.IPortalServiceService;
import com.sln.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/portal/service")
public class ServiceController extends BaseController {
    @Resource
    private IPortalServiceService portalServiceService;


    //商旅服务
    @RequestMapping(value = "/business.html",method = { RequestMethod.GET})
    public String business(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/business-service";
    }

    //会议服务
    @RequestMapping(value = "/conference.html",method = {RequestMethod.GET})
    public String conference(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/conference-service";
    }

    //健康服务
    @RequestMapping(value = "/health.html",method = {RequestMethod.GET})
    public String health(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/health-service";
    }

    //物业服务
    @RequestMapping(value = "/property.html",method = {RequestMethod.GET})
    public String property(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/property-services";
    }

    //交通出行
    @RequestMapping(value = "/traffic.html",method = {RequestMethod.GET})
    public String traffic(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/traffic-travel";
    }

    //餐饮服务
    @RequestMapping(value = "/restaurant.html",method = {RequestMethod.GET})
    public String restaurant(HttpServletRequest request, Map<String,Object>dataMap){
        this.head(0,dataMap,request);
        return "front/portal/restaurant-services";
    }

}
