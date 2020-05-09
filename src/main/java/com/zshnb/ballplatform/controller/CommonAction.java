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
import com.zshnb.ballplatform.utils.ImageUtils;
import com.zshnb.ballplatform.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * CreateDate：2020/5/3 <br/>
 * Author：wh <br/>
 * Description: 公共接口
 **/
@RestController
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

    @Autowired
    private ImageUtils imageUtils;

    @PostMapping("/college/{collegeId}/teachers")
    public Response<PageResponse<UserTeacher>> teachers(@PathVariable int collegeId, @RequestBody PageQo pageQo) {
        PageResponse<UserTeacher> teachers = teacherService.teachers(collegeId, pageQo);
        return Response.ok(teachers);
    }

    @PostMapping("/college/{collegeId}/classes")
    public Response<PageResponse<Class>> classes(@PathVariable int collegeId, @RequestBody PageQo pageQo) {
        PageResponse<Class> classes = classService.classes(collegeId, pageQo);
        return Response.ok(classes);
    }

    @PostMapping("colleges")
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
            if (studentService.alreadyExists(userInfo.getId())) {
                throw new RuntimeException("该学号已注册");
            }
            UserStudent student = new UserStudent();
            BeanUtils.copyProperties(userInfo, student);
            studentService.addStudent(student);
        } else {
            if (teacherService.alreadyExists(userInfo.getId())) {
                throw new RuntimeException("该教师工号已注册");
            }
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
     * @return
     */
    @IgnoreSession
    @PostMapping("session")
    public Response<UserInfo> login(@RequestBody LoginQo loginQo, HttpSession session) {
        UserInfo userInfo = new UserInfo();
        switch (loginQo.getUserType()) {
            case 0:
                if (!loginQo.getId().equals("0000") || !loginQo.getPassword().equals("123456")) {
                    throw new RuntimeException("账号或密码错误");
                }
                userInfo.setId("0000");
                userInfo.setName("admin");
                userInfo.setPassword("123456");
                userInfo.setUserType(0);
                break;
            case 1:
                UserTeacher teacher;
                try {
                    teacher = teacherService.getTeacherById(loginQo.getId());
                } catch (Exception e) {
                    throw new RuntimeException("账号或密码错误");
                }
                if (teacher == null || !teacher.getPassword().equals(loginQo.getPassword())) {
                    throw new RuntimeException("账号或密码错误");
                }
                BeanUtils.copyProperties(teacher, userInfo);
                College collegeByTeacher = collegeService.college(userInfo.getCollegeId());
                userInfo.setCollegeName(collegeByTeacher.getName());
                userInfo.setUserType(1);
                break;
            case 2:
                UserStudent student;
                try {
                    student = studentService.getStudentById(loginQo.getId());
                } catch (Exception e) {
                    throw new RuntimeException("账号或密码错误");
                }
                if (student == null || !student.getPassword().equals(loginQo.getPassword())) {
                    throw new RuntimeException("账号或密码错误");
                }
                BeanUtils.copyProperties(student, userInfo);
                UserTeacher teacherById = teacherService.getTeacherById(student.getTeacherId());
                userInfo.setTeacherName(teacherById.getName());
                Class aClass = classService.classById(student.getClassId());
                userInfo.setClassName(aClass.getName());
                College collegeByStudent = collegeService.college(userInfo.getCollegeId());
                userInfo.setCollegeName(collegeByStudent.getName());
                userInfo.setUserType(2);
                break;
            default:
        }
        session.setAttribute("userId", loginQo.getId());
        session.setAttribute("userType", loginQo.getUserType());
        return Response.ok(userInfo);
    }

    @DeleteMapping("session")
    public Response<String> logout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("userType");
        return Response.ok();
    }

    @PostMapping("/img")
    public Response<String> uploadImg(@RequestParam MultipartFile file) {
        String fileName = imageUtils.uploadImg(file);
        return Response.ok(fileName);
    }
}
