package com.zshnb.ballplatform.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.RecruitmentQo;

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

    /**
     * 管理员查看招聘列比奥
     *
     * @param pageQo 分页信息
     * @return 招聘信息列表
     */
    PageResponse<JobRecruitment> list(RecruitmentQo pageQo);

    /**
     * 导师查看招聘列表
     *
     * @param pageQo    分页信息
     * @param teacherId 学生学号
     * @return 招聘信息列表
     */
    PageResponse<JobRecruitment> listTeacherJob(RecruitmentQo pageQo, String teacherId);

    /**
     * 学生查看企业招聘信息
     *
     * @param pageQo    分页信息
     * @param studentId 学生学号
     * @return 招聘信息列表
     */
    PageResponse<JobRecruitment> listStudentJob(RecruitmentQo pageQo, String studentId);
}
