package com.zshnb.ballplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.*;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.*;
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
@RequestMapping("/teacher")
public class UserTeacherAction {

    @Autowired
    private MPJobRecruitmentService jobRecruitmentService;

    @Autowired
    private MPEvaluationService evaluationService;

    @Autowired
    private MPPracticeInfoService practiceInfoService;

    @Autowired
    private MPPracticePlanService planService;

    @Autowired
    private MPPracticeDiaryService diaryService;

    @Autowired
    private MPPracticeReportService reportService;

    @PostMapping("{id}/job")
    public Response<String> addJob(@PathVariable String id, @RequestBody JobRecruitment jobRecruitment) {
        jobRecruitmentService.add(jobRecruitment, id);
        return Response.ok();
    }

    @DeleteMapping("job/{id}")
    public Response<String> deleteJob(@PathVariable int id) {
        jobRecruitmentService.delete(id);
        return Response.ok();
    }

    @PostMapping("{id}/jobs")
    public Response<PageResponse<JobRecruitment>> listJob(@PathVariable String id, @RequestBody PageQo pageQo) {
        PageResponse<JobRecruitment> list = jobRecruitmentService.listTeacherJob(pageQo, id);
        return Response.ok(list);
    }

    @PutMapping("job/{id}")
    public Response<String> updateJob(@PathVariable int id, @RequestBody JobRecruitment jobRecruitment) {
        jobRecruitmentService.update(id, jobRecruitment);
        return Response.ok();
    }

    @PostMapping("evaluation")
    public Response<String> addEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.add(evaluation);
        return Response.ok();
    }

    @PutMapping("evaluation/{id}")
    public Response<String> updateEvaluation(@PathVariable int id, @RequestBody Evaluation evaluation) {
        evaluationService.update(id, evaluation);
        return Response.ok();
    }

    @DeleteMapping("evaluation/{id}")
    public Response<String> deleteEvaluation(@PathVariable int id) {
        evaluationService.delete(id);
        return Response.ok();
    }

    @PutMapping("practice/{id}")
    public Response<String> sourcePractice(@PathVariable int id, @RequestBody JSONObject fractionObject) {
        Integer fraction = fractionObject.getInteger("fraction");
        practiceInfoService.score(id, fraction);
        return Response.ok();
    }

    @PostMapping("/{teacherId}/practice/plans")
    public Response<PageResponse<PracticePlan>> practicePlanList(@PathVariable String teacherId,  @RequestBody PageQo pageQo) {
        PageResponse<PracticePlan> list = planService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }

    @PostMapping("/{teacherId}/practice/diaries")
    public Response<PageResponse<PracticeDiary>> practiceDiaryList(@PathVariable String teacherId, @RequestBody  PageQo pageQo) {
        PageResponse<PracticeDiary> list = diaryService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }

    @PostMapping("/{teacherId}/practice/reports")
    public Response<PageResponse<PracticeReport>> practiceReportList(@PathVariable String teacherId,  @RequestBody PageQo pageQo) {
        PageResponse<PracticeReport> list = reportService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }
}
