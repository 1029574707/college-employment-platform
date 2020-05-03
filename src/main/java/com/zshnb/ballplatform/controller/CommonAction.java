package com.zshnb.ballplatform.controller;

import com.zshnb.ballplatform.anno.IgnoreSession;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.Class;
import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.zshnb.ballplatform.enums.EUserType;
import com.zshnb.ballplatform.qo.LoginQo;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.UserQo;
import com.zshnb.ballplatform.service.inter.MPClassService;
import com.zshnb.ballplatform.service.inter.MPCollegeService;
import com.zshnb.ballplatform.service.inter.MPUserStudentService;
import com.zshnb.ballplatform.service.inter.MPUserTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：wh <br/>
 * Description: 公共接口
 **/
@Controller
@RequestMapping("/common")
public class CommonAction {

    @Autowired
    private MPUserTeacherService teacherService;

    @Autowired
    private MPClassService classService;

    @Autowired
    private MPCollegeService collegeService;

    @Autowired
    private MPUserStudentService studentService;

    @PostMapping("/college/{collegeId}/teacher")
    public Response<PageResponse<UserTeacher>> teachers(@PathVariable int collegeId, @RequestBody PageQo pageQo) {
        PageResponse<UserTeacher> teachers = teacherService.teachers(collegeId, pageQo);
        return Response.ok(teachers);
    }

    @PostMapping("/college/{collegeId}/class")
    public Response<PageResponse<Class>> classes(@PathVariable int collegeId, @RequestBody PageQo pageQo) {
        PageResponse<Class> classes = classService.classes(collegeId, pageQo);
        return Response.ok(classes);
    }

    @PostMapping("college")
    public Response<PageResponse<College>> colleges(@RequestBody PageQo pageQo) {
        PageResponse<College> colleges = collegeService.colleges(pageQo);
        return Response.ok(colleges);
    }

    /**
     * 注册，用学生类来接收，因为学生类的字段包含了导师的所有字段
     *
     * @param userInfo 注册信息
     */
    @IgnoreSession
    @PostMapping("/user")
    public Response<String> register(@RequestBody UserQo userInfo) {
        if (userInfo.getUserType() == EUserType.USER_TYPE_STUDENT.typeCode) {
            UserStudent student = new UserStudent();
            BeanUtils.copyProperties(userInfo, student);
            studentService.addStudent(student);
        } else {
            UserTeacher teacher = new UserTeacher();
            BeanUtils.copyProperties(userInfo, teacher);
            teacherService.addTeacher(teacher);
        }
        return Response.ok();
    }

    /**
     * 登录，登陆成功后设置session
     *
     * @param loginQo 登录信息
     * @param session session信息
     */
    @IgnoreSession
    @PostMapping("session")
    public Response<String> login(@RequestBody LoginQo loginQo, HttpSession session) {
        switch (loginQo.getUserType()) {
            case 0:
                if (!loginQo.getId().equals("0000") || !loginQo.getId().equals("123456")) {
                    return Response.error("账号或密码错误");
                }
            case 1:
                UserTeacher teacher = teacherService.getTeacherById(loginQo.getId());
                if (teacher == null || !teacher.getPassword().equals(loginQo.getPassword())) {
                    return Response.error("账号或密码错误");
                }
            case 2:
                UserStudent student = studentService.getStudentById(loginQo.getId());
                if (student == null || !student.getPassword().equals(loginQo.getPassword())) {
                    return Response.error("账号或密码错误");
                }
        }
        session.setAttribute("userId", loginQo.getId());
        session.setAttribute("userType", loginQo.getUserType());
        return Response.ok();
    }

    @DeleteMapping("session")
    public Response<String> logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userType");
        return Response.ok();
    }
}
