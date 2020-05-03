package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.Class;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.mapper.ClassDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Service
public class ClassServiceDiy extends ServiceImpl<ClassDao, Class> implements MPClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public PageResponse<Class> classes(int collegeId, PageQo pageQo) {

        Class aClass = new Class();
        aClass.setCollegeId(collegeId);
        Wrapper<Class> eWrapper = new QueryWrapper<>(aClass);

        if (pageQo.getPageSize() == -1) {
            List<Class> classes = classDao.selectList(eWrapper);
            return new PageResponse<>(classes.size(), classes);
        }
        Page<Class> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<Class> classPage = classDao.selectPage(page, eWrapper);

        List<Class> records = classPage.getRecords();
        return new PageResponse<>(classPage.getTotal(), records);
    }
}
