package com.baord.common;

public class GetDate {
	public static String getDate(String date) {
		System.out.println(date);
		String result=date.substring(0, 10);
		System.out.println("³¯Â¥"+result);
		return result;
	}
}
