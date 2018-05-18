package com.ytd.mds.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.ytd.mds.dao.IUserDao;
import com.ytd.mds.exception.CustomRuntimeException;
import com.ytd.mds.pojo.User;
import com.ytd.mds.v1.IUserService;
import com.ytd.mds.v1.pojo.OrderItemPojo;
import com.ytd.mds.v1.pojo.OrderPojo;
import com.ytd.mds.v1.pojo.UserModel;

public class UserServiceImpl implements IUserService {

	@Autowired
	@Qualifier("userDao")
	private IUserDao userDao;

	@Autowired
	@Qualifier("userSlave1Dao")
	private IUserDao userSlave1Dao;

	@Override
	public UserModel getUserById(String uid) {
		try {
			UserModel u = new UserModel();
			User user = userDao.queryUserByLoginName(uid);
			u.setUname(user.getUserName());
			u.setLoginName(user.getLoginName());
			return u;
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = CustomRuntimeException.class)
	public void saveUser(UserModel userModel) {
		try {
			User user = new User();
			user.setCustomerId("0");
			user.setPassword("21232f297a57a5a743894a0e4a801fc3");
			user.setRoleId("0");
			user.setUserName(userModel.getUname());
			user.setLoginName(userModel.getLoginName());
			userDao.saveUser(user);
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		}

	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void batchSaveUser(List<UserModel> userModelList) {
		List<User> userList = new ArrayList<User>();
		try {
			if (null != userModelList && !userModelList.isEmpty()) {
				User user = null;
				for (UserModel userModel : userModelList) {
					user = new User();
					user.setCustomerId("0");
					user.setPassword("21232f297a57a5a743894a0e4a801fc3");
					user.setRoleId("0");
					user.setUserName(userModel.getUname());
					user.setLoginName(userModel.getLoginName());
					userList.add(user);
					user = null;
				}
			}
			userDao.batchSaveUser(userList);
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		} finally {
			userList.clear();
		}

	}

	@Override
	public List<UserModel> getAllUser() {
		List<UserModel> userModelList = new ArrayList<UserModel>();
		try {
			List<User> userList = userDao.queryAll();
			UserModel model = null;
			for (User user : userList) {
				model = new UserModel();
				model.setUname(user.getUserName());
				model.setLoginName(user.getLoginName());
				userModelList.add(model);
				model = null;
			}
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		}
		return userModelList;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteUser(String uid) {
		try {
			userDao.deleteUser(uid);
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void updateUser(UserModel userModel) {
		try {
			User user = new User();
			user.setLoginName(userModel.getLoginName());
			user.setUserName(userModel.getUname());
			userDao.updateUser(user);
		} catch (Exception e) {
			throw new CustomRuntimeException(e);
		}

	}

	public List<OrderPojo> createOrder(String size, String userId) {
		int count = StringUtils.isEmpty(size) ? 20 : Integer.valueOf(size);

		List<OrderPojo> orderList = new ArrayList<OrderPojo>();
		OrderPojo order = null;
		for (int i = 0; i < count; i++) {
			order = new OrderPojo();
			order.setCreateTime(new Date());
			order.setItems(Arrays.asList(new OrderItemPojo[] {}));
			order.setOrderName("玩具" + i);
			order.setOrderNo(UUID.randomUUID().toString());
			order.setOrderStatus("E");
			order.setUserId(Long.valueOf(userId));
			orderList.add(order);
			order = null;
		}

		return orderList;
	}

}
