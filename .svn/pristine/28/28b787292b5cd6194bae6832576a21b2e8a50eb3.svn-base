package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.ShopActive;

import java.util.List;
import java.util.Map;

public interface IShopActiveService {
    ServiceResult<ShopActive> getShopActiveById(Integer id);


    ServiceResult<Integer> saveShopActive(ShopActive shopActive);


    ServiceResult<Integer> updateShopActive(ShopActive shopActive);


    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<Integer> del(Integer id);

    ServiceResult<List<ShopActive>> page(Map<String, String> queryMap, PagerInfo pager);
}
