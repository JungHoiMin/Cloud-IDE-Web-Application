package com.tukorea.ciwa.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tukorea.ciwa.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired(required = true)
	private SqlSession sqlSession;
	private static final String namespace = "com.tukorea.ciwa.mapper.UserMapper";

	@Override
	public UserVO read(String id) throws Exception {
		UserVO user = sqlSession.selectOne(namespace + ".selectByid", id);
		return user;
	}

	@Override
	public void add(UserVO user) throws Exception {
		sqlSession.insert(namespace + ".insert", user);

	}

	@Override
	public void delete(String id) throws Exception {
		sqlSession.delete(namespace + ".delete", id);
	}

}
