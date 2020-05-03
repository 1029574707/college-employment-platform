package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.UserAdmin;
import com.zshnb.ballplatform.mapper.UserAdminDao;
import com.zshnb.ballplatform.service.inter.MPUserAdminService;
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
public class UserAdminServiceDiy extends ServiceImpl<UserAdminDao, UserAdmin> implements MPUserAdminService {


}
