package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.mapper.UserStudentDao;
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

    @Override
    public void addStudent(UserStudent student) {
        studentDao.insert(student);
    }

    @Override
    public UserStudent getStudentById(String id) {
        return studentDao.selectById(id);
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
