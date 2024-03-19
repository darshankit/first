package com.kit.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.kit.backend.service.QRCodeService;

@RestController
@RequestMapping("/QrCode")
public class QrCodeGeneratorController {
	@Autowired
	private QRCodeService codeService;

	@GetMapping(value = "/upi", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] generateQrCode(@RequestParam String upiId,
			@RequestParam String amount,
			@RequestParam(defaultValue = "%20", required = false) String name,
			@RequestParam(defaultValue = "%20", required = false) String transactionId) {
		int width = 200;
		int heiht = 200;

		UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme("upi").host("pay")
				.queryParam("pa", upiId)
				.queryParam("pn", name) // URL-encoded space
				.queryParam("tr", transactionId) // URL-encoded space
				.queryParam("am", amount).queryParam("cu", "INR");

		String uri = builder.build().toUriString();
		
		return codeService.generateQrCodeImage(uri, width, heiht);
	}
	
	@GetMapping(value = "/content", produces = org.springframework.http.MediaType.IMAGE_PNG_VALUE)
	public byte[] generateQrCode1(@RequestParam String content) {
		int width = 200;
		int heiht = 200;


		return codeService.generateQrCodeImage(content, width, heiht);
	}
}
