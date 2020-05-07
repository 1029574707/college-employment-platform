package com.zshnb.ballplatform.controller;


import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.service.inter.*;
import com.zshnb.ballplatform.vo.JobInfoStatistics;
import com.zshnb.ballplatform.vo.PracticeInfoStatistics;
import com.zshnb.ballplatform.vo.StatisticsVo;
import com.zshnb.ballplatform.vo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/admin")
public class UserAdminAction {

    @Autowired
    private MPJobRecruitmentService jobRecruitmentService;

    @Autowired
    private MPUserStudentService studentService;

    @Autowired
    private MPUserTeacherService teacherService;

    @Autowired
    private MPPracticeInfoService practiceInfoService;

    @Autowired
    private MPJobInfoService jobInfoService;

    @PostMapping("jobs")
    public Response<PageResponse<JobRecruitment>> listJob(@RequestBody PageQo pageQo) {
        PageResponse<JobRecruitment> list = jobRecruitmentService.list(pageQo);
        return Response.ok(list);
    }

    @PutMapping("job/{id}")
    public Response<String> updateJob(@PathVariable int id, @RequestBody JobRecruitment jobRecruitment) {
        jobRecruitmentService.update(id, jobRecruitment);
        return Response.ok();
    }

    @DeleteMapping("job/{id}")
    public Response<String> deleteJob(@PathVariable int id) {
        jobRecruitmentService.delete(id);
        return Response.ok();
    }

    @PostMapping("job")
    public Response<String> addJob(@RequestBody JobRecruitment jobRecruitment) {
        jobRecruitment.setPublisherId("0000");
        jobRecruitmentService.add(jobRecruitment);
        return Response.ok();
    }

    @PostMapping("/students")
    public Response<PageResponse<StudentInfo>> listStudents(@RequestBody QueryStudentQo studentQo) {
        PageResponse<StudentInfo> list = studentService.listStudentInfo(studentQo);
        return Response.ok(list);
    }

    @PostMapping("/teachers")
    public Response<PageResponse<UserTeacher>> listTeachers(@RequestBody PageQo pageQo) {
        PageResponse<UserTeacher> list = teacherService.teachers(pageQo);
        return Response.ok(list);
    }

    @GetMapping("/statistics")
    public Response<StatisticsVo> statistics() {
        StatisticsVo statisticsVo = new StatisticsVo();
        PracticeInfoStatistics practiceInfoStatistics = practiceInfoService.statistics();
        statisticsVo.setPracticeInfoStatistics(practiceInfoStatistics);
        JobInfoStatistics jobInfoStatistics = jobInfoService.statistics();
        statisticsVo.setJobInfoStatistics(jobInfoStatistics);
        return Response.ok(statisticsVo);
    }
}
