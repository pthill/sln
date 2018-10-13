package com.sln.model.operate;

import com.sln.core.ConstantsEJS;
import com.sln.dao.shop.read.operate.OperationManagerReadDao;
import com.sln.dao.shop.read.operate.ParkReadDao;
import com.sln.dao.shop.write.operate.OperationManagerWriteDao;
import com.sln.dao.shop.write.system.CodeWriteDao;
import com.sln.entity.operate.OperationManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class OperationManagerModel {
    @Resource
    private OperationManagerWriteDao managerWriteDao;
    @Resource
    private OperationManagerReadDao  managerReadDao;
    @Resource
    private ParkReadDao parkReadDao;
    @Resource
    private CodeWriteDao codeWriteDao;


    /**
     * 根据id取得业务管理方
     * @param  id
     * @return
     */
    public OperationManager getById(Integer id){
        return managerReadDao.get(id);
    }

    public OperationManager getByNameAndParkId(Integer parkId,String name){
        return managerReadDao.getByNameAndParkId(parkId,name);
    }

    /**
     * 保存业务管理方表
     * @param  manager
     * @return
     */
    public Integer save(OperationManager manager){
        return managerWriteDao.insert(manager);
    }

    /**
     * 更新业务管理方表
     * @param  manager
     * @return
     */
    public Boolean update(OperationManager manager){
        return managerWriteDao.update(manager)>0;
    }

    /**
     * 更新业务管理方状态
     * @param  manager
     * @return
     */
    public Boolean updateStatus(OperationManager manager){
        return managerWriteDao.updateStatus(manager)>0;
    }

    public List<OperationManager> getManagers(Map<String, String> queryMap, Integer start, Integer size) {
        List<OperationManager> list=managerReadDao.getManagers(queryMap,start,size);
        if(list!=null && list.size()>0){
            for(OperationManager manager:list){
                manager.setParkName(parkReadDao.getParkById(manager.getParkId()).getParkName());
            }
        }
        return list;
    }

    public Integer getManagersCount(Map<String, String> queryMap){
        return managerReadDao.getManagersCount(queryMap);
    }
    /**
     * 检查code是否重复
     * @param  code
     * @return
     */
    public Boolean isCodeExists(String code,Integer id){
        return managerReadDao.isCodeExists(code,id)>0;
    }

    /**
     * 新增和修改时检查园区下的物业管理方是否重复
     * @param
     * @return
     */
    public Boolean isNameExists(String name,Integer parkId,Integer id){
        return managerReadDao.isNameExists(name,parkId.toString(),id)>0;
    }

    public List<OperationManager> getManagersByParkId(Integer parkId){
        List<OperationManager> list=managerReadDao.getManagersByParkId(parkId);
        for(OperationManager manager:list){
            manager.setOperationName(codeWriteDao.getCode(ConstantsEJS.OPERATION_NAME,manager.getName()).getCodeText());
        }
        return list;
    }

}
