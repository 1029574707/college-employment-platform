package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.mapper.UserTeacherDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.service.inter.MPUserTeacherService;
import com.zshnb.ballplatform.vo.StudentInfo;
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
public class UserTeacherServiceDiy extends ServiceImpl<UserTeacherDao, UserTeacher> implements MPUserTeacherService {

    @Autowired
    private UserTeacherDao teacherDao;

    @Autowired
    private UserStudentDao studentDao;

    @Override
    public PageResponse<UserTeacher> teachers(int collegeId, PageQo pageQo) {

        UserTeacher teacher = new UserTeacher();
        teacher.setCollegeId(collegeId);
        Wrapper<UserTeacher> eWrapper = new QueryWrapper<>(teacher);

        if (pageQo.getPageSize() == -1) {
            List<UserTeacher> userTeachers = teacherDao.selectList(eWrapper);
            return new PageResponse<>(userTeachers.size(), userTeachers);
        }
        Page<UserTeacher> page = new Page<>(pageQo.getPageNo(), pageQo.getPageSize());
        Page<UserTeacher> userTeacherPage = teacherDao.selectPage(page, eWrapper);

        List<UserTeacher> records = userTeacherPage.getRecords();
        return new PageResponse<>(userTeacherPage.getTotal(), records);
    }

    @Override
    public void addTeacher(UserTeacher teacher) {
        teacherDao.insert(teacher);
    }

    @Override
    public UserTeacher getTeacherById(String id) {
        return teacherDao.selectById(id);
    }

    @Override
    public boolean alreadyExists(String id) {
        return teacherDao.selectById(id) != null;
    }
}
