package com.lmig.gfc.invoicify.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	
	//create repo interfaces
	private BillingRecordRepository billingRecordRepo;
	private CompanyRepository companyRepo;

	public BillingRecordController(BillingRecordRepository billingRecordRepo, CompanyRepository companyRepo) {
		this.billingRecordRepo = billingRecordRepo;
		this.companyRepo = companyRepo;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		
		// Get all the billing records and add them to the model and view with the key "records"
		List<BillingRecord> records = billingRecordRepo.findAll();
		mv.addObject("records", records);
		
		// Get all the companies and add them to the model and view with the key "companies"
		List<Company> companies = companyRepo.findAll();
		mv.addObject("companies", companies);
		
		return mv;
	}

}
