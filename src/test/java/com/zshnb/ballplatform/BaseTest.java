package com.zshnb.ballplatform;

import com.zshnb.ballplatform.entity.SportItem;
import com.zshnb.ballplatform.entity.User;
import com.zshnb.ballplatform.mapper.SportItemMapper;
import com.zshnb.ballplatform.mapper.UserMapper;
import com.zshnb.ballplatform.request.LoginRequest;
import com.zshnb.ballplatform.request.RegisterRequest;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = {BallPlatformTestApplication.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class BaseTest {
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	private TruncateService truncateService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SportItemMapper sportItemMapper;

	protected int userId = 1;
	protected int sportItemId = 1;
	protected String username = "zsh";
	protected String password = "password";
	public RegisterRequest registerRequest;
	public LoginRequest loginRequest;

	@Before
	public void before() {
		registerRequest = new RegisterRequest();
		registerRequest.setUsername(username);
		registerRequest.setPassword(password);
		loginRequest = new LoginRequest();
		loginRequest.setUsername(username);
		loginRequest.setPassword(password);
		User user = new User();
		user.setId(1);
		user.setUsername(username);
		user.setPassword(password);
		user.setMoney(10.0);
		userMapper.insert(user);

		SportItem sportItem = new SportItem();
		sportItem.setId(1);
		sportItem.setName("篮球");
		sportItemMapper.insert(sportItem);
	}

	@After
	public void after() throws SQLException {
		truncateService.truncate();
	}
}
