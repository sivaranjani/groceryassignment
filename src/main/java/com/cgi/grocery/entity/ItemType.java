/**
 * 
 */
package com.cgi.grocery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Ranjani
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_item_type")
public class ItemType {

	@Id
	@Column(name="type_id")
	@GeneratedValue
	private int typeId;
	@Column(name="type_name")
	private String typeName;
	
}
