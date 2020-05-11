package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeDiary;
import com.zshnb.ballplatform.entity.PracticeInfo;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.enums.EPlanType;
import com.zshnb.ballplatform.enums.EPracticeType;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeDiaryDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.PracticeInfoQo;
import com.zshnb.ballplatform.service.inter.MPPracticeDiaryService;
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
public class PracticeDiaryServiceDiy extends ServiceImpl<PracticeDiaryDao, PracticeDiary> implements MPPracticeDiaryService {

    @Autowired
    private PracticeDiaryDao practiceDiaryDao;

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private UserStudentDao studentDao;

    @Override
    public void add(String studentId, PracticeDiary diary) {
        diary.setStudentId(studentId);
        diary.setCreateTime(DateUtils.format(new Date()));
        practiceDiaryDao.insert(diary);
    }

    @Override
    public void delete(int id) {
        practiceDiaryDao.deleteById(id);
    }

    @Override
    public void update(int id, String diaryContent) {
        PracticeDiary diary = practiceDiaryDao.selectById(id);
        diary.setUpdateTime(DateUtils.format(new Date()));
        diary.setContent(diaryContent);
        practiceDiaryDao.updateById(diary);
    }

    @Override
    public PageResponse<PracticeDiary> list(PageQo pageQo, String studentId) {
        QueryWrapper<PracticeDiary> wrapper = new QueryWrapper<>();
        wrapper.eq("studentId", studentId);

        if (pageQo.getPageSize() == -1) {
            List<PracticeDiary> list = practiceDiaryDao.selectList(wrapper);
            for (PracticeDiary diary : list) {
                PracticeInfo practiceInfo = practiceInfoDao.selectById(diary.getPracticeId());
                diary.setCompanyName(practiceInfo.getCompany());
                diary.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
                if (diary.getEvaluationId() != null) {
                    diary.setEvaluation(evaluationDao.selectById(diary.getEvaluationId()).getContent());
                }
            }
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeDiary> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeDiary> list = practiceDiaryDao.selectPage(page, wrapper);
        List<PracticeDiary> records = list.getRecords();
        for (PracticeDiary diary : records) {
            PracticeInfo practiceInfo = practiceInfoDao.selectById(diary.getPracticeId());
            diary.setCompanyName(practiceInfo.getCompany());
            diary.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
            if (diary.getEvaluationId() != null) {
                diary.setEvaluation(evaluationDao.selectById(diary.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }

    @Override
    public PageResponse<PracticeDiary> teacherList(PracticeInfoQo pageQo, String teacherId) {

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

        QueryWrapper<PracticeDiary> wrapper = new QueryWrapper<>();
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
            List<PracticeDiary> list = practiceDiaryDao.selectList(wrapper);
            for (PracticeDiary diary : list) {
                PracticeInfo practiceInfo = practiceInfoDao.selectById(diary.getPracticeId());
                diary.setCompanyName(practiceInfo.getCompany());
                diary.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
                UserStudent studentForDiary = userStudents.stream().filter(student -> student.getId().equals(diary.getStudentId())).collect(Collectors.toList()).get(0);
                diary.setStudentName(studentForDiary.getName());
                if (diary.getEvaluationId() != null) {
                    diary.setEvaluation(evaluationDao.selectById(diary.getEvaluationId()).getContent());
                }
            }
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeDiary> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeDiary> list = practiceDiaryDao.selectPage(page, wrapper);
        List<PracticeDiary> records = list.getRecords();
        for (PracticeDiary diary : records) {
            PracticeInfo practiceInfo = practiceInfoDao.selectById(diary.getPracticeId());
            diary.setCompanyName(practiceInfo.getCompany());
            UserStudent studentForDiary = userStudents.stream().filter(student -> student.getId().equals(diary.getStudentId())).collect(Collectors.toList()).get(0);
            diary.setStudentName(studentForDiary.getName());
            diary.setPracticeInfoType(EPracticeType.getDescByCode(practiceInfo.getType()));
            if (diary.getEvaluationId() != null) {
                diary.setEvaluation(evaluationDao.selectById(diary.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }
}
