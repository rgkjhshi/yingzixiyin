package com.yingzixiyin.consultant.web.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class PictureUtil {

	public static String savePicture(HttpServletRequest request,CommonsMultipartFile uploadFile) {
		
		String filePath =request.getRealPath("/cpictures") + java.io.File.separator
				+ "images" + java.io.File.separator;
		String uploadFileName = uploadFile.getOriginalFilename();
		int extPos = uploadFileName.lastIndexOf(".");
		String extension = uploadFileName.substring(extPos + 1);

		if (CacheConstants.extensionList.contains(extension)) {
			String fileName = null;
			fileName = MD5Utils.getMD5(UUID.randomUUID().toString()) + "."
					+ extension;
			String url = request.getRequestURI()+"/cpictures/" + fileName;
			String savedFileName = filePath + "/" + fileName;
			File savedFile = new File(savedFileName);

			try {
				uploadFile.getFileItem().write(savedFile);
				return url;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
