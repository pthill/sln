package com.sln.model.compain;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.StringUtil;
import com.sln.dao.shop.read.compain.ComplainRegisterReadDao;
import com.sln.dao.shop.write.compain.ComplainRegisterWriteDao;
import com.sln.entity.compain.ComplainRegister;


@Component
public class ComplainRegisterModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ComplainRegisterModel.class);
    
    @Resource
    private ComplainRegisterWriteDao complainRegisterWriteDao;
    @Resource
    private ComplainRegisterReadDao complainRegisterReadDao;
    
    /**
     * 根据id取得投诉登记表
     * @param  complainRegisterId
     * @return
     */
    public ComplainRegister getComplainRegisterById(Integer complainRegisterId) {
    	return complainRegisterWriteDao.get(complainRegisterId);
    }
    
    /**
     * 保存投诉登记表
     * @param  complainRegister
     * @return
     */
     public Integer saveComplainRegister(ComplainRegister complainRegister) {
     	this.dbConstrains(complainRegister);
     	return complainRegisterWriteDao.insert(complainRegister);
     }
     
     /**
     * 更新投诉登记表
     * @param  complainRegister
     * @return
     */
     public Integer updateComplainRegister(ComplainRegister complainRegister) {
     	this.dbConstrains(complainRegister);
     	return complainRegisterWriteDao.update(complainRegister);
     }
     
     private void dbConstrains(ComplainRegister complainRegister) {
		complainRegister.setComplainSeller(StringUtil.dbSafeString(complainRegister.getComplainSeller(), true, 30));
		complainRegister.setComplainType(StringUtil.dbSafeString(complainRegister.getComplainType(), true, 20));
		complainRegister.setComplainPerson(StringUtil.dbSafeString(complainRegister.getComplainPerson(), true, 20));
		complainRegister.setCreatePerson(StringUtil.dbSafeString(complainRegister.getCreatePerson(), true, 20));
		complainRegister.setDesceinfo(StringUtil.dbSafeString(complainRegister.getDesceinfo(), false, 300));
     }

     //获取总计录
	public int getSellersCount(Map<String, String> queryMap) {
		return complainRegisterReadDao.getSellersCount(queryMap);
	}

	//获取列表
	public List<ComplainRegister> getSellEliminate(Map<String, String> queryMap,
			Integer start, Integer size) {
		// TODO Auto-generated method stub
		return complainRegisterReadDao.getSellEliminate(queryMap,start,size);
	}

	//删除操作
	public Boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return complainRegisterWriteDao.deleteById(id);
	}
}