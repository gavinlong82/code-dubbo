package com.ytd.mds.dao.mapper;

import java.util.List;

import com.ytd.mds.pojo.User;

public interface UserMapper {
	List<User> queryAll();
	
	void updateUser(User user);
	
	void deleteUser(String loginName);
	
	void saveUser(User user);
	
	User queryUserByLoginName(String loginName);
	
	int insertBatchUser(List<User> userList);
}
