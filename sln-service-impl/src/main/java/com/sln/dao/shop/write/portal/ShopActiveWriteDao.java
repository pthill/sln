package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.ShopActive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopActiveWriteDao {

    Integer insert(ShopActive shopActive);

    Integer update(ShopActive shopActive);

    Integer updateState(@Param("id") Integer id,@Param("type") String type);

    Integer del(Integer id);

}
