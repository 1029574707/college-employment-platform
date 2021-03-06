package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.mapper.JobInfoDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
import com.zshnb.ballplatform.utils.DateUtils;
import com.zshnb.ballplatform.vo.JobInfoStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private UserStudentDao studentDao;

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

    @Override
    public List<String> listStudentId(int tripartite) {
        QueryWrapper<JobInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tripartite", tripartite);
        List<JobInfo> jobInfos = jobInfoDao.selectList(queryWrapper);
        return jobInfos.stream().map(JobInfo::getStudentId).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> listAllStudentId() {
        List<JobInfo> jobInfos = jobInfoDao.selectList(null);
        return jobInfos.stream().map(JobInfo::getStudentId).distinct().collect(Collectors.toList());
    }

    @Override
    public JobInfoStatistics statistics(Integer collegeId) {
        JobInfoStatistics statistics = new JobInfoStatistics();
        QueryWrapper<UserStudent> studentWrapper = new QueryWrapper<>();
        if (collegeId != null) {
            studentWrapper.eq("collegeId", collegeId);
        }
        List<UserStudent> userStudents = studentDao.selectList(studentWrapper);
        if (userStudents.size() == 0) {
            return statistics;
        }
        List<String> studentIds = userStudents.stream().map(UserStudent::getId).distinct().collect(Collectors.toList());

        QueryWrapper<JobInfo> jobWrapper = new QueryWrapper<>();
        jobWrapper.in("studentId", studentIds);

        List<JobInfo> jobInfos = jobInfoDao.selectList(jobWrapper);
        statistics.setHasJobCount((int) jobInfos.stream().map(JobInfo::getStudentId).distinct().count());
        statistics.setNoJobCount(studentIds.size() - statistics.getHasJobCount());
        return statistics;
    }

    @Override
    public JobInfoStatistics statistics(String teacherId, Integer classId) {
        JobInfoStatistics jobInfoStatistics = new JobInfoStatistics();
        QueryWrapper<UserStudent> studentWrapper = new QueryWrapper<>();
        studentWrapper.eq("teacherId", teacherId);
        if (classId != null) {
            studentWrapper.eq("classId", classId);
        }
        List<UserStudent> userStudents = studentDao.selectList(studentWrapper);
        if (userStudents.size() == 0) {
            return jobInfoStatistics;
        }
        List<String> studentIds = userStudents.stream().map(UserStudent::getId).distinct().collect(Collectors.toList());
        QueryWrapper<JobInfo> jobInfoQueryWrapper = new QueryWrapper<>();
        jobInfoQueryWrapper.in("studentId", studentIds);
        List<String> jobStudentIds = jobInfoDao.selectList(jobInfoQueryWrapper).stream().map(JobInfo::getStudentId).distinct().collect(Collectors.toList());
        jobInfoStatistics.setHasJobCount(jobStudentIds.size());
        jobInfoStatistics.setNoJobCount(userStudents.size() - jobInfoStatistics.getHasJobCount());
        return jobInfoStatistics;
    }
}
