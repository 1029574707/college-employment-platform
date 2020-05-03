package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.mapper.CollegeDao;
import com.zshnb.ballplatform.service.inter.MPCollegeService;
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
public class CollegeServiceDiy extends ServiceImpl<CollegeDao, College> implements MPCollegeService {

}
