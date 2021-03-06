package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.vo.JobInfoStatistics;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPJobInfoService extends IService<JobInfo> {

    void add(String studentId, JobInfo jobInfo);

    void delete(int id);

    void update(int id, JobInfo jobInfo);

    PageResponse<JobInfo> list(String studentId, PageQo pageQo);

    List<String> listStudentId(int tripartite);

    List<String> listAllStudentId();

    JobInfoStatistics statistics(Integer collegeId);

    JobInfoStatistics statistics(String teacherId, Integer classId);
}
