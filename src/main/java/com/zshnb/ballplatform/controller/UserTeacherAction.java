package com.zshnb.ballplatform.controller;


import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Controller
@RequestMapping("/teacher")
public class UserTeacherAction {

    @Autowired
    private MPJobRecruitmentService jobRecruitmentService;

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
        PageResponse<JobRecruitment> list = jobRecruitmentService.list(pageQo, id);
        return Response.ok(list);
    }

    @PutMapping("job/{id}")
    public Response<String> updateJob(@PathVariable int id, @RequestBody JobRecruitment jobRecruitment) {
        jobRecruitmentService.update(id, jobRecruitment);
        return Response.ok();
    }

}
