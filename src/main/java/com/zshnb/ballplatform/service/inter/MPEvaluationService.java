package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.entity.Evaluation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPEvaluationService extends IService<Evaluation> {

    void add(Evaluation evaluation);

    void delete(int id);

    void update(int id, Evaluation evaluation);
}
