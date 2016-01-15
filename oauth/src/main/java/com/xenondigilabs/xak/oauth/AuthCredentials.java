package com.xenondigilabs.xak.oauth;

import java.util.Date;
import java.util.UUID;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;



@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class AuthCredentials {
	
	UUID uuid;
	String client_id,secret_key,token,refresh_token;
	int expires_in=1;//in days
	Date add_date=new Date();
	
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
		
	public AuthCredentials() {
		
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public AuthCredentials(UUID uuid, String client_id, String secret_key,
			String token, String refresh_token) {
		super();
		this.uuid = uuid;
		this.client_id = client_id;
		this.secret_key = secret_key;
		this.token = token;
		this.refresh_token = refresh_token;
	}
	
	
}
