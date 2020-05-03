package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.mapper.UserTeacherDao;
import com.zshnb.ballplatform.service.inter.MPUserTeacherService;
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
public class UserTeacherServiceDiy extends ServiceImpl<UserTeacherDao, UserTeacher> implements MPUserTeacherService {

}
