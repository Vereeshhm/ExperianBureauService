package com.example.Experian.Bureau.Service.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("file:src/main/resources/application.properties")
public class PropertiesConfig {

	
	@Value("${Apiurl}")
	private String Apiurl;

	public String getApiurl() {
		return Apiurl;
	}

	public void setApiurl(String apiurl) {
		Apiurl = apiurl;
	}
	
	
}
