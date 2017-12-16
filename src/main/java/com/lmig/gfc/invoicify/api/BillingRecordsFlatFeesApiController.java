package com.lmig.gfc.invoicify.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@RestController
@RequestMapping("/api/flatfees")
public class BillingRecordsFlatFeesApiController {
	
	private BillingRecordRepository billingRecordRepository;
	private CompanyRepository companyRepository;

	public BillingRecordsFlatFeesApiController(BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository) {
		this.billingRecordRepository = billingRecordRepository;
		this.companyRepository = companyRepository;
	}
	
	@PostMapping("")
	public BillingRecord create(@RequestBody FlatFeeBillingRecord billingRecord, Authentication auth) {
		// Get the user from the auth.getPrincipal() method
		User user = (User) auth.getPrincipal();
		billingRecord.setCreatedBy(user);
		
		Company client = companyRepository.findOne(billingRecord.getClient().getId());
		billingRecord.setClient(client);
		
		return billingRecordRepository.save(billingRecord);
	}
	
	

}
