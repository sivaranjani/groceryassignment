/**
 * 
 */
package com.cgi.grocery.service;

import java.util.List;

import com.cgi.grocery.vo.ItemVo;

/**
 * @author vranj
 *
 */
public interface GroceryService {
	
	public List<ItemVo> getItemListWithMaxPrice();
	
	public List<ItemVo> getItemDetails(Long itemId);

}
