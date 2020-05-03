package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.mapper.PracticePlanDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticePlanService;
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
public class PracticePlanServiceDiy extends ServiceImpl<PracticePlanDao, PracticePlan> implements MPPracticePlanService {

    @Autowired
    private PracticePlanDao practicePlanDao;

    @Override
    public void add(String studentId, PracticePlan plan) {
        plan.setStudentId(studentId);
        plan.setCreateTime(DateUtils.format(new Date()));
        practicePlanDao.insert(plan);
    }

    @Override
    public void delete(int id) {
        practicePlanDao.deleteById(id);
    }

    @Override
    public void update(int id, String planContent) {
        PracticePlan practicePlan = practicePlanDao.selectById(id);
        practicePlan.setContent(planContent);
        practicePlanDao.updateById(practicePlan);
    }

    @Override
    public PageResponse<PracticePlan> list(PageQo pageQo, String studentId) {
        return null;
    }
}
