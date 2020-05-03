package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.mapper.CollegeDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CollegeServiceDiy extends ServiceImpl<CollegeDao, College> implements MPCollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public PageResponse<College> colleges(PageQo pageQo) {
        if (pageQo.getPageSize() == -1) {
            List<College> colleges = collegeDao.selectList(null);
            return new PageResponse<>(colleges.size(), colleges);
        }
        Page<College> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<College> collegePage = collegeDao.selectPage(page, null);
        return new PageResponse<>(collegePage.getTotal(), collegePage.getRecords());
    }

    @Override
    public College college(int collegeId) {
        return collegeDao.selectById(collegeId);
    }
}
