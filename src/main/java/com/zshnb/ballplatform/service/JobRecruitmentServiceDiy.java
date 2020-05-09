package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.enums.EJobRecruitmentType;
import com.zshnb.ballplatform.mapper.JobRecruitmentDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobRecruitmentService;
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

    @Autowired
    private UserStudentDao studentDao;

    @Override
    public void add(JobRecruitment jobRecruitment) {
        jobRecruitment.setCreateTime(DateUtils.format(new Date()));
        jobRecruitmentDao.insert(jobRecruitment);
    }

    @Override
    public void add(JobRecruitment jobRecruitment, String publisherId) {
        jobRecruitment.setCreateTime(DateUtils.format(new Date()));
        jobRecruitment.setPublisherId(publisherId);
        jobRecruitmentDao.insert(jobRecruitment);
    }

    @Override
    public void delete(int id) {
        jobRecruitmentDao.deleteById(id);
    }

    @Override
    public void update(int id, JobRecruitment jobRecruitment) {
        jobRecruitment.setId(id);
        jobRecruitment.setUpdateTime(DateUtils.format(new Date()));
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

    @Override
    public PageResponse<JobRecruitment> listTeacherJob(PageQo pageQo, String teacherId) {
        QueryWrapper<JobRecruitment> eWrapper = new QueryWrapper<>();
        eWrapper.eq("publisherId", teacherId);
        eWrapper.or(ew -> ew.eq("publisherId", "0000"));
        if (pageQo.getPageSize() == -1) {
            List<JobRecruitment> jobRecruitments = jobRecruitmentDao.selectList(eWrapper);
            jobRecruitments.forEach(jobRecruitment -> jobRecruitment.setTypeName(EJobRecruitmentType.getDescByCode(jobRecruitment.getType())));
            return new PageResponse<>(jobRecruitments.size(), jobRecruitments);
        }
        Page<JobRecruitment> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<JobRecruitment> jobRecruitmentPage = jobRecruitmentDao.selectPage(page, eWrapper);
        List<JobRecruitment> records = jobRecruitmentPage.getRecords();
        records.forEach(jobRecruitment -> jobRecruitment.setTypeName(EJobRecruitmentType.getDescByCode(jobRecruitment.getType())));
        return new PageResponse<>(jobRecruitmentPage.getTotal(), records);
    }

    @Override
    public PageResponse<JobRecruitment> listStudentJob(PageQo pageQo, String studentId) {
        UserStudent student = studentDao.selectById(studentId);
        return listTeacherJob(pageQo, student.getTeacherId());
    }
}
