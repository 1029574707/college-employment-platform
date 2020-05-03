package com.zshnb.ballplatform.controller.backend;

import com.zshnb.ballplatform.BaseTest;
import com.zshnb.ballplatform.entity.Coach;
import com.zshnb.ballplatform.request.backend.ListCoachRequest;
import com.zshnb.ballplatform.service.inter.ICoachService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CoachServiceTest extends BaseTest {
	@Autowired
	private ICoachService coachService;

	@Test
	public void testCRUD() {
		Coach coach = new Coach();
		coach.setName("name");
		coachService.add(coach);
		List<Coach> coaches = coachService.listCoaches(new ListCoachRequest());
		assert coaches.get(0).getName().equals("name");
		Coach coach1 = coaches.get(0);
		coach1.setName("new");
		coachService.update(coach1);
		coaches = coachService.listCoaches(new ListCoachRequest());
		coach1 = coaches.get(0);
		assert coach1.getName().equals("new");
		coachService.delete(coach1.getId());
		coaches = coachService.listCoaches(new ListCoachRequest());
		assert coaches.size() == 0;
	}

}
