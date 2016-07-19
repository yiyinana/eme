package cn.xidian.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTermUtils {

	public static String getCurrentTerm(){
		String term;// 课程学期定义为：截取系统时间，以年分为课程年，以月份判断春秋。如系统时间为2016/2，则学期为2016-春；
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format(now);
		String year = time.substring(0, time.indexOf("/"));
		Integer month = Integer.parseInt(time.substring(time.indexOf("/") + 1,
				time.lastIndexOf("/")));
		if (1 < month && month < 9) {
			term = "春";
		} else term = "秋";
		term = year + "-" + term;
		return term;
	}
	
}
