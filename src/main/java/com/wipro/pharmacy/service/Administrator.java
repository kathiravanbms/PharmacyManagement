package com.wipro.pharmacy.service;

import java.util.Date;
import java.util.List;
import com.wipro.pharmacy.bean.PharmacyBean;
import com.wipro.pharmacy.dao.PharmacyDAO;
import com.wipro.pharmacy.util.InvalidInputException;
public class Administrator {
	PharmacyDAO dao = new PharmacyDAO();
    public String addRecord(PharmacyBean bean) {
       try {
          if (bean == null || bean.getMedicineName() == null || bean.getPurchaseDate() == null)
				throw new InvalidInputException();
          if (bean.getMedicineName().length() < 2)
				return "INVALID MEDICINE NAME";
          if (dao.recordExists(bean.getMedicineName(), bean.getPurchaseDate()))
				return "ALREADY EXISTS";
           String id = dao.generateRecordID(bean.getMedicineName(), bean.getPurchaseDate());
			bean.setRecordId(id);
              return dao.createRecord(bean);
         } catch (InvalidInputException e) {
			return "INVALID INPUT";
		}
	}
   public PharmacyBean viewRecord(String name, Date date) {
		return dao.fetchRecord(name, date);
	}
   public List<PharmacyBean> viewAllRecords() {
		return dao.fetchAllRecords();
	}
}
