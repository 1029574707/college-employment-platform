package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.*;
import com.zshnb.ballplatform.enums.EPracticeStatus;
import com.zshnb.ballplatform.enums.EPracticeType;
import com.zshnb.ballplatform.mapper.*;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticeInfoService;
import com.zshnb.ballplatform.utils.DateUtils;
import com.zshnb.ballplatform.vo.PracticeInfoStatistics;
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
public class PracticeInfoServiceDiy extends ServiceImpl<PracticeInfoDao, PracticeInfo> implements MPPracticeInfoService {

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private PracticePlanDao planDao;

    @Autowired
    private PracticeDiaryDao diaryDao;

    @Autowired
    private PracticeReportDao reportDao;

    @Autowired
    private UserStudentDao studentDao;

    @Override
    public void score(int id, int fraction) {
        PracticeInfo practiceInfo = practiceInfoDao.selectById(id);
        practiceInfo.setFraction(fraction);
        practiceInfoDao.updateById(practiceInfo);
    }

    @Override
    public void add(String studentId, PracticeInfo info) {
        info.setStudentId(studentId);
        info.setCreateTime(DateUtils.format(new Date()));
        practiceInfoDao.insert(info);
    }

    @Override
    public void delete(int id) {
        QueryWrapper<PracticePlan> planWrapper = new QueryWrapper<>();
        planWrapper.eq("practiceId", id);
        planDao.delete(planWrapper);

        QueryWrapper<PracticeDiary> diaryWrapper = new QueryWrapper<>();
        diaryWrapper.eq("practiceId", id);
        diaryDao.delete(diaryWrapper);

        QueryWrapper<PracticeReport> reportWrapper = new QueryWrapper<>();
        reportWrapper.eq("practiceId", id);
        reportDao.delete(reportWrapper);

        practiceInfoDao.deleteById(id);
    }

    @Override
    public void update(int id, PracticeInfo info) {
        info.setId(id);
        info.setUpdateTime(DateUtils.format(new Date()));
        practiceInfoDao.updateById(info);
    }

    @Override
    public PageResponse<PracticeInfo> list(String studentId, PageQo pageQo) {
        QueryWrapper<PracticeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("studentId", studentId);

        if (pageQo.getPageSize() == -1) {
            List<PracticeInfo> list = practiceInfoDao.selectList(queryWrapper);
            list.forEach(info -> {
                info.setTypeName(EPracticeType.getDescByCode(info.getType()));
                info.setStatusName(EPracticeStatus.getDescByCode(info.getPracticeStatus()));
                if (info.getBeginTime() != null && !"".equals(info.getBeginTime())) {
                    info.setBeginTime(info.getBeginTime().split(" ")[0]);
                }
                if (info.getEndTime() != null && !"".equals(info.getEndTime())) {
                    info.setEndTime(info.getEndTime().split(" ")[0]);
                }
            });
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeInfo> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeInfo> list = practiceInfoDao.selectPage(page, queryWrapper);
        List<PracticeInfo> records = list.getRecords();
        records.forEach(info -> {
            info.setTypeName(EPracticeType.getDescByCode(info.getType()));
            info.setStatusName(EPracticeStatus.getDescByCode(info.getPracticeStatus()));
            if (info.getBeginTime() != null && !"".equals(info.getBeginTime())) {
                info.setBeginTime(info.getBeginTime().split(" ")[0]);
            }
            if (info.getEndTime() != null && !"".equals(info.getEndTime())) {
                info.setEndTime(info.getEndTime().split(" ")[0]);
            }
        });
        return new PageResponse<>(list.getTotal(), records);
    }

    @Override
    public List<String> listStudentId(int status) {
        QueryWrapper<PracticeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("practiceStatus", status);
        List<PracticeInfo> list = practiceInfoDao.selectList(queryWrapper);
        return list.stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> listAllStudentId() {
        List<PracticeInfo> list = practiceInfoDao.selectList(null);
        return list.stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
    }

    @Override
    public PracticeInfoStatistics statistics(Integer collegeId) {
        PracticeInfoStatistics statistics = new PracticeInfoStatistics();

        QueryWrapper<UserStudent> studentWrapper = new QueryWrapper<>();
        if (collegeId != null) {
            studentWrapper.eq("collegeId", collegeId);
        }
        List<UserStudent> allStudents = studentDao.selectList(studentWrapper);
        if (allStudents.size() == 0) {
            return statistics;
        }
        List<String> allStudentIds = allStudents.stream().map(UserStudent::getId).distinct().collect(Collectors.toList());

        // 实习中
        QueryWrapper<PracticeInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("practiceStatus", EPracticeStatus.STATUS_DOING.statusCode);
        queryWrapper.in("studentId", allStudentIds);
        List<PracticeInfo> list = practiceInfoDao.selectList(queryWrapper);
        List<String> practicingStudents = list.stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
        statistics.setPracticingCount(practicingStudents.size());

        // 结束实习
        QueryWrapper<PracticeInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("practiceStatus", EPracticeStatus.STATUS_END.statusCode);
        if (practicingStudents.size() != 0) {
            wrapper.notIn("studentId", practicingStudents);
        }
        wrapper.in("studentId", allStudentIds);
        List<String> donePracticeStudents = practiceInfoDao.selectList(wrapper).stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
        statistics.setDonePracticeCount(donePracticeStudents.size());

        // 未实习
        // List<UserStudent> userStudents = studentDao.selectList(null);
        // List<String> allStudents = userStudents.stream().map(UserStudent::getId).distinct().collect(Collectors.toList());
        // // 未实习包括未统计的人数
        // int unDocumentStudents = allStudents.size() - listAllStudentId().size();
        // // 未实习还包括状态是只有未实习的人
        // wrapper = new QueryWrapper<>();
        // wrapper.eq("practiceStatus", EPracticeStatus.STATUS_NOT_BEGIN.statusCode);
        // if (practicingStudents.size() != 0) {
        //     wrapper.notIn("studentId", practicingStudents);
        // }
        // if (donePracticeStudents.size() != 0) {
        //     wrapper.notIn("studentId", donePracticeStudents);
        // }
        // List<String> notPracticeStudents = practiceInfoDao.selectList(wrapper).stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
        // statistics.setNotPracticeCount(unDocumentStudents + notPracticeStudents.size());
        // 未实习
        statistics.setNotPracticeCount(allStudents.size() - practicingStudents.size() - donePracticeStudents.size());
        return statistics;
    }

    @Override
    public PracticeInfoStatistics statistics(String teacherId, Integer classId) {

        PracticeInfoStatistics statistics = new PracticeInfoStatistics();
        QueryWrapper<UserStudent> studentWrapper = new QueryWrapper<>();
        studentWrapper.eq("teacherId", teacherId);
        if (classId != null) {
            studentWrapper.eq("classId", classId);
        }
        List<UserStudent> userStudents = studentDao.selectList(studentWrapper);
        if (userStudents.size() == 0) {
            return statistics;
        }

        List<String> studentIds = userStudents.stream().map(UserStudent::getId).distinct().collect(Collectors.toList());
        QueryWrapper<PracticeInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("practiceStatus", EPracticeStatus.STATUS_DOING.statusCode);
        infoQueryWrapper.in("studentId", studentIds);
        // 实习中
        List<PracticeInfo> practicingInfos = practiceInfoDao.selectList(infoQueryWrapper);
        List<String> practicingStudents = practicingInfos.stream().map(PracticeInfo::getStudentId).collect(Collectors.toList());
        statistics.setPracticingCount(practicingStudents.size());

        // 结束实习
        infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("practiceStatus", EPracticeStatus.STATUS_END.statusCode);
        if (practicingInfos.size() != 0) {
            infoQueryWrapper.notIn("studentId", practicingStudents);
        }
        infoQueryWrapper.in("studentId", studentIds);
        List<String> donePracticeStudents = practiceInfoDao.selectList(infoQueryWrapper).stream().map(PracticeInfo::getStudentId).distinct().collect(Collectors.toList());
        statistics.setDonePracticeCount(donePracticeStudents.size());

        // 未实习，总人数减去实习中和实习结束的
        statistics.setNotPracticeCount(studentIds.size() - statistics.getPracticingCount() - statistics.getDonePracticeCount());
        return statistics;

    }
}
