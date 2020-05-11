package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeDiary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.entity.PracticePlan;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.PracticeInfoQo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPPracticeDiaryService extends IService<PracticeDiary> {

    void add(String studentId, PracticeDiary diary);

    void delete(int id);

    void update(int id, String diaryContent);

    PageResponse<PracticeDiary> list(PageQo pageQo, String studentId);

    PageResponse<PracticeDiary> teacherList(PracticeInfoQo pageQo, String teacherId);
}
