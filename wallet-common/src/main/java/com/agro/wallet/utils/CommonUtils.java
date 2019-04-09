
package com.agro.wallet.utils;

import java.util.UUID;

public class CommonUtils {


	public static boolean isVoid(Object obj){
		if(obj == null){
			return true;
		}
		if(obj instanceof String){
			String str = (String)obj;
			return str.length() == 0;
		}else{
			return false;
		}
	}
	
	public static boolean isNotVoid(Object obj){
		return !isVoid(obj);
	}

	public static String generateUUID(String partCode){
		String uuid = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		return partCode + uuid;
	}

}
