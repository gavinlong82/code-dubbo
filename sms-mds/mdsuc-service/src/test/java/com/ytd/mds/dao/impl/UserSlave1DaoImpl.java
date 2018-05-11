package com.ytd.mds.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ytd.mds.config.datasource.DataSource;
import com.ytd.mds.dao.IUserDao;
import com.ytd.mds.dao.mapper.UserMapper;
import com.ytd.mds.pojo.User;
import com.ytd.mds.utils.MdsConstants;

@Repository(value = "userSlave1Dao")
public class UserSlave1DaoImpl implements IUserDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Resource(name = "userMapper")
	private UserMapper userMapper;
	
	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public void deleteUser(String uame) throws Exception {
		userMapper.deleteUser(uame);
	}

	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public void saveUser(User user) throws Exception {
		userMapper.saveUser(user);
	}

	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public List<User> queryAll() throws Exception {
		List<User> users = userMapper.queryAll();
		return users;
	}

	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public void updateUser(User user) throws Exception {
		userMapper.updateUser(user);
	}

	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public User queryUserByLoginName(String uid) {
		return userMapper.queryUserByLoginName(uid);
	}

	@Override
	public void batchSaveUser(List<User> userList) {
		
	}

	@Override
	@DataSource(MdsConstants.SLAVE1_DATASOURCE)
	public void batchSaveUserMapper(List<User> userList) {
		SqlSession sqlSession = null;
		int result = 1;
		try {
			sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

			// int batchCount = 1000;// 每批commit的个数
			int batchCount = 10;// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < userList.size();) {
				if (batchLastIndex >= userList.size()) {
					batchLastIndex = userList.size();

					result = result + userMapper.insertBatchUser(userList.subList(index, batchLastIndex));
					sqlSession.commit();
					// 清理缓存，防止溢出
					sqlSession.clearCache();
					System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
					break;// 数据插入完毕，退出循环
				} else {
					result = result + userMapper.insertBatchUser(userList.subList(index, batchLastIndex));
					sqlSession.commit();
					// 清理缓存，防止溢出
					sqlSession.clearCache();
					System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + (batchCount - 1);
				}
				System.out.println(
						"=============>result=[" + result + "] begin=[" + index + "] end=[" + batchLastIndex + "]");
			}
			sqlSession.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != sqlSession) {
				sqlSession.clearCache();
				sqlSession.close();
			}
		}
		
	}

}
