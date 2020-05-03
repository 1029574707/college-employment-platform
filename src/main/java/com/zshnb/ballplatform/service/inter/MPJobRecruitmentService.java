package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPJobRecruitmentService extends IService<JobRecruitment> {

    void add(JobRecruitment jobRecruitment);

    void delete(int id);

    void update(JobRecruitment jobRecruitment);

    PageResponse<JobRecruitment> list(PageQo pageQo);
}
