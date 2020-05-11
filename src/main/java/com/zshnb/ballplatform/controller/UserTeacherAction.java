package com.zshnb.ballplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.*;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.PracticeInfoQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.qo.RecruitmentQo;
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
@RequestMapping("/teacher")
public class UserTeacherAction {

    @Autowired
    private MPUserStudentService studentService;

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

    @Autowired
    private MPJobInfoService jobInfoService;

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
    public Response<PageResponse<JobRecruitment>> listJob(@PathVariable String id, @RequestBody RecruitmentQo pageQo) {
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
    public Response<PageResponse<PracticePlan>> practicePlanList(@PathVariable String teacherId, @RequestBody PracticeInfoQo pageQo) {
        PageResponse<PracticePlan> list = planService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }

    @PostMapping("/{teacherId}/practice/diaries")
    public Response<PageResponse<PracticeDiary>> practiceDiaryList(@PathVariable String teacherId, @RequestBody PracticeInfoQo pageQo) {
        PageResponse<PracticeDiary> list = diaryService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }

    @PostMapping("/{teacherId}/practice/reports")
    public Response<PageResponse<PracticeReport>> practiceReportList(@PathVariable String teacherId, @RequestBody PracticeInfoQo pageQo) {
        PageResponse<PracticeReport> list = reportService.teacherList(pageQo, teacherId);
        return Response.ok(list);
    }

    @PostMapping("/{id}/students")
    public Response<PageResponse<StudentInfo>> listStudentInfo(@PathVariable String id, @RequestBody QueryStudentQo studentQo) {
        PageResponse<StudentInfo> list = studentService.listStudentInfo(id, studentQo);
        return Response.ok(list);
    }

    @GetMapping("/{id}/statistics")
    public Response<StatisticsVo> statistics(@PathVariable String id) {
        StatisticsVo statisticsVo = new StatisticsVo();
        PracticeInfoStatistics practiceInfoStatistics = practiceInfoService.statistics(id);
        statisticsVo.setPracticeInfoStatistics(practiceInfoStatistics);
        JobInfoStatistics jobInfoStatistics = jobInfoService.statistics(id);
        statisticsVo.setJobInfoStatistics(jobInfoStatistics);
        return Response.ok(statisticsVo);
    }
}
