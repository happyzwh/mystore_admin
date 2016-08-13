package com.mystore.business.util;

import org.apache.commons.logging.Log;

public class ExceptionUtil {

	/**
	 * error级别的日志打印，系统报错，如数据库连接不到，空指针等
	 * @param loger
	 * @param e
	 */
	public static void logerErrorMes(Log loger, Exception e) {
		String errormsg = e + "\r\n";
		StackTraceElement[] stacka = e.getStackTrace();
		for (int i = 0; i < stacka.length; i++) {
			StackTraceElement stmp = stacka[i];
			errormsg += stmp + "\r\n";
		}
		loger.error(errormsg);
		//e.printStackTrace();
	}

	/**
	 * error级别的日志打印，系统报错，如数据库连接不到，空指针等
	 * @param loger
	 * @param message 自定义消息
	 * @param e
	 * @author MengTao
	 */
	public static void logerErrorMes(Log loger, String message, Exception e) {
		String errormsg = e + "\r\n";
		StackTraceElement[] stacka = e.getStackTrace();
		for (int i = 0; i < stacka.length; i++) {
			StackTraceElement stmp = stacka[i];
			errormsg += stmp + "\r\n";
		}
		loger.error(message +"\r\n"+ errormsg);
	}

	/**
	 * 警告级别的日志打印（注意，警告级别的一般都是客户输入造成的错误，程序主动拦截到的）
	 * @param loger
	 * @param msg
	 */
	public static void logerWarnMes(Log loger, String msg) {
		loger.warn(msg);
	}

}
