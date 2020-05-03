package com.zshnb.ballplatform.controller.front;

import com.zshnb.ballplatform.BaseTest;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.entity.Companion;
import com.zshnb.ballplatform.request.ListCompanionRequest;
import com.zshnb.ballplatform.request.UploadCompanionRequest;
import com.zshnb.ballplatform.service.inter.ICompanionService;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CompanionControllerTest extends BaseTest {
	@Autowired
	private ICompanionService companionService;

	@Test
	public void testListCompanion() {
		UploadCompanionRequest uploadCompanionRequest = new UploadCompanionRequest();
		uploadCompanionRequest.setSportItemId(1);
		uploadCompanionRequest.setUserId(userId);
		uploadCompanionRequest.setTime(LocalDateTime.now());
		restTemplate.postForEntity("/front/companion/upload", uploadCompanionRequest, Response.class);
		uploadCompanionRequest.setUserId(2);
		restTemplate.postForEntity("/front/companion/upload", uploadCompanionRequest, Response.class);
		ListCompanionRequest listCompanionRequest = new ListCompanionRequest();
		listCompanionRequest.setUserId(userId);
		listCompanionRequest.setSportItemId(1);
		listCompanionRequest.setStartAt(LocalDateTime.MIN);
		listCompanionRequest.setEndAt(LocalDateTime.now());
		List<Companion> companions = companionService.listCompanion(listCompanionRequest);
		assert companions.size() == 1;
	}

	@Test
	public void testDeleteCompanion() {
		UploadCompanionRequest uploadCompanionRequest = new UploadCompanionRequest();
		uploadCompanionRequest.setSportItemId(1);
		uploadCompanionRequest.setUserId(userId);
		uploadCompanionRequest.setTime(LocalDateTime.now().minusDays(1));
		companionService.uploadCompanion(uploadCompanionRequest);
		ListCompanionRequest listCompanionRequest = new ListCompanionRequest();
		listCompanionRequest.setUserId(userId);
		listCompanionRequest.setSportItemId(1);
		listCompanionRequest.setEndAt(LocalDateTime.now());
		List<Companion> companions = companionService.listCompanion(listCompanionRequest);
		assert companions.size() == 1;
		companionService.deleteCompanion(companions.get(0).getId());
		companions = companionService.listCompanion(listCompanionRequest);
		assert companions.size() == 0;
	}
}
