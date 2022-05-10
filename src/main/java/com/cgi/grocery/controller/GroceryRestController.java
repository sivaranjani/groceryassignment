/**
 * 
 */
package com.cgi.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.grocery.service.GroceryFileService;
import com.cgi.grocery.service.GroceryService;
import com.cgi.grocery.vo.ItemVo;

/**
 * @author Ranjani
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("api/cgi")
public class GroceryRestController {
	
	@Autowired
	private GroceryService groceryService;
	
	@Autowired
	private GroceryFileService groceryFileService;
	
	@GetMapping("/getItemList")
	public ResponseEntity<List<ItemVo>> getItemListWithMaxPrice()
	{
		List<ItemVo> itemList = groceryService.getItemListWithMaxPrice();
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}
	
	@GetMapping("/getItemDetails/{id}")
	public ResponseEntity<List<ItemVo>> getItemDetails(@PathVariable Long id)
	{
		List<ItemVo> itemList = groceryService.getItemDetails(id);
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}
	
	@GetMapping("/uploadGroceryData")
	public void uploadGroceryData()
	{
		System.out.println("uploadGroceryData started:::");
		groceryFileService.readExcelData();
		System.out.println("uploadGroceryData done:::");
	}

}
