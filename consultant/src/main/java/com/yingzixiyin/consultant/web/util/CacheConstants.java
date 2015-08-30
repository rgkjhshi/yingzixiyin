package com.yingzixiyin.consultant.web.util;

import java.util.HashSet;
import java.util.Set;

public class CacheConstants {
	
	public static Set<String> extensionList=new HashSet<String>();
	
	static {
        extensionList.add("jpg");
        extensionList.add("jpeg");
        extensionList.add("png");
        extensionList.add("bmp");
        extensionList.add("JPG");
        extensionList.add("JPEG");
        extensionList.add("PNG");
        extensionList.add("BMP");
	}
	
	public static Set<String> videoExtensionList=new HashSet<String>();
	
	static {
	    videoExtensionList.add("MP4");
	    videoExtensionList.add("mp4");
	}
}
