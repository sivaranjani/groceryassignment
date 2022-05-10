/**
 * 
 */
package com.cgi.grocery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.cgi.grocery.common.ApplicationQueryConstant;
import com.cgi.grocery.dao.ItemDaoImpl;
import com.cgi.grocery.entity.Item;
import com.cgi.grocery.repository.ItemRepository;
import com.cgi.grocery.vo.ItemVo;


/**
 * @author vranj
 *
 */
@ExtendWith(MockitoExtension.class)
public class GroceryServiceImplTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	GroceryServiceImpl serviceImpl;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	ItemDaoImpl daoImpl;

	@Mock
	ItemRepository itemRepository;
	
	@Mock 
	TypedQuery<ItemVo> query;
	
	public List<ItemVo> getItemList()
	{
		List<ItemVo> list = new ArrayList<ItemVo>();
		ItemVo item1 = new ItemVo(1, "Orange", 29, new Date());
		ItemVo item2 = new ItemVo(2, "Banana", 90,new Date());
		ItemVo item3 = new ItemVo(3, "Apple", 210,new Date());
		list.add(item1);
		list.add(item2);
		list.add(item3);
		return list;
	}
	
	@Test
	public void test_getItemListWithMaxPrice() {
		
		List<ItemVo> itemList = getItemList();
		lenient().doReturn(query).when(entityManager).createQuery(ApplicationQueryConstant.ITEM_QUERY, ItemVo.class);
		lenient().doReturn(itemList).when(query).getResultList();
		
		List<ItemVo> list = daoImpl.getItemListWithMaxPrice();
		assertTrue(list.size() != itemList.size());
		
	}
	
	@Test
	public void test_getItemDetails() {
		List<ItemVo> itemList = getItemList();
		lenient().when(serviceImpl.getItemListWithMaxPrice()).thenReturn(itemList);
		Optional<Item> opt = Optional.of(new Item());
		Item item = opt.get();
		item.setId(2L);
		item.setItemName("Banana");				
		lenient().when(daoImpl.getItemDetails(2L)).thenReturn(Optional.of(item));			
		assertTrue(item != null);	
		assertEquals(item.getItemName() , itemList.get(1).getItemName());
	}

}
