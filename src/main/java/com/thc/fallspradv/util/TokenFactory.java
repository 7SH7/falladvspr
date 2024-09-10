package com.thc.fallspradv.util;

import java.util.Arrays;

public class TokenFactory {
	String temp_key = "123124125";  // 이거 기반 암호화
	int intervalRefreshToken = 600;
	int intervalAccessToken = 60;

	public String issueRefreshToken(String tbuserId){
		return generateToken(tbuserId, intervalRefreshToken);
	}

	public String issueAccessToken(String refreshToken) throws Exception{
		return generateToken(verifyToken(refreshToken), intervalAccessToken);
	}

	public String generateToken(String tbuserId, int interval){
		String returnVal = "";
		// 저장해야하는정
		// 유저 id값, 만료일
		long period = 0;
		AES256Cipher aes256Cipher = new AES256Cipher();
		try{
			NowDate now = new NowDate();
			String due = now.getDue(interval);
			System.out.println("tbuserId : " + tbuserId);
			System.out.println("due : " + due);
			returnVal = aes256Cipher.AES_Encode(temp_key, tbuserId + "_" + due);

		} catch (Exception e){
			throw new RuntimeException("AES encrypt failed");
		}

		return returnVal;
	}
	public String verifyToken(String token) throws Exception {
		String returnVal = "";
		AES256Cipher aes = new AES256Cipher();
		try{
			// id_만료일
			returnVal = aes.AES_Decode(temp_key, token);
		} catch (Exception e){
			System.out.println("error....");
		}
		if("".equals(returnVal)){
			throw new Exception("....");
		}

		String[] arrayVal = returnVal.split("_");
		String tbuserId = arrayVal[0];
		String due = arrayVal[1];
		NowDate nowDate = new NowDate();
		String now = nowDate.getNow();
		System.out.println("due : " + due);
		System.out.println("now : " + now);

		String[] arrayNow = {due, now};
		Arrays.sort(arrayNow);
		if(due.equals(arrayNow[1])){
			//정상!
			return tbuserId;
		} else {
			//만료!
			throw new Exception("due....");
		}
	}
}