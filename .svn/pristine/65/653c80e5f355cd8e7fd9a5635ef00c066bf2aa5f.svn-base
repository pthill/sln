package com.sln.dao.shop.write.flash;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.flash.ActFlashSaleStage;

@Repository
public interface ActFlashSaleStageWriteDao {

    ActFlashSaleStage get(java.lang.Integer id);

    Integer insert(ActFlashSaleStage actFlashSaleStage);

    Integer update(ActFlashSaleStage actFlashSaleStage);

    Integer delete(java.lang.Integer id);

    Integer deleteByActFlashSaleId(Integer actFlashSaleId);

    Integer batchInsert(@Param("list") List<ActFlashSaleStage> stageList);
}