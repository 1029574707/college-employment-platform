package com.zshnb.ballplatform.controller;


import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobRecruitment;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobRecruitmentService;
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

}
