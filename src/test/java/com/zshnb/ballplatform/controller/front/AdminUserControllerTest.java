package com.zshnb.ballplatform.controller.front;

import com.zshnb.ballplatform.BaseTest;
import com.zshnb.ballplatform.common.Response;
import com.zshnb.ballplatform.dto.UserDto;
import com.zshnb.ballplatform.request.LoginRequest;
import com.zshnb.ballplatform.request.UpdateInfoRequest;
import com.zshnb.ballplatform.request.UpdatePasswordRequest;
import java.lang.reflect.Type;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminUserControllerTest extends BaseTest {
	public ResponseEntity<Response> register() {
		return restTemplate.postForEntity("/front/user/register", registerRequest, Response.class);
	}

	public ResponseEntity<Response<UserDto>> login(LoginRequest request) {
		ParameterizedTypeReference<Response<UserDto>> ref = new ParameterizedTypeReference<Response<UserDto>>() {
			@Override
			public Type getType() {
				return super.getType();
			}
		};
		return restTemplate.exchange("/front/user/login", HttpMethod.POST,
			new HttpEntity<>(request), ref);
	}

	@Test
	public void testRegister() {
		ResponseEntity<Response> responseEntity = register();
		assert responseEntity.getStatusCode().is2xxSuccessful();
	}

	@Test
	public void testLogin() {
		register();
		ResponseEntity<Response<UserDto>> responseEntity = login(loginRequest);
		assert responseEntity.getStatusCode().is2xxSuccessful();
		loginRequest.setPassword("aaa");
		responseEntity = login(loginRequest);
		assert responseEntity.getStatusCode().is4xxClientError();
	}

	@Test
	public void testUpdateInfo() {
		register();
		ResponseEntity<Response<UserDto>> loginResponse = login(loginRequest);
		UserDto dto = loginResponse.getBody().getData();

		UpdateInfoRequest updateInfoRequest = new UpdateInfoRequest();
		updateInfoRequest.setId(dto.getId());
		updateInfoRequest.setAge(19);
		updateInfoRequest.setSex("man");

		ResponseEntity<Response> updateInfoResponse = restTemplate.postForEntity("/front/user/update-info",
			updateInfoRequest, Response.class);
		assert updateInfoResponse.getStatusCode().is2xxSuccessful();

		loginResponse = login(loginRequest);
		dto = loginResponse.getBody().getData();
		assert dto.getAge().equals(19);
		assert dto.getSex().equals("man");
	}

	@Test
	public void testUpdatePassword() {
		register();
		ResponseEntity<Response<UserDto>> loginResponse = login(loginRequest);
		UserDto dto = loginResponse.getBody().getData();

		UpdatePasswordRequest updatePasswordRequest = new UpdatePasswordRequest();
		updatePasswordRequest.setUsername(dto.getUsername());
		updatePasswordRequest.setPassword("new");
		restTemplate.postForEntity("/front/user/update-password", updatePasswordRequest, Response.class);

		loginResponse = login(loginRequest);
		assert loginResponse.getStatusCode().is4xxClientError();

		loginRequest.setPassword("new");
		loginResponse = login(loginRequest);
		assert loginResponse.getStatusCode().is2xxSuccessful();

	}
}
