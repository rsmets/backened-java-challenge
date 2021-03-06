package com.flickr.project.facade.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.flickr.project.domain.FlickrSearchResponse;
import com.flickr.project.domain.FlickrSizeResponse;

@Service
public class FlickrClient extends AbstractRestfulClient implements IFlickrClient {
		
	@Value("${flickr.api.key}")
	private String _apiKey;
	
	@Value("${flickr.api.base}")
	private String _baseUrl; 
	
	@Value("${flickr.api.search.method}")
	private String _searchMethod;
	
	@Value("${flickr.api.size.method}")
	private String _sizeMethod;
	
	@Override
	public FlickrSizeResponse size(String id) {
		String url = "https://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key=" + _apiKey + "&photo_id=" + id + "&format=json&nojsoncallback=1";

		FlickrSizeResponse response = _restTemplate.getForObject(url, FlickrSizeResponse.class);
		
		return response;
	}

	@Override
	public FlickrSearchResponse search(String search) {
		String url = _baseUrl + _searchMethod + "&api_key=" + _apiKey + "&text=" + search + "&per_page=10&format=json&nojsoncallback=1"; 
		
		FlickrSearchResponse response = _restTemplate.getForObject(url, FlickrSearchResponse.class);

		return response;
	}
	
}
