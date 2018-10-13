package com.sln.model.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.member.InvoiceReadDao;
import com.sln.dao.shop.read.member.MemberSpecialIntegralReadDao;
import com.sln.dao.shop.write.member.InvoiceWriteDao;
import com.sln.entity.member.MemberSpecialIntegral;
import com.sln.entity.order.Invoice;

@Component(value = "memberSpecialIntegralModel")
public class MemberSpecialIntegralModel {
    private static Logger   log = LogManager.getLogger(MemberSpecialIntegralModel.class);

    @Resource
    private MemberSpecialIntegralReadDao memberSpecialIntegralReadDao;

    /**
     * 根据用户Id和商家id获取商家发放的专项积分
     * @param memberId
     * @param sellerId
     * @return
     */
    public List<MemberSpecialIntegral> getMemberSpecialIntegralByMemberId(Integer memberId,
			Integer sellerId){
    	Map<String,Object>queryMap = new HashMap<String,Object>();
    	queryMap.put("q_memberId", memberId);
    	queryMap.put("q_sellerId", sellerId);
    	
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	queryMap.put("q_endTime", sdf.format(date));
    	queryMap.put("q_startTime", sdf.format(date));
    	return memberSpecialIntegralReadDao.page(queryMap, null, null);
    }
    
    /**
     * 根据用户Id获取可用专用积分总和
     * @param memberId
     * @return
     */
    public Integer getValueByMemberId(Integer memberId) {
    	Map<String,Object>queryMap = new HashMap<String,Object>();
    	queryMap.put("q_memberId", memberId);
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	queryMap.put("q_endTime", sdf.format(date));
    	queryMap.put("q_startTime", sdf.format(date));
    	return memberSpecialIntegralReadDao.getValueByMemberId(queryMap);
    }

}
