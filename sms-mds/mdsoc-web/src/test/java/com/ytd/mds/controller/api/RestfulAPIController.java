package com.ytd.mds.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytd.mds.v1.IUserService;
import com.ytd.mds.v1.pojo.OrderPojo;
import com.ytd.mds.v1.pojo.UserModel;

@RestController
@RequestMapping("api/v2")
public class RestfulAPIController {
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	@RequestMapping(value = "/order/create/{uid}", method = RequestMethod.GET)
	public List<OrderPojo> createOrder(@PathVariable("uid") String userId, @RequestParam("size") String size) {
		return userService.createOrder(size, userId);
	}

	@RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
	public UserModel getUserById(@PathVariable("uid") String uid) {
		return userService.getUserById(uid);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserModel> getAllUser() {
		return userService.getAllUser();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void saveUser(@RequestBody UserModel userModel) {
		userService.saveUser(userModel);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public void batchSaveUser(@RequestBody List<UserModel> userModelList) {
		userService.batchSaveUser(userModelList);
	}

	@RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("uid") String uid) {
		userService.deleteUser(uid);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody UserModel user) {
		userService.updateUser(user);
	}
}
