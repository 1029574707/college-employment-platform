package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.Evaluation;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.enums.EEvaluationType;
import com.zshnb.ballplatform.enums.EPlanType;
import com.zshnb.ballplatform.mapper.EvaluationDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.mapper.PracticePlanDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticePlanService;
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
public class PracticePlanServiceDiy extends ServiceImpl<PracticePlanDao, PracticePlan> implements MPPracticePlanService {

    @Autowired
    private PracticePlanDao practicePlanDao;

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private EvaluationDao evaluationDao;

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
        practicePlan.setUpdateTime(DateUtils.format(new Date()));
        practicePlanDao.updateById(practicePlan);
    }

    @Override
    public PageResponse<PracticePlan> list(PageQo pageQo, String studentId) {
        QueryWrapper<PracticePlan> wrapper = new QueryWrapper<>();
        wrapper.eq("studentId", studentId);

        if (pageQo.getPageSize() == -1) {
            List<PracticePlan> list = practicePlanDao.selectList(wrapper);
            for (PracticePlan practicePlan : list) {
                practicePlan.setCompanyName(practiceInfoDao.selectById(practicePlan.getPracticeId()).getCompany());
                practicePlan.setTypeName(EPlanType.getDescByCode(practicePlan.getType()));
                if (practicePlan.getEvaluationId() != null) {
                    practicePlan.setEvaluation(evaluationDao.selectById(practicePlan.getEvaluationId()).getContent());
                }
            }
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticePlan> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticePlan> list = practicePlanDao.selectPage(page, wrapper);
        List<PracticePlan> records = list.getRecords();
        for (PracticePlan practicePlan : records) {
            practicePlan.setCompanyName(practiceInfoDao.selectById(practicePlan.getPracticeId()).getCompany());
            practicePlan.setTypeName(EPlanType.getDescByCode(practicePlan.getType()));
            if (practicePlan.getEvaluationId() != null) {
                practicePlan.setEvaluation(evaluationDao.selectById(practicePlan.getEvaluationId()).getContent());
            }
        }
        return new PageResponse<>(page.getTotal(), records);
    }
}
