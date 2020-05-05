package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.mapper.ClassDao;
import com.zshnb.ballplatform.mapper.CollegeDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.mapper.UserTeacherDao;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.service.inter.MPUserStudentService;
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
public class UserStudentServiceDiy extends ServiceImpl<UserStudentDao, UserStudent> implements MPUserStudentService {

    @Autowired
    private UserStudentDao studentDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private UserTeacherDao teacherDao;

    @Override
    public void addStudent(UserStudent student) {
        studentDao.insert(student);
    }

    @Override
    public UserStudent getStudentById(String id) {
        UserStudent student = studentDao.selectById(id);
        student.setClassName(classDao.selectById(student.getClassId()).getName());
        student.setCollegeName(collegeDao.selectById(student.getCollegeId()).getName());
        student.setTeacherName(teacherDao.selectById(student.getTeacherId()).getName());
        return student;
    }

    @Override
    public void updateStudent(String id, UserStudent student) {
        student.setId(id);
        studentDao.updateById(student);
    }

    @Override
    public List<UserStudent> students(QueryStudentQo queryStudentQo) {
        return null;
    }

    @Override
    public boolean alreadyExists(String id) {
        return studentDao.selectById(id) != null;
    }
}
