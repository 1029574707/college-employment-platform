package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.qo.PageQo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPPracticePlanService extends IService<PracticePlan> {

    void add(String studentId, PracticePlan plan);

    void delete(int id);

    void update(int id, String planContent);

    PageResponse<PracticePlan> list(PageQo pageQo, String studentId);

    PageResponse<PracticePlan> teacherList(PageQo pageQo, String teacherId);
}
