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

    void add(JobRecruitment jobRecruitment, String publisherId);

    void delete(int id);

    void update(int id, JobRecruitment jobRecruitment);

    PageResponse<JobRecruitment> list(PageQo pageQo);

    PageResponse<JobRecruitment> listTeacherJob(PageQo pageQo, String teacherId);

    PageResponse<JobRecruitment> listStudentJob(PageQo pageQo, String studentId);
}
