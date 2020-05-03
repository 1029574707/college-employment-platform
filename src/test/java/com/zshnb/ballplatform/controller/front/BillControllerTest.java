package com.zshnb.ballplatform.controller.front;

import com.zshnb.ballplatform.BaseTest;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.dto.UserDto;
import com.zshnb.ballplatform.entity.Bill;
import com.zshnb.ballplatform.entity.User;
import com.zshnb.ballplatform.request.AddBillRequest;
import com.zshnb.ballplatform.request.ListBillRequest;
import com.zshnb.ballplatform.service.inter.IUserService;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class BillControllerTest extends BaseTest {
	@Autowired
	private IUserService userService;

	@Test
	public void testAddBill() {
		AddBillRequest request = new AddBillRequest();
		request.setUserId(userId);
		request.setPrice(10.0);
		ResponseEntity<Response> responseEntity = restTemplate.postForEntity("/front/bill/add", request, Response.class);
		assert responseEntity.getStatusCode().is2xxSuccessful();
		assert responseEntity.getBody().getData().equals(20.0);
		User user = userService.login(loginRequest);
		assert user.getMoney().equals(20.0);
	}

	@Test
	public void testListBill() {
		testAddBill();
		ListBillRequest request = new ListBillRequest();
		request.setUserId(1);
		ResponseEntity<Response<List<Bill>>> responseEntity = restTemplate.exchange(
			"/front/bill/list", HttpMethod.POST,
			new HttpEntity<>(request), new ParameterizedTypeReference<Response<List<Bill>>>() {
				@Override
				public Type getType() {
					return super.getType();
				}
			});
		assert responseEntity.getStatusCode().is2xxSuccessful();
		assert responseEntity.getBody().getData().size() == 1;
		Bill bill = responseEntity.getBody().getData().get(0);
		System.out.println(bill.toString());
	}
}
