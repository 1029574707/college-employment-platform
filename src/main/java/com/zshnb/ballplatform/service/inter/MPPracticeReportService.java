package com.zshnb.ballplatform.service.inter;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zshnb.ballplatform.common.PageResponse;
import com.zshnb.ballplatform.entity.PracticeReport;
import com.zshnb.ballplatform.qo.PageQo;
import com.zshnb.ballplatform.qo.PracticeInfoQo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zsh
 * @since 2020-05-03
 */
public interface MPPracticeReportService extends IService<PracticeReport> {

    void add(String studentId, PracticeReport report);

    void delete(int id);

    void update(int id, String reportContent);

    PageResponse<PracticeReport> list(PageQo pageQo, String studentId);

    PageResponse<PracticeReport> teacherList(PracticeInfoQo pageQo, String teacherId);

}
