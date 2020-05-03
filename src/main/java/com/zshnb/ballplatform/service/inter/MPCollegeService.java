package com.zshnb.ballplatform.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.qo.PageQo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPCollegeService extends IService<College> {

    /**
     * 获取学院列表
     *
     * @return 学院列表
     */
    PageResponse<College> colleges(PageQo pageQo);

    /**
     * 查找学院
     *
     * @param collegeId 学院id
     * @return 学院信息
     */
    College college(int collegeId);
}
