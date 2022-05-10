/**
 * 
 */
package com.cgi.grocery.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.grocery.entity.ItemDetails;

/**
 * @author vranj
 *
 */
@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetails,Long>{
	
}
