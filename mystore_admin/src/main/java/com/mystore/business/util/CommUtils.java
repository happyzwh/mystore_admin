package com.mystore.business.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class CommUtils {
	
	public static final String DATE_FORMATE_24h2b = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 锟斤拷取锟酵伙拷锟斤拷锟斤拷实锟斤拷IP锟斤拷址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				//锟斤拷锟斤拷锟饺★拷锟斤拷锟斤拷锟斤拷玫锟絀P    
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		//锟斤拷锟斤拷通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷IP为锟酵伙拷锟斤拷锟斤拷实IP,锟斤拷锟絀P锟斤拷锟斤拷','锟街革拷    
		if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15    
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	} 

	
	public static String transferDateToString(Date date,String formate){
		
		SimpleDateFormat dateformat=new SimpleDateFormat(formate); 
		
		return dateformat.format(date);
		
	}
	
	public static String createUUid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
