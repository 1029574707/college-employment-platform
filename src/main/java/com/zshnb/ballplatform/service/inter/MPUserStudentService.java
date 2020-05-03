package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.entity.UserStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.QueryStudentQo;

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

    /**
     * 查询学生列表
     *
     * @param queryStudentQo 查询条件
     * @return 学生列表
     */
    List<UserStudent> students(QueryStudentQo queryStudentQo);

    /**
     * 学号是否已经存在
     *
     * @param id 学号
     * @return true-存在，false-不存在
     */
    boolean alreadyExists(String id);
}
