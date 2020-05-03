package com.zshnb.ballplatform.service;

import com.zshnb.ballplatform.entity.PracticeDiary;
import com.zshnb.ballplatform.mapper.PracticeDiaryDao;
import com.zshnb.ballplatform.service.inter.MPPracticeDiaryService;
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
public class PracticeDiaryServiceDiy extends ServiceImpl<PracticeDiaryDao, PracticeDiary> implements MPPracticeDiaryService {

}
