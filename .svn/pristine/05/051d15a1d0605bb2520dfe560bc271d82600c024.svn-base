package com.sln.service.pcindex;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.pcindex.PcIndexImage;

public interface IPcIndexImageService {

    /**
     * 根据id取得PC端首页的一些图片
     * @param  pcIndexImageId
     * @return
     */
    ServiceResult<PcIndexImage> getPcIndexImageById(Integer pcIndexImageId);

    /**
     * 保存PC端首页的一些图片
     * @param  pcIndexImage
     * @return
     */
    ServiceResult<Boolean> savePcIndexImage(PcIndexImage pcIndexImage);

    /**
     * 更新PC端首页的一些图片
     * @param pcIndexImage
     * @return
     */
    ServiceResult<Boolean> updatePcIndexImage(PcIndexImage pcIndexImage);

    /**
     * 删除PC端首页的一些图片
     * @param  pcIndexImage
     * @return
     */
    ServiceResult<Boolean> deletePcIndexImage(Integer pcIndexImageId);

    /**
     * 根据条件取得PC端首页的一些图片
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexImage>> getPcIndexImages(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 取得PC端首页的一些图片（当前时间在展示时间范围内的轮播图）<br>
     * <li>如果isPreview=true取使用和预使用状态的轮播图
     * <li>如果isPreview=false取使用状态的轮播图
     * 
     * @param isPreview
     * @param type
     * @return
     */
    ServiceResult<List<PcIndexImage>> getPcIndexImageForView(Boolean isPreview, int type);

}