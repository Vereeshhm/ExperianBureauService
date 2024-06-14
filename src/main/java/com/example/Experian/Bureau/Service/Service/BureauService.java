package com.example.Experian.Bureau.Service.Service;

import com.example.Experian.Bureau.Service.Entity.BureauDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BureauService {

	public String getBureauReport(BureauDto bureauDto, HttpServletRequest request1, HttpServletResponse response);

}
