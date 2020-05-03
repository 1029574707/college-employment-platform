package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.Class;
import com.zshnb.ballplatform.mapper.ClassDao;
import com.zshnb.ballplatform.service.inter.MPClassService;
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
public class ClassServiceDiy extends ServiceImpl<ClassDao, Class> implements MPClassService {

}
