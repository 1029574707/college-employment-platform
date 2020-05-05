package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.QueryStudentQo;
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
public interface MPUserTeacherService extends IService<UserTeacher> {

    /**
     * 获取导师列表
     *
     * @param collegeId 学院id
     * @return 导师列表
     */
    PageResponse<UserTeacher> teachers(int collegeId, PageQo pageQo);

    /**
     * 增加导师
     *
     * @param teacher 导师信息
     */
    void addTeacher(UserTeacher teacher);

    /**
     * 查找导师
     *
     * @param id 导师id
     * @return 导师信息
     */
    UserTeacher getTeacherById(String id);

    /**
     * 教师号是否已经注册
     *
     * @param id 教师号
     * @return true-存在，false-不存在
     */
    boolean alreadyExists(String id);
}
