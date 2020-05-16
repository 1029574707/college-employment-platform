package com.zshnb.ballplatform.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.College;
import com.zshnb.ballplatform.entity.UserStudent;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
import com.zshnb.ballplatform.vo.ClassStatisticsVo;
import com.zshnb.ballplatform.vo.CollegeStatisticsVo;
import com.zshnb.ballplatform.vo.StudentInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPUserStudentService extends IService<UserStudent> {

    /**
     * 新增学生
     */
    void addStudent(UserStudent student);

    /**
     * 查找学生
     *
     * @param id 学生id
     * @return 学生信息
     */
    UserStudent getStudentById(String id);

    void updateStudent(String id, UserStudent student);

    /**
     * 学号是否已经存在
     *
     * @param id 学号
     * @return true-存在，false-不存在
     */
    boolean alreadyExists(String id);

    PageResponse<StudentInfo> listStudentInfo(String teacherId, QueryStudentQo studentQo);

    PageResponse<StudentInfo> listStudentInfo(QueryStudentQo studentQo);

    /**
     * 查看班级统计
     *
     * @param classId 班级id
     * @return 班级统计信息
     */
    ClassStatisticsVo classStudentStatistics(String teacherId, int classId);

    List<CollegeStatisticsVo> schoolStatistics();

    List<ClassStatisticsVo> collegeStudentsStatistics(int collegeId);

    List<ClassStatisticsVo> collegeStudentsStatistics(int collegeId, String teacherId);
}
