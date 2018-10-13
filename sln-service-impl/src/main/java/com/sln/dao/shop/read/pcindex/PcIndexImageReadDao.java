package com.sln.dao.shop.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.pcindex.PcIndexImage;

@Repository
public interface PcIndexImageReadDao {

    PcIndexImage get(java.lang.Integer id);

    Integer getPcIndexImagesCount(@Param("queryMap") Map<String, String> queryMap);

    List<PcIndexImage> getPcIndexImages(@Param("queryMap") Map<String, String> queryMap,
                                        @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 为展示页面取数据，数据order by `order_no`，默认取当前时间在展示时间之内的数据
     * 
     * @param isPreview 0:不是预览，取status为1的数据，1：预览，取status小于3的数据
     * @param type 分类
     * @return
     */
    List<PcIndexImage> getPcIndexImagesForView(@Param("isPreview") Integer isPreview,
                                               @Param("type") Integer type);
}