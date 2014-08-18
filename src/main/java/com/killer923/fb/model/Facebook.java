package com.killer923.fb.model;

public class Facebook
{
	private String ssoToken;
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
		return "ERROR!!! Application Secret cannot be revealed.";
	}
	public void setApplicationSecret(String applicationSecret)
	{
		this.applicationSecret = applicationSecret;
	}
	public String getSsoToken()
	{
		return ssoToken;
	}
	public void setSsoToken(String ssoToken)
	{
		this.ssoToken = ssoToken;
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
