package com.niit.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.niit.onlineshopping.service.CartService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {

		ModelAndView mv = new ModelAndView("page");

		if (result != null) {

			switch (result) {

			case "updated":
				mv.addObject("message", "CartLine has been updated successfully");
				break;
			case "added":
				mv.addObject("message", "CartLine has been added successfully");
				break;
			case "maximum":
				mv.addObject("message", "CartLine has reached the maximum count");
				break;
			case "unavailable":
				mv.addObject("message", "Product quantity is not available");
				break;
			case "deleted":
				mv.addObject("message", "CartLine has been deleted successfully");
				break;
			case "error":
				mv.addObject("message", "Something went wrong");
				break;

			}

		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());

		return mv;
	}

	@RequestMapping(value = "/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count) {

		String response = cartService.manageCartLine(cartLineId, count);

		return "redirect:/cart/show?" + response;
	}

	@RequestMapping(value = "/add/{productId}/product")
	public String addCart(@PathVariable int productId) {

		String response = cartService.addCartLine(productId);

		return "redirect:/cart/show?" + response;
	}
	
	@RequestMapping(value = "/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId) {

		String response = cartService.deleteCartLine(cartLineId);

		return "redirect:/cart/show?" + response;
	}

}
