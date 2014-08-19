package com.killer923.fb.model;

public enum FacebookVersion
{
	unversionsed("unversioned","https://graph.facebook.com/"),
	v1("v1","https://graph.facebook.com/v1.0/"),
	v2_0("v2.0","https://graph.facebook.com/v2.0/"),
	v2_1("v2.1","https://graph.facebook.com/v2.1/");
//	v2_2("v2.2","https://graph.facebook.com/v2.2/");
	
	
	private String version;
	private String baseGraphUrl;
	public String getVersion()
	{
		return version;
	}
	public String getBaseGraphUrl()
	{
		return baseGraphUrl;
	}
	private FacebookVersion(String version,String baseGraphUrl)
	{
		this.version=version;
		this.baseGraphUrl=baseGraphUrl;
	}
}
