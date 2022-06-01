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

	@Override
	public void add(FileVO file) throws Exception {
		sqlSession.insert(namespace + ".insert", file);

	}

	@Override
	public FileVO read(String title) throws Exception {
		FileVO file = sqlSession.selectOne(namespace + ".selectBytitle", title);
		return file;
	}

	@Override
	public void delete(String title) throws Exception {
		sqlSession.insert(namespace + ".delete", title);
	}

	@Override
	public void deleteAll(String userid) throws Exception {
		sqlSession.insert(namespace + ".deleteAll", userid);
	}

}
