package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.*;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.enums.EPracticeType;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.mapper.PracticeReportDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.PracticeInfoQo;
import com.zshnb.ballplatform.service.inter.MPPracticeReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                PracticeInfo practiceInfo = practiceInfoDao.selectById(report.getPracticeId());
                report.setCompanyName(practiceInfo.getCompany());
                report.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
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
            PracticeInfo practiceInfo = practiceInfoDao.selectById(report.getPracticeId());
            report.setCompanyName(practiceInfo.getCompany());
            report.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
            if (report.getEvaluationId() != null) {
                report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }

    @Override
    public PageResponse<PracticeReport> teacherList(PracticeInfoQo pageQo, String teacherId) {

        // 学生姓名+教师id搜索学生列表先
        QueryWrapper<UserStudent> studentQueryWrapper = new QueryWrapper<>();
        if (pageQo.getStudentName() != null && !"".equals(pageQo.getStudentName())) {
            studentQueryWrapper.like("name", pageQo.getStudentName());
        }

        studentQueryWrapper.eq("teacherId", teacherId);
        List<UserStudent> userStudents = studentDao.selectList(studentQueryWrapper);
        List<String> studentIdList = userStudents.stream().map(UserStudent::getId).collect(Collectors.toList());
        if (studentIdList.size() == 0) {
            return new PageResponse<>(0, new ArrayList<>());
        }

        QueryWrapper<PracticeReport> wrapper = new QueryWrapper<>();
        wrapper.in("studentId", studentIdList);

        // type
        if (pageQo.getType() != null) {
            QueryWrapper<PracticeInfo> infoQueryWrapper = new QueryWrapper<>();
            infoQueryWrapper.eq("type", pageQo.getType());
            List<PracticeInfo> practiceInfos = practiceInfoDao.selectList(infoQueryWrapper);
            List<Integer> infoIds = practiceInfos.stream().map(PracticeInfo::getId).distinct().collect(Collectors.toList());
            if (infoIds.size() == 0) {
                return new PageResponse<>(0, new ArrayList<>());
            }
            wrapper.in("practiceId", infoIds);
        }

        if (pageQo.getPageSize() == -1) {
            List<PracticeReport> list = practiceReportDao.selectList(wrapper);
            for (PracticeReport report : list) {
                PracticeInfo practiceInfo = practiceInfoDao.selectById(report.getPracticeId());
                report.setCompanyName(practiceInfo.getCompany());
                report.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
                UserStudent studentForReport = userStudents.stream().filter(student -> student.getId().equals(report.getStudentId())).collect(Collectors.toList()).get(0);
                report.setStudentName(studentForReport.getName());
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
            PracticeInfo practiceInfo = practiceInfoDao.selectById(report.getPracticeId());
            report.setCompanyName(practiceInfo.getCompany());
            report.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
            UserStudent studentForReport = userStudents.stream().filter(student -> student.getId().equals(report.getStudentId())).collect(Collectors.toList()).get(0);
            report.setStudentName(studentForReport.getName());
            if (report.getEvaluationId() != null) {
                report.setEvaluation(evaluationDao.selectById(report.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);

    }
}
