package com.tukorea.ciwa.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private static final String path = "C:/ciwa/users/";

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
		setDateTime(file);
		fileDAO.add(file);
		saveBody(file, body);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 10)
	public void updateFile(FileVO file, FileVO modified_file, String body) throws Exception {
		setDateTime(modified_file);
		fileDAO.delete(file.getTitle());
		fileDAO.add(modified_file);
		deleteBody(file);
		saveBody(modified_file, body);
	}

	@Override
	public String getBody(FileVO file) throws Exception {
		String filePath = path + file.getUserid() + "/" + file.getTitle();
		FileInputStream fs = null;
		try {
			File check = new File(filePath);
			if (!check.exists())
				throw new FileNotFoundException(file.getTitle());
			fs = new FileInputStream(filePath);
			byte[] readBuffer = new byte[fs.available()];
			while (fs.read(readBuffer) != -1) {
			}
			return new String(readBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fs.close();
		}
		return null;
	}

	@Override
	public void deleteFile(String title) throws Exception {
		deleteBody(readFile(title));
		fileDAO.delete(title);
	}

	@Override
	public void deleteAllFile(String userid) throws Exception {
		deleteAllBody(userid);
		fileDAO.deleteAll(userid);
	}

	@Override
	public String executeBody(FileVO file) throws Exception {
		String filePath = path + file.getUserid() + "/" + file.getTitle();
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		try {
			if (file.getType().equals("python")) {
				pc = rt.exec("python " + filePath);
			} else if (file.getType().equals("java")) {
				pc = rt.exec("javac " + filePath + " -encoding UTF-8");
				pc.waitFor();
				pc = rt.exec("java -cp " + path + file.getUserid() + "/ "
						+ file.getTitle().substring(0, file.getTitle().length() - 5));
			}

			br = new BufferedReader(new InputStreamReader(pc.getInputStream(), "MS949"));
			String line;
			sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append('\r');
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pc.waitFor();
			br.close();
			pc.destroy();
		}
		return sb.toString();
	}

	@Override
	public void copyFile(FileVO file, String new_title) throws Exception {
		String body = getBody(file);
		file.setTitle(new_title);
		addFile(file, body);
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

	public void deleteAllBody(String userid) throws Exception {
		File folder = new File(path + userid);

		try {
			File[] files = folder.listFiles();

			for (int i = 0; i < files.length; i++)
				files[i].delete();
			folder.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setDateTime(FileVO file) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		file.setModifyDate(sdf.format(now.getTime()));
	}
}
