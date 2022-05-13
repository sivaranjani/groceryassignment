/**
 * 
 */
package com.cgi.grocery.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.grocery.common.ApplicationQueryConstant;
import com.cgi.grocery.entity.Item;
import com.cgi.grocery.repository.ItemRepository;
import com.cgi.grocery.vo.ItemVo;

/**
 * @author vranj
 *
 */
@Service
public class ItemDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ItemRepository itemRepository;

	public List<ItemVo> getItemListWithMaxPrice() {

		TypedQuery<ItemVo> query = entityManager.createQuery(ApplicationQueryConstant.ITEM_QUERY, ItemVo.class);
		List<ItemVo> list =  query.getResultList();
		return list;
	}

	public Optional<Item> getItemDetails(Long itemId) {
		
		return itemRepository.findById(itemId);
	}
}
