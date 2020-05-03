package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.service.inter.MPUserStudentService;
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
public class UserStudentServiceDiy extends ServiceImpl<UserStudentDao, UserStudent> implements MPUserStudentService {

}
