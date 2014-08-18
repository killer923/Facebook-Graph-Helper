package com.killer923.fb.service;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.commons.httpclient.methods.RequestEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.killer923.dataFetcher.net.api.exception.ResponseException;

public interface FacebookService
{
	
	LinkedHashMap getUrl(String url) throws ResponseException, JsonParseException, JsonMappingException, IOException;
	public LinkedHashMap postToUrl(String url,RequestEntity postContent,Integer timeout) throws ResponseException, JsonParseException, JsonMappingException, IOException;
}
