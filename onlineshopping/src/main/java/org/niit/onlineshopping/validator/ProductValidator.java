package org.niit.onlineshopping.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import org.niit.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(ProductValidator.class);
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
		
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
		
			logger.info("inside no file upload");
			errors.rejectValue("file", null, "Please select an image file to upload");
			return;
		}
		
		if(! 
				(product.getFile().getContentType().equals("image/jpeg") ||
						product.getFile().getContentType().equals("images/png") ||
						product.getFile().getContentType().equals("image/gif")
				 ))
		  
				{
					logger.info("inside wrong file upload");
					errors.rejectValue("file", null, "Please select only image file for upload");
					return;
				}
	}

}
