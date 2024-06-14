package com.example.Experian.Bureau.Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Experian.Bureau.Service.Entity.BureauDto;
import com.example.Experian.Bureau.Service.Service.BureauService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/saswat")
public class Experianbureaucontroller {

	
	@Autowired
	BureauService bureauService;
	
	
	@PostMapping("/experian-report")
	public String getBureauReport(@RequestBody BureauDto bureauDto, HttpServletRequest request1, HttpServletResponse response)
	{
		return bureauService.getBureauReport(bureauDto,request1,response);
	}
}
