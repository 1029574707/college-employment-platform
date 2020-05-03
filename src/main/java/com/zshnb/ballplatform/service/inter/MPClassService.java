package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPClassService extends IService<Class> {

    /**
     * 获取班级列表
     *
     * @param collegeId 学院id
     * @return 班级列表
     */
    PageResponse<Class> classes(int collegeId, PageQo pageQo);

    /**
     * 获取班级
     *
     * @param id 班级id
     * @return 班级信息
     */
    Class classById(int id);
}
