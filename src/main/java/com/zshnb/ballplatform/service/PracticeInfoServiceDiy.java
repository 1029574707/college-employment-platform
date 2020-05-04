package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeInfo;
import com.zshnb.ballplatform.mapper.PracticeInfoDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPPracticeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void score(int id, int fraction) {
        PracticeInfo practiceInfo = practiceInfoDao.selectById(id);
        practiceInfo.setFraction(fraction);
        practiceInfoDao.updateById(practiceInfo);
    }

    @Override
    public void add(String studentId, PracticeInfo info) {
        
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, PracticeInfo info) {

    }

    @Override
    public PageResponse<PracticeInfo> list(String studentId, PageQo pageQo) {
        return null;
    }
}
