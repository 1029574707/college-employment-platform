package com.zshnb.ballplatform.controller;


import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
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
@RequestMapping("/student")
public class UserStudentAction {

    @Autowired
    private MPJobInfoService jobInfoService;

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


}
