package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.mapper.JobInfoDao;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
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
public class JobInfoServiceDiy extends ServiceImpl<JobInfoDao, JobInfo> implements MPJobInfoService {

}
