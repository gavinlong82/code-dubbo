package com.ytd.mds.v1;

import java.util.List;

import com.ytd.mds.v1.pojo.OrderPojo;
import com.ytd.mds.v1.pojo.UserModel;

/**
 * 测试接口
 * 
 * @description
 * @project: mds-api
 * @Date:2018年4月13日
 * @version 1.0
 * @Company: YTD
 * @author gavinlong
 */
public interface IUserService {
	public UserModel getUserById(String uid);

	public void saveUser(UserModel userModel);

	public void batchSaveUser(List<UserModel> userModelList);

	public List<UserModel> getAllUser();

	public void deleteUser(String uid);

	public void updateUser(UserModel user);
	
	public List<OrderPojo> createOrder(String size, String userId);
}
