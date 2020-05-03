package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.mapper.PracticeReportDao;
import com.zshnb.ballplatform.service.inter.MPPracticeReportService;
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
public class PracticeReportServiceDiy extends ServiceImpl<PracticeReportDao, PracticeReport> implements MPPracticeReportService {

}
