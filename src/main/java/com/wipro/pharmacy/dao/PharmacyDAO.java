package com.wipro.pharmacy.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.wipro.pharmacy.bean.PharmacyBean;
import com.wipro.pharmacy.util.DBUtil;

public class PharmacyDAO {
   public String createRecord(PharmacyBean bean) {
		try (Connection con = DBUtil.getDBConnection()) {
			String sql = "INSERT INTO PHARMACY_TB VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bean.getRecordId());
			ps.setString(2, bean.getMedicineName());
			ps.setString(3, bean.getSupplierName());
            ps.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
            ps.setInt(5, bean.getQuantity());
			ps.setInt(6, bean.getPrice());
			ps.setString(7, bean.getRemarks());
            int n = ps.executeUpdate();
			return (n > 0) ? bean.getRecordId() : "FAIL";
           } catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
    public PharmacyBean fetchRecord(String name, Date date) {
           PharmacyBean bean = null;
          try (Connection con = DBUtil.getDBConnection()) {
            String sql = "SELECT * FROM PHARMACY_TB WHERE MEDICINENAME=? AND PURCHASE_DATE=?";
			PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
			ps.setDate(2, new java.sql.Date(date.getTime())); // ✅ convert
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
				bean = new PharmacyBean();
				bean.setRecordId(rs.getString("RECORDID"));
				bean.setMedicineName(rs.getString("MEDICINENAME"));
				bean.setSupplierName(rs.getString("SUPPLIERNAME"));
				bean.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
				bean.setQuantity(rs.getInt("QUANTITY"));
				bean.setPrice(rs.getInt("PRICE"));
				bean.setRemarks(rs.getString("REMARKS"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
    public boolean recordExists(String name, Date date) {
		return fetchRecord(name, date) != null;
	}
    public String generateRecordID(String name, Date date) {
         String id = "";
       try (Connection con = DBUtil.getDBConnection()) {
            Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT PHARMACY_SEQ.NEXTVAL FROM DUAL");
            rs.next();
			int seq = rs.getInt(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String datePart = sdf.format(date);
            id = datePart + name.substring(0, 2).toUpperCase() + seq;
        } catch (Exception e) {
			e.printStackTrace();
		}
        return id;
	}
    public List<PharmacyBean> fetchAllRecords() {
       List<PharmacyBean> list = new ArrayList<>();
           try (Connection con = DBUtil.getDBConnection()) {
            Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PHARMACY_TB");
            while (rs.next()) {
				PharmacyBean b = new PharmacyBean();
				b.setRecordId(rs.getString("RECORDID"));
				b.setMedicineName(rs.getString("MEDICINENAME"));
				b.setSupplierName(rs.getString("SUPPLIERNAME"));
				b.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
				b.setQuantity(rs.getInt("QUANTITY"));
				b.setPrice(rs.getInt("PRICE"));
				b.setRemarks(rs.getString("REMARKS"));
				list.add(b);
			}
      } catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
