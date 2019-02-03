package org.niit.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private static final String ABS_PATH = "C:/Users/Karthik/git/online-shopping/onlineshopping/src/main/webapp/assets/images";//project directory
	private static String REAL_PATH = ""; // server location where the file needs to be kept

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {

		// get the real path

		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");

		logger.info(REAL_PATH);

		// to make all the directories exist
		// if not create the directories
		if (!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		if (!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}

		try {
			// upload to server
			file.transferTo(new File(REAL_PATH + code + ".jpg"));

			// upload to project directory
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		} catch (IOException ex) {
		}

	}

}
