package com.tukorea.ciwa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tukorea.ciwa.domain.FileVO;
import com.tukorea.ciwa.exception.AlreadyExistFileException;
import com.tukorea.ciwa.persistence.FileDAO;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDAO fileDAO;

	@Override
	public FileVO readFile(String title) throws Exception {
		return fileDAO.read(title);
	}

	@Override
	public List<FileVO> readFileListByUserid(String id) throws Exception {
		return fileDAO.readListByUserid(id);
	}

	@Override
	public void addFile(FileVO file, String body) throws Exception {
		if (readFile(file.getTitle()) != null)
			throw new AlreadyExistFileException(file.getTitle());
		fileDAO.add(file);
	}

	@Override
	public void updateFile(FileVO file, FileVO modified_file, String body) throws Exception {
		fileDAO.delete(file.getTitle());
		fileDAO.add(modified_file);
	}
}
