/**
 * 
 */
package com.cgi.grocery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.grocery.entity.Item;

/**
 * @author vranj
 *
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
	
}
