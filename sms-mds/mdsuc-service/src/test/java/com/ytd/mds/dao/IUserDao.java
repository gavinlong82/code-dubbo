package com.ytd.mds.dao;

import java.util.List;

import com.ytd.mds.pojo.User;


public interface IUserDao {
	public void deleteUser(String uame) throws Exception;

	public void saveUser(User user) throws Exception;

	public List<User> queryAll() throws Exception;

	public void updateUser(User user) throws Exception;

	public User queryUserByLoginName(String uid);

	public void batchSaveUser(List<User> userList);

	public void batchSaveUserMapper(List<User> userList);
}
