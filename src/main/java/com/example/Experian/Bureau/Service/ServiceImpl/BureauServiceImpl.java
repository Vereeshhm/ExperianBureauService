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
import com.example.Experian.Bureau.Service.Exception.DobformatException;
import com.example.Experian.Bureau.Service.Exception.EmptyDobException;
import com.example.Experian.Bureau.Service.Exception.EmptyPanException;
import com.example.Experian.Bureau.Service.Exception.Emptyfirstnameexception;
import com.example.Experian.Bureau.Service.Exception.EmptylastnameException;
import com.example.Experian.Bureau.Service.Exception.InvalidParameterException;
import com.example.Experian.Bureau.Service.Exception.InvalidPhonenumException;
import com.example.Experian.Bureau.Service.Exception.NotfoundException;
import com.example.Experian.Bureau.Service.Exception.PhonnumRequiredException;
import com.example.Experian.Bureau.Service.Exception.pincoderequireexception;
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

		int statusCode = response.getStatus();
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

		try {
			String response1 = restTemplate.postForObject(Url, entity, String.class);
			apiLog.setResponseBody(response1);
			apiLog.setStatusCode(HttpStatus.OK.value());
			apiLog.setStatus("SUCCESS");

			return response1;

		} catch (HttpClientErrorException.BadRequest e) {
			String errorMessage = e.getResponseBodyAsString();
			apiLog.setResponseBody(errorMessage);
			apiLog.setStatusCode(e.getStatusCode().value());
			apiLog.setStatus("FAILURE");

			if (errorMessage.contains("Invalid phoneNumber")) {
				throw new InvalidPhonenumException("Invalid phoneNumber");
			} else if (errorMessage.contains("\\\"phoneNumber\\\" must be a number")) {

				throw new PhonnumRequiredException("\\\"phoneNumber\\\" must be a number");

			} else if (errorMessage.contains("Invalid/missing input parameter")) {
				throw new InvalidParameterException("Invalid/missing input parameter");
			} else if (errorMessage.contains("\\\"dateOfBirth\\\" is not allowed to be empty")) {
				throw new EmptyDobException("\\\"dateOfBirth\\\" is not allowed to be empty");
			} else if (errorMessage.contains("\\\"dateOfBirth\\\" with value")) {
				throw new DobformatException(bureauDto.getDateOfBirth());
			} else if (errorMessage.contains("\\\"pan\\\" is not allowed to be empty")) {
				throw new EmptyPanException("\\\"pan\\\" is not allowed to be empty");
			} else if (errorMessage.contains("\\\"firstName\\\" is not allowed to be empty")) {
				throw new Emptyfirstnameexception("\\\"firstName\\\" is not allowed to be empty");
			} else if (errorMessage.contains("\\\"lastName\\\" is not allowed to be empty")) {
				throw new EmptylastnameException("\\\"lastName\\\" is not allowed to be empty");
			} else if (errorMessage.contains("Invalid pincode")) {
				throw new pincoderequireexception("Invalid pincode");
			}

			else {
				throw e;
			}
		} catch (HttpClientErrorException.NotFound e) {
			{
				String errorMessage = e.getResponseBodyAsString();
				apiLog.setResponseBody(errorMessage);
				apiLog.setStatusCode(e.getStatusCode().value());
				apiLog.setStatus("FAILURE");

				if (errorMessage.contains("No Record Found")) {
					throw new NotfoundException("No Record Found");
				} else {
					throw e;
				}
			}

		} finally {
			apiLogRepository.save(apiLog);
		}

	}

}
