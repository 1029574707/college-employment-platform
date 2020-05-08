package com.zshnb.ballplatform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.JobInfo;
import com.zshnb.ballplatform.entity.PracticeInfo;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.enums.EPracticeStatus;
import com.zshnb.ballplatform.mapper.ClassDao;
import com.zshnb.ballplatform.mapper.CollegeDao;
import com.zshnb.ballplatform.mapper.UserStudentDao;
import com.zshnb.ballplatform.mapper.UserTeacherDao;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.service.inter.MPJobInfoService;
import com.zshnb.ballplatform.service.inter.MPPracticeInfoService;
import com.zshnb.ballplatform.service.inter.MPUserStudentService;
import com.zshnb.ballplatform.vo.StudentInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
@Service
public class UserStudentServiceDiy extends ServiceImpl<UserStudentDao, UserStudent> implements MPUserStudentService {

    @Autowired
    private UserStudentDao studentDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private CollegeDao collegeDao;

    @Autowired
    private UserTeacherDao teacherDao;

    @Autowired
    private MPPracticeInfoService practiceInfoService;

    @Autowired
    private MPJobInfoService jobInfoService;

    @Override
    public void addStudent(UserStudent student) {
        studentDao.insert(student);
    }

    @Override
    public UserStudent getStudentById(String id) {
        UserStudent student = studentDao.selectById(id);
        student.setClassName(classDao.selectById(student.getClassId()).getName());
        student.setCollegeName(collegeDao.selectById(student.getCollegeId()).getName());
        student.setTeacherName(teacherDao.selectById(student.getTeacherId()).getName());
        return student;
    }

    @Override
    public void updateStudent(String id, UserStudent student) {
        student.setId(id);
        studentDao.updateById(student);
    }

    @Override
    public boolean alreadyExists(String id) {
        return studentDao.selectById(id) != null;
    }


    @Override
    public PageResponse<StudentInfo> listStudentInfo(String teacherId, QueryStudentQo studentQo) {
        QueryWrapper<UserStudent> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("teacherId", teacherId);

        if (studentQo.getClassId() != null) {
            studentQueryWrapper.eq("classId", studentQo.getClassId());
        }
        if (studentQo.getStudentId() != null) {
            studentQueryWrapper.like("id", studentQo.getStudentId());
        }
        if (studentQo.getPracticeStatus() != null) {
            List<String> doingStudentIdList = practiceInfoService.listStudentId(1);
            List<String> doneStudentIdList = practiceInfoService.listStudentId(2);
            List<String> notBeginStudentIdList = practiceInfoService.listStudentId(0);
            switch (studentQo.getPracticeStatus()) {
                case 1:
                    if (doingStudentIdList.size() == 0) {
                        return new PageResponse<>(0, new ArrayList<>());
                    }
                    studentQueryWrapper.in("id", doingStudentIdList);
                    break;
                case 2:
                    if (doneStudentIdList.size() == 0) {
                        return new PageResponse<>(0, new ArrayList<>());
                    }
                    if (doingStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doingStudentIdList);
                    }
                    studentQueryWrapper.in("id", doneStudentIdList);
                    break;
                case 0:
                    List<String> allNotBeginStudents = practiceInfoService.listAllStudentId();
                    if (notBeginStudentIdList.size() == 0) {
                        if (allNotBeginStudents.size() == 0) {
                            break;
                        }
                        studentQueryWrapper.notIn("id", allNotBeginStudents);
                        break;
                    }
                    if (doingStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doingStudentIdList);
                    }
                    if (doneStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doneStudentIdList);
                    }
                    studentQueryWrapper.and(qw -> qw.in("id", notBeginStudentIdList).or().notIn("id", allNotBeginStudents));
            }
        }

        if (studentQo.getPageSize() == -1) {
            List<UserStudent> userStudents = studentDao.selectList(studentQueryWrapper);
            List<StudentInfo> studentInfoList = new ArrayList<>(userStudents.size());
            for (UserStudent userStudent : userStudents) {
                StudentInfo studentInfo = new StudentInfo();
                BeanUtils.copyProperties(userStudent, studentInfo);
                studentInfo.setClassName(classDao.selectById(studentInfo.getClassId()).getName());
                studentInfo.setCollegeName(collegeDao.selectById(studentInfo.getCollegeId()).getName());
                PageResponse<PracticeInfo> practiceInfoPageList = practiceInfoService.list(studentInfo.getId(), PageQo.allPage());
                studentInfo.setPracticeInfoList(practiceInfoPageList.getResults());
                List<Integer> collect = practiceInfoPageList.getResults().stream().map(PracticeInfo::getPracticeStatus).distinct().collect(Collectors.toList());
                if (collect.contains(EPracticeStatus.STATUS_DOING.statusCode)) {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_DOING.desc);
                } else if (collect.contains(EPracticeStatus.STATUS_END.statusCode)) {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_END.desc);
                } else {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_NOT_BEGIN.desc);
                }

                PageResponse<JobInfo> jobInfoPageList = jobInfoService.list(studentInfo.getId(), PageQo.allPage());
                studentInfo.setJobInfoList(jobInfoPageList.getResults());
                List<Integer> collect1 = jobInfoPageList.getResults().stream().map(JobInfo::getTripartite).distinct().collect(Collectors.toList());
                if (collect1.contains(1)) {
                    studentInfo.setTripartite(true);
                } else {
                    studentInfo.setTripartite(false);
                }
                studentInfoList.add(studentInfo);
            }
            return new PageResponse<>(studentInfoList.size(), studentInfoList);
        }

        Page<UserStudent> page = new Page<>(studentQo.getPageNo(), studentQo.getPageSize());
        Page<UserStudent> list = studentDao.selectPage(page, studentQueryWrapper);
        List<UserStudent> records = list.getRecords();
        List<StudentInfo> studentInfoList = new ArrayList<>(records.size());
        for (UserStudent userStudent : records) {
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(userStudent, studentInfo);
            studentInfo.setClassName(classDao.selectById(studentInfo.getClassId()).getName());
            studentInfo.setCollegeName(collegeDao.selectById(studentInfo.getCollegeId()).getName());
            PageResponse<PracticeInfo> practiceInfoPageList = practiceInfoService.list(studentInfo.getId(), PageQo.allPage());
            studentInfo.setPracticeInfoList(practiceInfoPageList.getResults());
            List<Integer> collect = practiceInfoPageList.getResults().stream().map(PracticeInfo::getPracticeStatus).distinct().collect(Collectors.toList());
            if (collect.contains(EPracticeStatus.STATUS_DOING.statusCode)) {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_DOING.desc);
            } else if (collect.contains(EPracticeStatus.STATUS_END.statusCode)) {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_END.desc);
            } else {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_NOT_BEGIN.desc);
            }

            PageResponse<JobInfo> jobInfoPageList = jobInfoService.list(studentInfo.getId(), PageQo.allPage());
            studentInfo.setJobInfoList(jobInfoPageList.getResults());
            List<Integer> collect1 = jobInfoPageList.getResults().stream().map(JobInfo::getTripartite).distinct().collect(Collectors.toList());
            if (collect1.contains(1)) {
                studentInfo.setTripartite(true);
            } else {
                studentInfo.setTripartite(false);
            }
            studentInfoList.add(studentInfo);
        }
        return new PageResponse<>(list.getTotal(), studentInfoList);
    }

    @Override
    public PageResponse<StudentInfo> listStudentInfo(QueryStudentQo studentQo) {
        QueryWrapper<UserStudent> studentQueryWrapper = new QueryWrapper<>();

        if (studentQo.getClassId() != null) {
            studentQueryWrapper.eq("classId", studentQo.getClassId());
        }
        if (studentQo.getStudentId() != null) {
            studentQueryWrapper.like("id", studentQo.getStudentId());
        }
        if (studentQo.getCollegeId() != null) {
            studentQueryWrapper.eq("collegeId", studentQo.getCollegeId());
        }
        if (studentQo.getTripartite() != null) {
            if (studentQo.getTripartite() == 1) {
                List<String> jobStudentIds = jobInfoService.listStudentId(1);
                if (jobStudentIds.size() == 0) {
                    return new PageResponse<>(0, new ArrayList<>());
                }
                studentQueryWrapper.in("id", jobStudentIds);
            } else {
                List<String> notTripartiteIdList = jobInfoService.listStudentId(0);
                List<String> allStudentId = jobInfoService.listAllStudentId();
                if (notTripartiteIdList.size() == 0) {
                    if (allStudentId.size() != 0) {
                        studentQueryWrapper.notIn("id", allStudentId);
                    }
                } else {
                    studentQueryWrapper.and(qw -> qw.in("id", notTripartiteIdList).or().notIn("id", allStudentId));
                }
            }
        }
        if (studentQo.getPracticeStatus() != null) {
            List<String> doingStudentIdList = practiceInfoService.listStudentId(1);
            List<String> doneStudentIdList = practiceInfoService.listStudentId(2);
            List<String> notBeginStudentIdList = practiceInfoService.listStudentId(0);
            switch (studentQo.getPracticeStatus()) {
                case 1:
                    if (doingStudentIdList.size() == 0) {
                        return new PageResponse<>(0, new ArrayList<>());
                    }
                    studentQueryWrapper.in("id", doingStudentIdList);
                    break;
                case 2:
                    if (doneStudentIdList.size() == 0) {
                        return new PageResponse<>(0, new ArrayList<>());
                    }
                    if (doingStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doingStudentIdList);
                    }
                    studentQueryWrapper.in("id", doneStudentIdList);
                    break;
                case 0:
                    List<String> allNotBeginStudents = practiceInfoService.listAllStudentId();
                    if (notBeginStudentIdList.size() == 0) {
                        if (allNotBeginStudents.size() == 0) {
                            break;
                        }
                        studentQueryWrapper.notIn("id", allNotBeginStudents);
                        break;
                    }
                    if (doingStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doingStudentIdList);
                    }
                    if (doneStudentIdList.size() != 0) {
                        studentQueryWrapper.notIn("id", doneStudentIdList);
                    }
                    studentQueryWrapper.in("id", notBeginStudentIdList);
                    studentQueryWrapper.or().notIn("id", allNotBeginStudents);
            }
        }

        if (studentQo.getPageSize() == -1) {
            List<UserStudent> userStudents = studentDao.selectList(studentQueryWrapper);
            List<StudentInfo> studentInfoList = new ArrayList<>(userStudents.size());
            for (UserStudent userStudent : userStudents) {
                StudentInfo studentInfo = new StudentInfo();
                BeanUtils.copyProperties(userStudent, studentInfo);
                studentInfo.setClassName(classDao.selectById(studentInfo.getClassId()).getName());
                studentInfo.setCollegeName(collegeDao.selectById(studentInfo.getCollegeId()).getName());
                PageResponse<PracticeInfo> practiceInfoPageList = practiceInfoService.list(studentInfo.getId(), PageQo.allPage());
                studentInfo.setPracticeInfoList(practiceInfoPageList.getResults());
                List<Integer> collect = practiceInfoPageList.getResults().stream().map(PracticeInfo::getPracticeStatus).distinct().collect(Collectors.toList());
                if (collect.contains(EPracticeStatus.STATUS_DOING.statusCode)) {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_DOING.desc);
                } else if (collect.contains(EPracticeStatus.STATUS_END.statusCode)) {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_END.desc);
                } else {
                    studentInfo.setPracticeStatus(EPracticeStatus.STATUS_NOT_BEGIN.desc);
                }
                PageResponse<JobInfo> jobInfoPageList = jobInfoService.list(studentInfo.getId(), PageQo.allPage());
                studentInfo.setJobInfoList(jobInfoPageList.getResults());
                List<Integer> collect1 = jobInfoPageList.getResults().stream().map(JobInfo::getTripartite).distinct().collect(Collectors.toList());
                if (collect1.contains(1)) {
                    studentInfo.setTripartite(true);
                } else {
                    studentInfo.setTripartite(false);
                }
                studentInfoList.add(studentInfo);
            }
            return new PageResponse<>(studentInfoList.size(), studentInfoList);
        }

        Page<UserStudent> page = new Page<>(studentQo.getPageNo(), studentQo.getPageSize());
        Page<UserStudent> list = studentDao.selectPage(page, studentQueryWrapper);
        List<UserStudent> records = list.getRecords();
        List<StudentInfo> studentInfoList = new ArrayList<>(records.size());
        for (UserStudent userStudent : records) {
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(userStudent, studentInfo);
            studentInfo.setClassName(classDao.selectById(studentInfo.getClassId()).getName());
            studentInfo.setCollegeName(collegeDao.selectById(studentInfo.getCollegeId()).getName());
            PageResponse<PracticeInfo> practiceInfoPageList = practiceInfoService.list(studentInfo.getId(), PageQo.allPage());
            studentInfo.setPracticeInfoList(practiceInfoPageList.getResults());
            List<Integer> collect = practiceInfoPageList.getResults().stream().map(PracticeInfo::getPracticeStatus).distinct().collect(Collectors.toList());
            if (collect.contains(EPracticeStatus.STATUS_DOING.statusCode)) {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_DOING.desc);
            } else if (collect.contains(EPracticeStatus.STATUS_END.statusCode)) {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_END.desc);
            } else {
                studentInfo.setPracticeStatus(EPracticeStatus.STATUS_NOT_BEGIN.desc);
            }
            PageResponse<JobInfo> jobInfoPageList = jobInfoService.list(studentInfo.getId(), PageQo.allPage());
            studentInfo.setJobInfoList(jobInfoPageList.getResults());
            List<Integer> collect1 = jobInfoPageList.getResults().stream().map(JobInfo::getTripartite).distinct().collect(Collectors.toList());
            if (collect1.contains(1)) {
                studentInfo.setTripartite(true);
            } else {
                studentInfo.setTripartite(false);
            }
            studentInfoList.add(studentInfo);
        }
        return new PageResponse<>(list.getTotal(), studentInfoList);
    }
}
