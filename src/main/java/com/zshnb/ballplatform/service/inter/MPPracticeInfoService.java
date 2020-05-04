package com.zshnb.ballplatform.service.inter;

import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeInfo;
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
public interface MPPracticeInfoService extends IService<PracticeInfo> {

    void score(int id, int fraction);

    void add(String studentId, PracticeInfo info);

    void delete(int id);

    void update(int id, PracticeInfo info);

    PageResponse<PracticeInfo> list(String studentId, PageQo pageQo);
}
