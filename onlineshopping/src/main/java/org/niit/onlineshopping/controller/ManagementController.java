package org.niit.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.niit.shoppingbackend.dao.CategoryDAO;
import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dto.Category;
import org.niit.shoppingbackend.dto.Product;
import org.niit.onlineshopping.util.FileUploadUtility;
import org.niit.onlineshopping.validator.ProductValidator;

@Controller
@RequestMapping(value="/manage") //request mapping at class level to restrict the access to the url starting with /manage only to admin
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	
	@RequestMapping(value = "/products", method=RequestMethod.GET)//by default the request method is GET
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
						
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		Product nproduct = new Product(); // this product model is attached to the spring form for Product Management
		
		nproduct.setActive(true);
		nproduct.setSupplierId(1);
		mv.addObject("product", nproduct);
		
		/*
		 * to
		 */
		if(operation!=null) 
		{
			if(operation.equals("product"))
			{
				mv.addObject("message", "Product Submission Successfull!");	
			}
			else if(operation.equals("category"))
			{
				mv.addObject("message", "Category Added Successfully!");	
			}
		}
		
		return mv;
	}
	
	
	//handling product submission
	@RequestMapping(value = "/products", method=RequestMethod.POST)
	public String handleFormSubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult results, Model model,
			HttpServletRequest request) {
		
		//validating the image if new product is added(which requires an image)
		if(mproduct.getId()==0) {
			new ProductValidator().validate(mproduct,results);
		}
		
		else{
			//validating if the product is getting updated along with the image
			if(!mproduct.getFile().getOriginalFilename().equals(""))
			{
				new ProductValidator().validate(mproduct,results);
			}
		}
			
		
		
		//in case of validation errors
		if(results.hasErrors())
		{
			model.addAttribute("title", "Manage Products");
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("message", "Product submission failed!");	
				
			return "page"; //we are just returning the same page with error message instead to redirecting it which will erase off the validations
		}
		logger.info(mproduct.toString());
		
		//to add or update product
		if(mproduct.getId()==0)
		{
			//to add the product to table
			productDAO.add(mproduct);
		}
		else {
			//to update the product to table
			productDAO.update(mproduct);
		}
		
		
		//to upload the image to server path and also project directory
		if(!mproduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request,mproduct.getFile(),mproduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	
	}
	
	/*
	 * for product activation
	 */
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable("id") int id) {
		
		Product product = productDAO.get(id);
		// to check if the product is active or inactive
		boolean isActive = product.isActive();
		
		//if the product is active then set it to deactivate and vice versa
		product.setActive(!product.isActive());
		productDAO.update(product);
		
	//	String str = (isActive)? "You have successfully deactivated the product with id " + product.getId()
		// :"You have successfully activated the product with id " + product.getId() ;
		return (isActive)? "You have successfully deactivated the product"
		 :"You have successfully activated the product" ;
	}
		
	// for editing product details
	@RequestMapping(value = "/{id}/product", method=RequestMethod.GET)
	public ModelAndView handleUpdateProducts(@PathVariable("id") int id) {
						
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts", true);
		Product nproduct = productDAO.get(id);
	
		mv.addObject("product", nproduct);
		
		return mv;
	}
	
	// adding new Category
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute("category")Category aCategory)
	{
		categoryDAO.add(aCategory);
		return "redirect:/manage/products?operation=category";
	}
	
	//returning categories for all the request mappings in this controller
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	
	@ModelAttribute("category")
	public Category getCategory()
	{
		return new Category();
	}
	
	
	
	
	
	
	
	
}
