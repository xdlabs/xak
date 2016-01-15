package com.xenondigilabs.xak.oauth;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import com.xenondigilabs.alpha.commons.core.types.Result;
import com.xenondigilabs.xak.persistence.PersistenceService;



public class Oauth {

	//To generate Token,and return with token 
	public AuthCredentials generateToken(String client_id, String secret_key, UUID uuid,PersistenceService pgsql) {
		
		try {
			String token = uuid + client_id + secret_key;
			String encrypted_token = sha1(token);
			String refresh_token = UUID.randomUUID().toString() + client_id + secret_key;
			String encrypted_refresh_token = sha1(refresh_token);

			//Check given credentials are already exists or not
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("client_id", client_id);
			map.put("secret_key", secret_key);
			map.put("uuid", uuid);
			Collection<Object> collection = pgsql.find(AuthCredentials.class,map);
			if (collection.size() == 0) {
				
				//Add new credentials
				AuthCredentials credentials = new AuthCredentials(uuid,
						client_id, secret_key, encrypted_token,
						encrypted_refresh_token);
				pgsql.insert(credentials);
				return credentials;
			
			} else {
				
				//if credentials are exits,then check token is valid or expired
				Iterator itr = collection.iterator();
				while (itr.hasNext()) {
					AuthCredentials credentials = (AuthCredentials) itr.next();
					
					//Get Today Date
					Date today=new Date();
					Date d1=credentials.getAdd_date();
					//Calculate day difference between today and stored date
					long diffday= (today.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
					
					//Token is valid for 1 day,if it exceeds then regenerate the token
					if (diffday >1) {
						
						// token is expired,regenerate the token
						Map<String, Object> mapping = new HashMap<String, Object>();
						mapping.put("uuid", uuid);
						pgsql.remove(AuthCredentials.class, mapping);
						AuthCredentials re_credentials = new AuthCredentials(
								uuid, client_id, secret_key, encrypted_token,
								encrypted_refresh_token);
						pgsql.insert(re_credentials);
						return re_credentials;
					}
					else
					{
						return credentials;//return same token
					}
				}
			}
		} catch (Exception e) {
			return new AuthCredentials();
		}
		return new AuthCredentials();
	}

	//To regenerate the token,with refresh Token
	public AuthCredentials refreshToken(String client_id, String secret_key, UUID uuid,String refresh_token,PersistenceService pgsql) {
		
		//Check given credentials are valid or not
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("client_id", client_id);
		map.put("secret_key", secret_key);
		map.put("uuid", uuid);
		map.put("refresh_token", refresh_token);
		Collection<Object> collection = pgsql.find(AuthCredentials.class,map);
		if (collection.size() == 0) {
			//Wrong Credentials
			return new AuthCredentials();
		}
		else
		{
			//credentials are valid,regenerate the token
			Map<String, Object> mapping = new HashMap<String, Object>();
			mapping.put("uuid", uuid);
			pgsql.remove(AuthCredentials.class, mapping);
			
			//Generate Token and refersh_token
			String token = uuid + client_id + secret_key + refresh_token;
			String encrypted_token = sha1(token);
			String new_refresh_token = UUID.randomUUID().toString() + client_id + secret_key;
			String encrypted_refresh_token = sha1(new_refresh_token);
			
			AuthCredentials re_credentials = new AuthCredentials(
					uuid, client_id, secret_key, encrypted_token,
					encrypted_refresh_token);
			pgsql.insert(re_credentials);
		return re_credentials;
		}
	}
	
	//To check token is valid,expire or correct
	public Result verifyToken(UUID uuid,String token,PersistenceService pgsql) throws ParseException
	{
		Result  result=new Result();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("uuid", uuid);
		Collection<Object> collection = pgsql.find(AuthCredentials.class,map);
		if(collection.size()==0)
		{
			result.setMessage("Invalid Token");
			result.setStatus(false);
		}
		else
		{
			//Check token is valid or expired
			Iterator itr = collection.iterator();
			while (itr.hasNext()) {
				AuthCredentials credentials = (AuthCredentials) itr.next();
				Date d2=new Date();
				Date d1=credentials.getAdd_date();
				long diffday= (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
				
				if (diffday >= 1) {
					//Token has expired
					result.setMessage("Token has expired,Please Regenerate the token with refershtoken");
					result.setStatus(false);
				}
				else
				{
					result.setMessage("OK");
					result.setStatus(true);
				}
			}
		}
		return result;
		
	}
	/*
	 * For Encryption
	 * */
	private static String sha1(String data)
	   {
		 StringBuffer r = new StringBuffer(32);
	      byte[] bytes = data.getBytes();
	      try
	      {
	         MessageDigest md5er = MessageDigest.getInstance("SHA-1");
	         byte[] hash = md5er.digest(bytes);
	         for (int i = 0; i < hash.length; i++)
		      {
		         String x = Integer.toHexString(hash[i] & 0xff);
		         if (x.length() < 2)
		            r.append("0");
		         r.append(x);
		      }
	         return r.toString();
	      }
	      catch (GeneralSecurityException e)
	      {
	         throw new RuntimeException(e);
	      }
	   }
}
