package com.tukorea.ciwa.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tukorea.ciwa.domain.FileVO;

@Repository
public class FileDAOImpl implements FileDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.tukorea.ciwa.mapper.FileMapper";

	@Override
	public List<FileVO> readListByUserid(String id) throws Exception {
		return sqlSession.selectList(namespace + ".selectAllByuserid", id);
	}

}
