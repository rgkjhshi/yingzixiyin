package com.yingzi.web.utils;

public class ByteToHexStringUtils {
	private ByteToHexStringUtils(){};
	/**
	 * 得到byte到16进制的字符串
	 * @param kl
	 * @return
	 */
	private static String getHexString (byte kl){
		StringBuffer rsb=new StringBuffer();
		short ch=0;
		if(kl<0){
			kl+=256;
		}
		for(int i=0;i<2;i++){
			if((i&1)==0){
				ch=(short)((kl&0xF0)>>4);
			}
			else{
				ch=(short)(kl&0x0F);
			}
			if(ch>=0&&ch<=9){
				rsb.append(ch+"");
			}
			else if(ch<16){
				char s='A';
				char c=(char)(s+(ch-10));
				rsb.append(c);
			}
		}
		return rsb.toString();
		
	}
	public static String bytes2HexString(byte[] digest){
		StringBuffer sb=new StringBuffer();
		if(digest==null) {
			return "";
		}
		for(int i=0;i<digest.length;i++){
			sb.append(getHexString(digest[i]));
		}
		return sb.toString().toLowerCase();
	}
}
