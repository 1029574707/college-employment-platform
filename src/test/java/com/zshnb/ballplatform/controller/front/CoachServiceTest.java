package com.zshnb.ballplatform.controller.front;

import com.zshnb.ballplatform.BaseTest;
import com.zshnb.ballplatform.entity.Coach;
import com.zshnb.ballplatform.request.ListCoachRequest;
import com.zshnb.ballplatform.service.inter.ICoachService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CoachServiceTest extends BaseTest {
	@Autowired
	private ICoachService coachService;

	@Test
	public void testListCoaches() {
		Coach coach = new Coach();
		coach.setName("name");
		coach.setPrice(10.0);
		coachService.add(coach);
		ListCoachRequest request = new ListCoachRequest();
		List<Coach> coaches = coachService.listCoaches(request);
		assert coaches.size() == 1;
	}
}
