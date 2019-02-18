package com.cjhm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjhm.multiple.product.dao.ProductRepository;
import com.cjhm.multiple.product.model.Product;
import com.cjhm.multiple.user.dao.UserRepository;
import com.cjhm.multiple.user.model.User;

@RestController
public class MultiController {

	@Autowired
	ProductRepository prodouctDao;

	@Autowired
	UserRepository userDao;

	@GetMapping("/list")
	public String list() {
		List<Product> prodLists = prodouctDao.findAll();
		List<User> userLists = userDao.findAll();
		return prodLists.toString() + "\n" + userLists.toString();
	}
}
