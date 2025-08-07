package storage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String DateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return simpleDateFormat.format(date);
	}
}
