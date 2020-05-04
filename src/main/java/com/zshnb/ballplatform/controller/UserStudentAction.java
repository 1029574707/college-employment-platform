package com.zshnb.ballplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
import com.zshnb.ballplatform.service.inter.MPJobRecruitmentService;
import com.zshnb.ballplatform.service.inter.MPPracticePlanService;
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

}
