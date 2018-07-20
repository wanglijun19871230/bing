package com.pdstars.common.util;

import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author Yanfa-0509
 * @date 2017年7月4日 下午2:31:13
 * @Title PDStarsStringUtil
 * @Description：
 */
public class PDStarsStringUtil {

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };

	/**
	 * 校验字符串是否为空
	 * 
	 * @author:Wangljun
	 * @DateTime:2017年7月4日 下午2:31:45
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 分割字符串
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年7月4日 下午2:31:59
	 * @param param
	 * @param regex
	 * @return
	 */
	public static String[] splitString(String param, String regex) {

		if (null != param && !param.isEmpty()) {
			return param.split(regex);
		} else {
			return null;
		}
	}

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}
}
