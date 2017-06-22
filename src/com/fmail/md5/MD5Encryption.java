package com.fmail.md5;
import java.security.MessageDigest;

public class MD5Encryption {
	
	public static String getPassword(String pass)
	{
		String encryptedPass=null;
		try {
			MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
			messageDigestNesnesi.update(pass.getBytes());
			byte messageDigestDizisi[] = messageDigestNesnesi.digest();
			StringBuffer sb32 = new StringBuffer();
			for (int i = 0; i < messageDigestDizisi.length; i++) {			      
			       sb32.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 32));
			}
			encryptedPass = sb32.toString();
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return encryptedPass; 
		
	}
	
	public static void main(String[] args){
		
		System.out.println(MD5Encryption.getPassword("1453"));
		
		
	}
	
}
