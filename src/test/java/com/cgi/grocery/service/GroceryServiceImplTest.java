/**
 * 
 */
package com.cgi.grocery.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cgi.grocery.common.ApplicationQueryConstant;
import com.cgi.grocery.repository.ItemRepository;
import com.cgi.grocery.vo.ItemVo;


/**
 * @author vranj
 *
 */
@WebMvcTest(controllers = GroceryServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class GroceryServiceImplTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EntityManager entityManager;

	@MockBean
	ItemRepository itemRepository;
	
	

}
