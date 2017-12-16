package com.lmig.gfc.invoicify.api;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/rate-baseds")

public class BillingRecordsRateBasedsApiController {
	
	private BillingRecordRepository billingRecordRepository;
	private CompanyRepository companyRepository;

	public BillingRecordsRateBasedsApiController(BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository) {
		this.billingRecordRepository = billingRecordRepository;
		this.companyRepository = companyRepository;
	}
	
	@PostMapping("")
	public BillingRecord create(@RequestBody RateBasedBillingRecord billingRecord, Authentication auth) {
		User user = (User) auth.getPrincipal();
		billingRecord.setCreatedBy(user);
		
		Company client = companyRepository.findOne(billingRecord.getClient().getId());
		billingRecord.setClient(client);
		
		return billingRecordRepository.save(billingRecord);
	}
	
	

}
