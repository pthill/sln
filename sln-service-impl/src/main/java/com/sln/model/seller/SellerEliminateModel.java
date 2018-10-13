package com.sln.model.seller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.dao.shop.read.seller.SellerEliminateReadDao;
import com.sln.dao.shop.write.seller.SellerEliminateWriteDao;
import com.sln.entity.seller.SellerEliminate;

@Component
public class SellerEliminateModel{

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SellerEliminateModel.class);
    
    @Resource
    private SellerEliminateWriteDao sellerEliminateWriteDao;
    @Resource
    private SellerEliminateReadDao SellerEliminateReadDao;
    
    /**
     * 根据id取得淘汰机制表
     * @param  sellerEliminateId
     * @return
     */
    public SellerEliminate getSellerEliminateById(Integer sellerEliminateId) {
    	return sellerEliminateWriteDao.get(sellerEliminateId);
    }
    
    /**
     * 保存淘汰机制表
     * @param  sellerEliminate
     * @return
     */
     public Integer saveSellerEliminate(SellerEliminate sellerEliminate) {
     	this.dbConstrains(sellerEliminate);
     	return sellerEliminateWriteDao.insert(sellerEliminate);
     }
     
     /**
     * 更新淘汰机制表
     * @param  sellerEliminate
     * @return
     */
     public Integer updateSellerEliminate(SellerEliminate sellerEliminate) {
     	this.dbConstrains(sellerEliminate);
     	return sellerEliminateWriteDao.update(sellerEliminate);
     }
     
     private void dbConstrains(SellerEliminate sellerEliminate) {
     }

	public Integer insertSellerEliminate(List<SellerEliminate> sellerEliminatelist) {
		// TODO Auto-generated method stub
		return sellerEliminateWriteDao.insertSellerEliminate(sellerEliminatelist);
	}

	/*
	 * 获取总记录数
	 */
	public Integer getcount() {
		// TODO Auto-generated method stub
		return SellerEliminateReadDao.getcount();
	}

	/*
	 * 批量更新
	 */
	public Integer updateBatchSellerEliminate(List<SellerEliminate> list) {
		// TODO Auto-generated method stub
		return sellerEliminateWriteDao.updateBatch(list);
	}

	//获取淘汰表中四条记录
	public List<SellerEliminate> getSellerEliminateList() {
		// TODO Auto-generated method stub
		return SellerEliminateReadDao.getSellerEliminate();
	}
}