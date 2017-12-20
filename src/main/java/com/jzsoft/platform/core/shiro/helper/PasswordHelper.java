package com.jzsoft.platform.core.shiro.helper;

public class PasswordHelper {

    private static PasswordEncoder passwordEncoder = new PasswordEncoder();
    public static String encode(String rawPass, String salt) {
    	return passwordEncoder.encode(rawPass, salt);
    }
    
    public static String encode(String rawPass) {
    	return passwordEncoder.encode(rawPass);
    }
    
    public static void main(String[] args) {
    	System.out.println(encode("000000"));
	}
}
