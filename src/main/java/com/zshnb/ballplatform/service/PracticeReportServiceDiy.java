package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.mapper.PracticeReportDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticeReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.utils.DateUtils;
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
public class PracticeReportServiceDiy extends ServiceImpl<PracticeReportDao, PracticeReport> implements MPPracticeReportService {

    @Autowired
    private PracticeReportDao practiceReportDao;

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private UserStudentDao studentDao;


    @Override
    public void add(String studentId, PracticeReport report) {
        report.setStudentId(studentId);
        report.setCreateTime(DateUtils.format(new Date()));
        practiceReportDao.insert(report);
    }

    @Override
    public void delete(int id) {
        practiceReportDao.deleteById(id);
    }

    @Override
    public void update(int id, String reportContent) {
        PracticeReport report = practiceReportDao.selectById(id);
        report.setUpdateTime(DateUtils.format(new Date()));
        report.setContent(reportContent);
        practiceReportDao.updateById(report);
    }

    @Override
    public PageResponse<PracticeReport> list(PageQo pageQo, String studentId) {
        QueryWrapper<PracticeReport> wrapper = new QueryWrapper<>();
        wrapper.eq("studentId", studentId);

        if (pageQo.getPageSize() == -1) {
            List<PracticeReport> list = practiceReportDao.selectList(wrapper);
            for (PracticeReport report : list) {
                report.setCompanyName(practiceInfoDao.selectById(report.getPracticeId()).getCompany());
                if (report.getEvaluationId() != null) {
                    report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
                }
            }
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeReport> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeReport> list = practiceReportDao.selectPage(page, wrapper);
        List<PracticeReport> records = list.getRecords();
        for (PracticeReport report : records) {
            report.setCompanyName(practiceInfoDao.selectById(report.getPracticeId()).getCompany());
            if (report.getEvaluationId() != null) {
                report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }

    @Override
    public PageResponse<PracticeReport> teacherList(PageQo pageQo, String teacherId) {

        QueryWrapper<UserStudent> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("teacherId", teacherId);
        List<UserStudent> userStudents = studentDao.selectList(studentQueryWrapper);
        List<String> studentIdList = userStudents.stream().map(UserStudent::getId).collect(Collectors.toList());

        QueryWrapper<PracticeReport> wrapper = new QueryWrapper<>();
        wrapper.in("studentId", studentIdList);

        if (pageQo.getPageSize() == -1) {
            List<PracticeReport> list = practiceReportDao.selectList(wrapper);
            for (PracticeReport report : list) {
                report.setCompanyName(practiceInfoDao.selectById(report.getPracticeId()).getCompany());
                if (report.getEvaluationId() != null) {
                    report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
                }
            }
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeReport> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeReport> list = practiceReportDao.selectPage(page, wrapper);
        List<PracticeReport> records = list.getRecords();
        for (PracticeReport report : records) {
            report.setCompanyName(practiceInfoDao.selectById(report.getPracticeId()).getCompany());
            if (report.getEvaluationId() != null) {
                report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);

    }
}
