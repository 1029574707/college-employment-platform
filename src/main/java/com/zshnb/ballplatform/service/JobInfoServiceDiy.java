package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.mapper.JobInfoDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
import com.zshnb.ballplatform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Service
public class JobInfoServiceDiy extends ServiceImpl<JobInfoDao, JobInfo> implements MPJobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    public void add(String studentId, JobInfo jobInfo) {
        jobInfo.setStudentId(studentId);
        jobInfo.setCreateTime(DateUtils.format(new Date()));
        jobInfoDao.insert(jobInfo);
    }

    @Override
    public void delete(int id) {
        jobInfoDao.deleteById(id);
    }

    @Override
    public void update(int id, JobInfo jobInfo) {
        jobInfo.setId(id);
        jobInfo.setUpdateTime(DateUtils.format(new Date()));
        jobInfoDao.updateById(jobInfo);
    }

    @Override
    public PageResponse<JobInfo> list(String studentId, PageQo pageQo) {
        JobInfo jobInfo = new JobInfo();
        jobInfo.setStudentId(studentId);
        Wrapper<JobInfo> eWrapper = new QueryWrapper<>(jobInfo);

        if (pageQo.getPageSize() == -1) {
            List<JobInfo> jobInfos = jobInfoDao.selectList(eWrapper);
            return new PageResponse<>(jobInfos.size(), jobInfos);
        }

        Page<JobInfo> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<JobInfo> jobInfoPage = jobInfoDao.selectPage(page, eWrapper);
        return new PageResponse<>(jobInfoPage.getTotal(), jobInfoPage.getRecords());
    }
}
