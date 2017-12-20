package com.jzsoft.platform.core.shiro.helper;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.jzsoft.platform.module.user.model.SysUser;
public class PasswordEncoder {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 1;

    public PasswordEncoder() {
		super();
	}

	public PasswordEncoder(String algorithmName) {
		super();
		this.algorithmName = algorithmName;
	}
	
	public PasswordEncoder(String algorithmName, int hashIterations) {
		super();
		this.algorithmName = algorithmName;
		this.hashIterations = hashIterations;
	}

	public void encode(SysUser user) {
//        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    public String encode(String rawPass, String salt) {
    	String newPassword = new SimpleHash(
    			algorithmName,
    			rawPass,
    			salt==null?null:ByteSource.Util.bytes(salt),
    			hashIterations).toHex();
    	return newPassword;
    }
    
    public String encode(String rawPass) {
    	return encode(rawPass, null);
    }
    
    
    public static void main(String[] args) {
    	String newPassword = new SimpleHash(
    			"md5",
                "000000",
                null,
                1).toHex();
    	
    	System.out.println(newPassword);
    	System.out.println(new PasswordEncoder().encode("000000"));
	}
}
