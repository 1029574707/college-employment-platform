package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.mapper.JobRecruitmentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobRecruitmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class JobRecruitmentServiceDiy extends ServiceImpl<JobRecruitmentDao, JobRecruitment> implements MPJobRecruitmentService {

    @Autowired
    private JobRecruitmentDao jobRecruitmentDao;

    @Override
    public void add(JobRecruitment jobRecruitment) {
        jobRecruitmentDao.insert(jobRecruitment);
    }

    @Override
    public void delete(int id) {
        jobRecruitmentDao.deleteById(id);
    }

    @Override
    public void update(JobRecruitment jobRecruitment) {
        jobRecruitmentDao.updateById(jobRecruitment);
    }

    @Override
    public PageResponse<JobRecruitment> list(PageQo pageQo) {
        if (pageQo.getPageSize() == -1) {
            List<JobRecruitment> jobRecruitments = jobRecruitmentDao.selectList(null);
            return new PageResponse<>(jobRecruitments.size(), jobRecruitments);
        }
        Page<JobRecruitment> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<JobRecruitment> jobRecruitmentPage = jobRecruitmentDao.selectPage(page, null);
        return new PageResponse<>(jobRecruitmentPage.getTotal(), jobRecruitmentPage.getRecords());
    }
}
