package com.ibm.javanet.util;

import java.util.ArrayList;
import java.util.Properties;

public class Test {
	public static void main(String[] args) {
		Properties props = System.getProperties(); // 系统属性
		System.out.println("用户的当前工作目录：" + props.getProperty("user.dir"));
		System.out.println("用户的主目录：" + props.getProperty("user.home"));
		System.out.println("Java的类路径：" + props.getProperty("java.class.path"));
		ArrayList<Object> lists = new ArrayList<>();
		int i = 0;
		try {
			while(true) {
				lists.add(new Object());
				i++;
			}
		} catch (Error e) {
			System.out.println(i);
			e.printStackTrace();
		} finally {
			System.out.println(i);
		}
	}
}
