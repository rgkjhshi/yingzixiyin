package com.yingzixiyin.consultant.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;


public class PictureUtil {
    private static final Logger logger = LoggerFactory.getLogger(PictureUtil.class);

    public static String savePicture(HttpServletRequest request, CommonsMultipartFile uploadFile) {

        String filePath = request.getRealPath("/cpictures") + java.io.File.separator
                + "images" + java.io.File.separator;
        String uploadFileName = uploadFile.getOriginalFilename();
        int extPos = uploadFileName.lastIndexOf(".");
        String extension = uploadFileName.substring(extPos + 1);
        logger.debug("--进行图片保存的路径：path=" + filePath);
        if (CacheConstants.extensionList.contains(extension)) {
            String fileName = MD5Utils.getMD5(UUID.randomUUID().toString()) + "." + extension;
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/cpictures/images/" + fileName;
            String savedFileName = filePath + "/" + fileName;
            File savedFile = new File(savedFileName);
            logger.debug("--生成图片url：url=" + url);
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
