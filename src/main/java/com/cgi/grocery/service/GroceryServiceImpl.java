/**
 * 
 */
package com.cgi.grocery.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.grocery.common.ApplicationQueryConstant;
import com.cgi.grocery.entity.Item;
import com.cgi.grocery.entity.ItemDetails;
import com.cgi.grocery.repository.ItemRepository;
import com.cgi.grocery.vo.ItemVo;

/**
 * @author vranj
 *
 */
@Service
public class GroceryServiceImpl implements GroceryService{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<ItemVo> getItemListWithMaxPrice() {

		TypedQuery<ItemVo> query = entityManager.createQuery(ApplicationQueryConstant.ITEM_QUERY, ItemVo.class);
		List<ItemVo> list =  query.getResultList();
		return list;
	}

	@Override
	public List<ItemVo> getItemDetails(Long itemId) {
		List<ItemVo> itemList = new ArrayList<ItemVo>();
		Optional<Item> item = itemRepository.findById(itemId);
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
