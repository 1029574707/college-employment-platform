package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.Evaluation;
import com.zshnb.ballplatform.entity.PracticeDiary;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeDiaryDao;
import com.zshnb.ballplatform.mapper.PracticePlanDao;
import com.zshnb.ballplatform.mapper.PracticeReportDao;
import com.zshnb.ballplatform.service.inter.MPEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Service
public class EvaluationServiceDiy extends ServiceImpl<EvaluationDao, Evaluation> implements MPEvaluationService {

    @Autowired
    private EvaluationDao evaluationDao;

    @Autowired
    private PracticePlanDao planDao;

    @Autowired
    private PracticeDiaryDao diaryDao;

    @Autowired
    private PracticeReportDao reportDao;

    @Override
    public void add(Evaluation evaluation) {
        evaluation.setCreateTime(DateUtils.format(new Date()));
        evaluationDao.insert(evaluation);

        int type = evaluation.getType();
        switch (type) {
            case 1:
                PracticePlan plan = planDao.selectById(evaluation.getAssociateId());
                plan.setEvaluationId(evaluation.getId());
                planDao.updateById(plan);
                break;
            case 2:
                PracticeDiary diary = diaryDao.selectById(evaluation.getAssociateId());
                diary.setEvaluationId(evaluation.getId());
                diaryDao.updateById(diary);
                break;
            case 3:
                PracticeReport report = reportDao.selectById(evaluation.getAssociateId());
                report.setEvaluationId(evaluation.getId());
                reportDao.updateById(report);
                break;
        }
    }

    @Override
    public void delete(int id) {
        evaluationDao.deleteById(id);
    }

    @Override
    public void update(int id, Evaluation evaluation) {
        evaluation.setId(id);
        evaluation.setUpdateTime(DateUtils.format(new Date()));
        evaluationDao.updateById(evaluation);
    }
}
