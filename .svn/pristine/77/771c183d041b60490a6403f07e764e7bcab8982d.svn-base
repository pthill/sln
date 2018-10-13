package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.QuickEnter;

import java.util.List;
import java.util.Map;

public interface IQuickEnterService {

    ServiceResult<QuickEnter> getQuickEnterById(Integer id);


    ServiceResult<Integer> saveQuickEnter(QuickEnter quickEnter);


    ServiceResult<Integer> updateQuickEnter(QuickEnter quickEnter);


    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<Integer> del(Integer id);

    ServiceResult<List<QuickEnter>> page(Map<String, String> queryMap, PagerInfo pager);
}
