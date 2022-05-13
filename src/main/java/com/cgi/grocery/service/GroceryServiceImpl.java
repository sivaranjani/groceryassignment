/**
 * 
 */
package com.cgi.grocery.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.grocery.dao.ItemDaoImpl;
import com.cgi.grocery.entity.Item;
import com.cgi.grocery.entity.ItemDetails;
import com.cgi.grocery.vo.ItemVo;

/**
 * @author vranj
 *
 */
@Service
public class GroceryServiceImpl implements GroceryService{

	@Autowired
	ItemDaoImpl daoImpl; 

	@Override
	public List<ItemVo> getItemListWithMaxPrice() {
		List<ItemVo> list =  daoImpl.getItemListWithMaxPrice();
		return list;
	}

	@Override
	public List<ItemVo> getItemDetails(Long itemId) {
		List<ItemVo> itemList = new ArrayList<ItemVo>();
		Optional<Item> item = daoImpl.getItemDetails(itemId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if(null != item && null != item.get())
		{
			Item value = item.get();
			List<ItemDetails> detailList = value.getItemDetailList();
			for(ItemDetails detail : detailList)
			{
				ItemVo vo = new ItemVo(value.getId(), value.getItemName(), detail.getPrice(), detail.getPriceDt(),dateFormat.format(detail.getPriceDt()));
				itemList.add(vo);
			}
		}
		return itemList;
	}

}
