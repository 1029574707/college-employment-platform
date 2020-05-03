package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeDiary;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeDiaryDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticeDiaryService;
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
public class PracticeDiaryServiceDiy extends ServiceImpl<PracticeDiaryDao, PracticeDiary> implements MPPracticeDiaryService {

    @Autowired
    private PracticeDiaryDao practiceDiaryDao;

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private EvaluationDao evaluationDao;

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
                diary.setCompanyName(practiceInfoDao.selectById(diary.getPracticeId()).getCompany());
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
            diary.setCompanyName(practiceInfoDao.selectById(diary.getPracticeId()).getCompany());
            if (diary.getEvaluationId() != null) {
                diary.setEvaluation(evaluationDao.selectById(diary.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }
}
