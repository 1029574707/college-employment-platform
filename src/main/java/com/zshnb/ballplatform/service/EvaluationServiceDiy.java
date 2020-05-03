package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.Evaluation;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.service.inter.MPEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Service
public class EvaluationServiceDiy extends ServiceImpl<EvaluationDao, Evaluation> implements MPEvaluationService {

}
