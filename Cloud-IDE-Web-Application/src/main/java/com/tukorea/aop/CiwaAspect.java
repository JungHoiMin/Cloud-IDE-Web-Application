package com.tukorea.aop;

import java.io.File;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tukorea.ciwa.domain.FileVO;

@Aspect
@Component
public class CiwaAspect {
	private static final Logger logger = LoggerFactory.getLogger(CiwaAspect.class);
	private static final String path = "C:/CIWA";

	@Before("execution(* com.tukorea.ciwa.service.MemberServiceImpl.*(..))")
	public void checkMainDirectory() throws Exception {
		File projectFolder = new File(path);
		File usersFolder = new File(path + "/users");
		if (!usersFolder.exists()) {
			if (!projectFolder.exists()) {
				try {
					projectFolder.mkdir();
					logger.info(path + " 폴더가 생성되었습니다.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				usersFolder.mkdir();
				logger.info(path + "/users 폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Before("execution(* com.tukorea.ciwa.service.FileServiceImpl.addFile(..))")
	public void checkUserDirectory(JoinPoint jp) throws Exception {
		Object[] obj = jp.getArgs();

		FileVO file = (FileVO) obj[0];
		String folderPath = path + "/users/" + file.getUserid();

		File folder = new File(folderPath);
		if (!folder.exists()) {
			try {
				folder.mkdir();
				logger.info(folderPath + " 폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
