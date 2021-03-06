package com.zshnb.ballplatform.controller;


import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.qo.RecruitmentQo;
import com.zshnb.ballplatform.service.inter.*;
import com.zshnb.ballplatform.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Response<PageResponse<JobRecruitment>> listJob(@RequestBody RecruitmentQo pageQo) {
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
        jobRecruitment.setPublisherId("admin");
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
    public Response<StatisticsVo> statistics(@RequestParam Integer collegeId) {
        StatisticsVo statisticsVo = new StatisticsVo();
        PracticeInfoStatistics practiceInfoStatistics = practiceInfoService.statistics(collegeId);
        statisticsVo.setPracticeInfoStatistics(practiceInfoStatistics);
        JobInfoStatistics jobInfoStatistics = jobInfoService.statistics(collegeId);
        statisticsVo.setJobInfoStatistics(jobInfoStatistics);
        return Response.ok(statisticsVo);
    }

    @GetMapping("/school/statistics")
    public Response<List<CollegeStatisticsVo>> schoolStatistics() {
        return Response.ok(studentService.schoolStatistics());
    }

    @GetMapping("/college/{collegeId}/statistics")
    public Response<List<ClassStatisticsVo>> collegeStatistics(@PathVariable int collegeId) {
        return Response.ok(studentService.collegeStudentsStatistics(collegeId));
    }
}
