/**
 * 
 */
package com.cgi.grocery.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.cgi.grocery.service.GroceryService;
import com.cgi.grocery.vo.ItemVo;


/**
 * @author vranj
 *
 */
@WebMvcTest(controllers = GroceryRestController.class)
@ExtendWith(SpringExtension.class)
public class GroceryRestControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	GroceryService groceryService;

	@Test
	void test_getItemListWithMaxPrice() throws Exception
	{
		when(groceryService.getItemListWithMaxPrice())
		.thenReturn(Arrays.asList(new ItemVo(71,"Apple",125,new Date())));
		
		mockMvc.perform(get("/api/cgi/getItemList"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[*].itemId").value(71));
		verify(groceryService).getItemListWithMaxPrice();
	}
	
	@Test
	void test_getItemDetails() throws Exception
	{
		String itemName ="Orange";
		when(groceryService.getItemDetails(112L))
		.thenReturn(Arrays.asList(new ItemVo(112,"Orange",165,new Date())));
		
		
		mockMvc.perform(get("/api/cgi/getItemDetails/{id}", 112L))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$[*].itemName").value(itemName));
		verify(groceryService).getItemDetails(anyLong());
	}
	
}
