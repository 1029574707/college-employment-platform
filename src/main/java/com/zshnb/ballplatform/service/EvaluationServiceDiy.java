package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.Evaluation;
import com.zshnb.ballplatform.mapper.EvaluationDao;
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
    EvaluationDao evaluationDao;

    @Override
    public void add(Evaluation evaluation) {
        evaluation.setCreateTime(DateUtils.format(new Date()));
        evaluationDao.insert(evaluation);
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
