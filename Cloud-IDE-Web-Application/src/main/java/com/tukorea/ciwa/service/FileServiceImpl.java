package com.tukorea.ciwa.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tukorea.ciwa.domain.FileVO;
import com.tukorea.ciwa.exception.AlreadyExistFileException;
import com.tukorea.ciwa.persistence.FileDAO;

@Service
public class FileServiceImpl implements FileService {
	private static final String path = "C:/CIWA/users/";

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
		deleteBody(file);
		saveBody(file, body);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 10)
	public void updateFile(FileVO file, FileVO modified_file, String body) throws Exception {
		fileDAO.delete(file.getTitle());
		fileDAO.add(modified_file);
		deleteBody(file);
		saveBody(modified_file, body);
	}

	@Override
	public void deleteFile(String title) throws Exception {
		deleteBody(readFile(title));
		fileDAO.delete(title);
	}

	public void saveBody(FileVO file, String body) throws Exception {
		String filePath = path + file.getUserid() + "/" + file.getTitle();
		BufferedOutputStream bs = null;
		body = body + "\n";
		try {
			bs = new BufferedOutputStream(new FileOutputStream(filePath));
			bs.write(body.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bs.close();
		}
	}

	public void deleteBody(FileVO file) throws AlreadyExistFileException {
		String filePath = path + file.getUserid() + "/" + file.getTitle();
		File localFile = new File(filePath);
		try {
			if (localFile.exists()) {
				localFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
