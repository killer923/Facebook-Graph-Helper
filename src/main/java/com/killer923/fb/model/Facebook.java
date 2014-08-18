package com.killer923.fb.model;

public class Facebook
{
	private String accessToken;
	private boolean isLongTerm;
	private FacebookVersion version;
	private String applicationId;
	private String applicationSecret;
	
	public String getApplicationId()
	{
		return applicationId;
	}
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}
	public String getApplicationSecret()
	{
		return applicationSecret;
	}
	public void setApplicationSecret(String applicationSecret)
	{
		this.applicationSecret = applicationSecret;
	}
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	public boolean isLongTerm()
	{
		return isLongTerm;
	}
	public void setLongTerm(boolean isLongTerm)
	{
		this.isLongTerm = isLongTerm;
	}
	public FacebookVersion getVersion()
	{
		return version;
	}
	public void setVersion(FacebookVersion version)
	{
		this.version = version;
	}
}
