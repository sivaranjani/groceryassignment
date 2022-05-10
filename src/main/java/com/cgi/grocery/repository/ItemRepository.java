/**
 * 
 */
package com.cgi.grocery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cgi.grocery.entity.Item;

/**
 * @author vranj
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	
	@Query(value ="select i from Item i where i.itemName = :itemName")
	public Item findByName(@Param("itemName") String itemName);
}
