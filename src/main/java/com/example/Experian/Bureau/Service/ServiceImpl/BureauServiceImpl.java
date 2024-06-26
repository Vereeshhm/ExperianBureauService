package com.example.Experian.Bureau.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.Experian.Bureau.Service.Entity.BureauDto;
import com.example.Experian.Bureau.Service.Repository.ApiLogRepository;
import com.example.Experian.Bureau.Service.Service.BureauService;
import com.example.Experian.Bureau.Service.Utils.ApiLog;
import com.example.Experian.Bureau.Service.Utils.PropertiesConfig;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class BureauServiceImpl implements BureauService {

	private final RestTemplate restTemplate;

	private final String token;

	@Autowired
	ApiLogRepository apiLogRepository;

	@Autowired
	PropertiesConfig config;

	public BureauServiceImpl(RestTemplate restTemplate, @Value("${token}") String token) {
		this.restTemplate = restTemplate;
		this.token = token;
	}

	@Override
	public String getBureauReport(BureauDto bureauDto, HttpServletRequest request1, HttpServletResponse response) {

		String Url = config.getApiurl();
		String requestUrl = request1.getRequestURI().toString();

//		int statusCode = response.getStatus();
		bureauDto.getPhoneNumber();

		bureauDto.getPan();
		bureauDto.getFirstName();
		bureauDto.getLastName();

		bureauDto.getPincode();
		bureauDto.getDateOfBirth();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", token);

		Gson gson = new Gson();

		String requestBodyJson = gson.toJson(bureauDto);

		HttpEntity<String> entity = new HttpEntity(requestBodyJson, headers);

		ApiLog apiLog = new ApiLog();
		apiLog.setUrl(requestUrl);
		apiLog.setRequestBody(requestBodyJson);
		String response1 = null;
		try {
			response1 = restTemplate.postForObject(Url, entity, String.class);
			apiLog.setResponseBody(response1);
			apiLog.setStatusCode(HttpStatus.OK.value());
			apiLog.setStatus("SUCCESS");

			return response1;

		} catch (HttpClientErrorException.TooManyRequests e) {
			// Handling Too Many Requests Exception specifically
			apiLog.setStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
			apiLog.setStatus("FAILURE");
			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLog.setResponseBodyAsJson("API rate limit exceeded");
		} catch (HttpClientErrorException.Unauthorized e) {
			// Handling Unauthorized Exception specifically
			apiLog.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			apiLog.setStatus("FAILURE");
			response1 = e.getResponseBodyAsString();
			System.out.println(response1 + "Response");
			apiLog.setResponseBodyAsJson("No API key found in request");

		}

		catch (HttpClientErrorException e) {
			apiLog.setStatusCode(e.getStatusCode().value());
			apiLog.setStatus("FAILURE");
			response1 = e.getResponseBodyAsString();
			apiLog.setResponseBody(response1);
		}

		catch (Exception e) {
			apiLog.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			apiLog.setStatus("ERROR");
			response1 = e.getMessage();
			apiLog.setResponseBody(response1);
		}

		finally {
			apiLogRepository.save(apiLog);
		}
		return response1;

	}

}
