package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeDiary;
import com.zshnb.ballplatform.entity.PracticeInfo;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.enums.EPracticeStatus;
import com.zshnb.ballplatform.enums.EPracticeType;
import com.zshnb.ballplatform.mapper.PracticeDiaryDao;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.mapper.PracticePlanDao;
import com.zshnb.ballplatform.mapper.PracticeReportDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticeInfoService;
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
public class PracticeInfoServiceDiy extends ServiceImpl<PracticeInfoDao, PracticeInfo> implements MPPracticeInfoService {

    @Autowired
    private PracticeInfoDao practiceInfoDao;

    @Autowired
    private PracticePlanDao planDao;

    @Autowired
    private PracticeDiaryDao diaryDao;

    @Autowired
    private PracticeReportDao reportDao;

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
            list.forEach(info -> info.setTypeName(EPracticeType.getDescByCode(info.getType())));
            list.forEach(info -> info.setStatusName(EPracticeStatus.getDescByCode(info.getPracticeStatus())));
            return new PageResponse<>(list.size(), list);
        }

        Page<PracticeInfo> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<PracticeInfo> list = practiceInfoDao.selectPage(page, queryWrapper);
        List<PracticeInfo> records = list.getRecords();
        records.forEach(info -> info.setTypeName(EPracticeType.getDescByCode(info.getType())));
        records.forEach(info -> info.setStatusName(EPracticeStatus.getDescByCode(info.getPracticeStatus())));
        return new PageResponse<>(list.getTotal(), records);
    }
}
