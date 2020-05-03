package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.UserTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;

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
     * @param id 导师id
     * @return 导师信息
     */
    UserTeacher getTeacherById(String id);
}
