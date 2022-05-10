/**
 * 
 */
package com.cgi.grocery.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.cgi.grocery.entity.Item;
import com.cgi.grocery.entity.ItemDetails;
import com.cgi.grocery.repository.ItemDetailRepository;
import com.cgi.grocery.repository.ItemRepository;

/**
 * @author vranj
 *
 */
@Service
@Transactional
public class GroceryFileServiceImpl implements GroceryFileService{

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemDetailRepository detailRepository;

	Workbook workbook;

	@Override
	public void readExcelData() {

		List<String> list = new ArrayList<String>();
		DataFormatter dataFormatter = new DataFormatter();
		try {
			File file = ResourceUtils.getFile("classpath:templates/vegetables.xlsx");
			workbook = WorkbookFactory.create(file);

			Sheet sheet = workbook.getSheetAt(0);
			int noOfColumns = sheet.getRow(0).getLastCellNum();

			for (Row row : sheet) {
				for (Cell cell : row) {
					String cellValue = dataFormatter.formatCellValue(cell);
					list.add(cellValue);
				}
			}

			createItemData(list, noOfColumns);

			workbook.close();
		} catch (EncryptedDocumentException |ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createItemData(List<String> excelData, int noOfColumns) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yy");
		int i = noOfColumns;
		do {

			String itemName = excelData.get(i + 1);
			String price = excelData.get(i + 3);
			String priceDate = excelData.get(i + 2);
			if(validateRow(itemName,price,priceDate))
			{

				Item item = itemRepository.findByName(itemName);
				if(item == null)
				{
					item = new Item();
					item.setItemName(itemName);
					itemRepository.save(item);
				}			

				ItemDetails detail = new ItemDetails();
				detail.setPrice(Double.valueOf(price));
				Date priceDt = dateFormat.parse(priceDate);
				detail.setPriceDt(priceDt);
				detail.setItem(item);
				detailRepository.save(detail);
			}
			i = i + (noOfColumns);

		} while (i < excelData.size());

	}

	private boolean validateRow(String itemName, String price, String priceDate)
	{
		if(null != itemName && null != price && null != priceDate)
		{
			return true;
		}
		return false;
	}

}
