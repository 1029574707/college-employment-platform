package com.zshnb.ballplatform.mapper;

import com.zshnb.ballplatform.entity.UserStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zshnb.ballplatform.qo.QueryStudentQo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface UserStudentDao extends BaseMapper<UserStudent> {

    List<UserStudent> listStudents(QueryStudentQo queryStudentQo);
}
