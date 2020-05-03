package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.mapper.PracticePlanDao;
import com.zshnb.ballplatform.service.inter.MPPracticePlanService;
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
public class PracticePlanServiceDiy extends ServiceImpl<PracticePlanDao, PracticePlan> implements MPPracticePlanService {

}
