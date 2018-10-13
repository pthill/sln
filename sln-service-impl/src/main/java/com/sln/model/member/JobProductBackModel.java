package com.sln.model.member;

import com.sln.dao.shop.read.member.JobProductBackReadDao;
import com.sln.dao.shop.write.member.JobProductBackWriteDao;
import com.sln.entity.member.JobProductBack;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class JobProductBackModel {

    private static Logger log = LogManager.getLogger(JobProductBackModel.class);

    @Resource
    private JobProductBackReadDao  jobProductBackReadDao;
    @Resource
    private JobProductBackWriteDao jobProductBackWriteDao;

    public JobProductBack getById(Integer id) {
        return jobProductBackReadDao.getById(id);
    }

    public int getPageCount(Map<String, String> queryMap){
        return jobProductBackReadDao.queryCount(queryMap);
    }

    public List<JobProductBack> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return jobProductBackReadDao.queryPage(queryMap, size, start);
    }

    public Integer save(JobProductBack jobProductBack){
        return jobProductBackWriteDao.insert(jobProductBack);
    }
}
