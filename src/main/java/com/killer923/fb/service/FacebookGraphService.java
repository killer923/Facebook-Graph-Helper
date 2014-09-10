package com.killer923.fb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.rmi.UnexpectedException;
import java.util.LinkedHashMap;

import org.apache.http.HttpEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.killer923.dataFetcher.net.api.RequestDispatcher;
import com.killer923.dataFetcher.net.api.ResponseWrapper;
import com.killer923.dataFetcher.net.api.exception.ResponseException;
import com.killer923.fb.model.Facebook;


public class FacebookGraphService implements FacebookService
{
	private Facebook fb;
	private RequestDispatcher httpRequestDispatcher;
	private ObjectMapper objectMapper;
	
	public FacebookGraphService()
	{
		objectMapper=new ObjectMapper();
	}
	
	public Facebook getFb()
	{
		return fb;
	}

	public void setFb(Facebook fb)
	{
		this.fb = fb;
	}
	
	public RequestDispatcher getHttpRequestDispatcher()
	{
		return httpRequestDispatcher;
	}

	public void setHttpRequestDispatcher(RequestDispatcher httpRequestDispatcher)
	{
		this.httpRequestDispatcher = httpRequestDispatcher;
	}

	public ObjectMapper getObjectMapper()
	{
		return objectMapper;
	}

	public void setObjectMapper(ObjectMapper objectMapper)
	{
		this.objectMapper = objectMapper;
	}

	/**
	 * 
	 * @param redirectUrl : The redirect URI must be set to the exact value in the app's configuration.
	 * @return
	 * @throws ResponseException : Error while making the request
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public String getCodeForLongTermToken(String redirectUrl) throws ResponseException, JsonParseException, JsonMappingException, IOException
	{
		//build the url
		StringBuilder url = new StringBuilder("https://graph.facebook.com/oauth/client_code?access_token=");
		url.append(fb.getAccessToken());
		url.append("&client_secret=");
		url.append(fb.getApplicationSecret());
		url.append("&redirect_uri=");
		url.append(redirectUrl);
		url.append("&client_id=");
		url.append(fb.getApplicationId());
		
		//make the request to fb servers
		ResponseWrapper response = null;
		try{
			response=httpRequestDispatcher.sendGETRequest(url.toString(), null);
		}catch(ResponseException e)
		{
			System.out.println("Error making request");
			e.printStackTrace();
		}
		
		//get the data from the received content
		String content = new String(response.getResponse());
		LinkedHashMap recievedMap=(LinkedHashMap) objectMapper.readValue(content, Object.class);
		if(!recievedMap.containsKey("code"))
		{
			throw new UnexpectedException("Unexpected response received.");
		}
		return (String) recievedMap.get("code");
	}
	
	public LinkedHashMap getUrl(String url) throws ResponseException, JsonParseException, JsonMappingException, IOException
	{
		//make url proper
		url = generateUrl(url);
		
		//make the request to fetch content
		ResponseWrapper response = null;
		try{
			response=httpRequestDispatcher.sendGETRequest(url, null);
		}catch(ResponseException e)
		{
			System.out.println("Error making request");
			e.printStackTrace();
		}
		
		//convert the content to required type
		String recievedJson = new String(response.getResponse());
		//TODO catch the JsonMappingException
		LinkedHashMap receivedContent = (LinkedHashMap) objectMapper.readValue(recievedJson,Object.class);
		
		return receivedContent;
	}
	
	public LinkedHashMap postToUrl(String url,HttpEntity postContent,Integer timeout) throws ResponseException, JsonParseException, JsonMappingException, IOException
	{
		//make url proper
		url = generateUrl(url);
		
		if(timeout==null)
		{
			timeout=30;
		}
		
		//make the request to fetch content
		ResponseWrapper response = null;
		try{
			response=httpRequestDispatcher.sendPOSTRequest(url, postContent, null, timeout);
		}catch(ResponseException e)
		{
			System.out.println("Error making request");
			e.printStackTrace();
		}
		
		//convert the content to required type
		String recievedJson = new String(response.getResponse());
		//TODO catch the JsonMappingException
		LinkedHashMap receivedContent = (LinkedHashMap) objectMapper.readValue(recievedJson,Object.class);
		
		return receivedContent;
	}
	
	private String generateUrl(String requestedUrl) throws UnsupportedEncodingException{
		StringBuilder newUrl = new StringBuilder(fb.getVersion().getBaseGraphUrl());
//		newUrl.append(URLEncoder.encode(requestedUrl, "UTF-8"));
		newUrl.append(requestedUrl.replace(" ", "%20"));//replacing ' ' by %20 as direct url encoded url not accepted by Facebook
		String[] parametericUrl=requestedUrl.split("\\?");
		if(parametericUrl.length==1)
		{// there are no get parameters set
			newUrl.append("?access_token=");
		}
		else if(parametericUrl[1].isEmpty())
		{// '?' exists but no fields set
			newUrl.append("access_token=");
		}
		else
		{//parameters exist
			newUrl.append("&access_token=");
		}
		newUrl.append(fb.getAccessToken());
		return newUrl.toString();
	}
}
