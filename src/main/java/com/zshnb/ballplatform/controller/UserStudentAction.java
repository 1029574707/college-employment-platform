package com.zshnb.ballplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.*;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
@RequestMapping("/student")
public class UserStudentAction {

    @Autowired
    private MPJobInfoService jobInfoService;

    @Autowired
    private MPJobRecruitmentService jobRecruitmentService;

    @Autowired
    private MPPracticePlanService practicePlanService;

    @Autowired
    private MPPracticeDiaryService practiceDiaryService;

    @Autowired
    private MPPracticeReportService practiceReportService;

    @PostMapping("{studentId}/job/info")
    public Response<String> addJobInfo(@PathVariable String studentId, @RequestBody JobInfo jobInfo) {
        jobInfoService.add(studentId, jobInfo);
        return Response.ok();
    }

    @DeleteMapping("/job/info/{id}")
    public Response<String> deleteJobInfo(@PathVariable int id) {
        jobInfoService.delete(id);
        return Response.ok();
    }

    @PutMapping("/job/info/{id}")
    public Response<String> updateJobInfo(@PathVariable int id, @RequestBody JobInfo jobInfo) {
        jobInfoService.update(id, jobInfo);
        return Response.ok();
    }

    @PostMapping("/{studentId}/job/infos")
    public Response<PageResponse<JobInfo>> listJonInfo(@PathVariable String studentId, @RequestBody PageQo pageQo) {
        PageResponse<JobInfo> list = jobInfoService.list(studentId, pageQo);
        return Response.ok(list);
    }

    @PostMapping("/{studentId}/job/recruitments")
    public Response<PageResponse<JobRecruitment>> listJobRecruitment(@PathVariable String studentId, @RequestBody PageQo pageQo) {
        PageResponse<JobRecruitment> list = jobRecruitmentService.listStudentJob(pageQo, studentId);
        return Response.ok(list);
    }

    @PostMapping("/{studentId}/practice/plan")
    public Response<String> addPracticePlan(@PathVariable String studentId, @RequestBody PracticePlan plan) {
        practicePlanService.add(studentId, plan);
        return Response.ok();
    }

    @DeleteMapping("/practice/plan/{id}")
    public Response<String> deletePracticePlan(@PathVariable int id) {
        practicePlanService.delete(id);
        return Response.ok();
    }

    @PutMapping("/practice/plan/{id}")
    public Response<String> updatePracticePlan(@PathVariable int id, @RequestBody JSONObject contentObject) {
        practicePlanService.update(id, contentObject.getString("content"));
        return Response.ok();
    }

    @PostMapping("/{studentId}/practice/plans")
    public Response<PageResponse<PracticePlan>> listPlans(@PathVariable String studentId, @RequestBody PageQo pageQo) {
        PageResponse<PracticePlan> list = practicePlanService.list(pageQo, studentId);
        return Response.ok(list);
    }

    @PostMapping("/{studentId}/practice/diary")
    public Response<String> addPracticeDiary(@PathVariable String studentId, @RequestBody PracticeDiary diary) {
        practiceDiaryService.add(studentId, diary);
        return Response.ok();
    }

    @DeleteMapping("/practice/diary/{id}")
    public Response<String> deletePracticeDiary(@PathVariable int id) {
        practiceDiaryService.delete(id);
        return Response.ok();
    }

    @PutMapping("/practice/diary/{id}")
    public Response<String> updatePracticeDiary(@PathVariable int id, @RequestBody JSONObject contentObject) {
        practiceDiaryService.update(id, contentObject.getString("content"));
        return Response.ok();
    }

    @PostMapping("/{studentId}/practice/diaries")
    public Response<PageResponse<PracticeDiary>> listDiaries(@PathVariable String studentId, @RequestBody PageQo pageQo) {
        PageResponse<PracticeDiary> list = practiceDiaryService.list(pageQo, studentId);
        return Response.ok(list);
    }

    @PostMapping("/{studentId}/practice/report")
    public Response<String> addPracticeReport(@PathVariable String studentId, @RequestBody PracticeReport report) {
        practiceReportService.add(studentId, report);
        return Response.ok();
    }

    @DeleteMapping("/practice/report/{id}")
    public Response<String> deletePracticeReport(@PathVariable int id) {
        practiceReportService.delete(id);
        return Response.ok();
    }

    @PutMapping("/practice/report/{id}")
    public Response<String> updatePracticeReport(@PathVariable int id, @RequestBody JSONObject contentObject) {
        practiceReportService.update(id, contentObject.getString("content"));
        return Response.ok();
    }

    @PostMapping("/{studentId}/practice/reports")
    public Response<PageResponse<PracticeReport>> listReports(@PathVariable String studentId, @RequestBody PageQo pageQo) {
        PageResponse<PracticeReport> list = practiceReportService.list(pageQo, studentId);
        return Response.ok(list);
    }

}
