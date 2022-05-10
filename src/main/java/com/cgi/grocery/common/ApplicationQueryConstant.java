/**
 * 
 */
package com.cgi.grocery.common;

/**
 * @author vranj
 *
 */
public final class ApplicationQueryConstant {
	
	ApplicationQueryConstant()
	{	}
	
	public static final String ITEM_QUERY ="select new com.cgi.grocery.vo.ItemVo(i.id,i.itemName , max(d.price),max(d.priceDt)) FROM Item i , ItemDetails d WHERE i.id = d.item.id GROUP BY i.itemName ORDER BY i.itemName ASC";

}
