package com.wipro.pharmacy.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.wipro.pharmacy.bean.PharmacyBean;
import com.wipro.pharmacy.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	Administrator admin = new Administrator();

	public String addRecord(HttpServletRequest request) {
		try {
			PharmacyBean bean = new PharmacyBean();
			bean.setMedicineName(request.getParameter("medicineName"));
			bean.setSupplierName(request.getParameter("supplierName"));
			String dateStr = request.getParameter("purchaseDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateStr);
			bean.setPurchaseDate(date);
			bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			bean.setPrice(Integer.parseInt(request.getParameter("price")));
			bean.setRemarks(request.getParameter("remarks"));
			return admin.addRecord(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}
   public PharmacyBean viewRecord(HttpServletRequest request) {
		try {
			String medicineName = request.getParameter("medicineName");
			String dateStr = request.getParameter("purchaseDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dateStr);
			return admin.viewRecord(medicineName, date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   public List<PharmacyBean> viewAllRecords(HttpServletRequest request) {
		return admin.viewAllRecords();
	}
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("newRecord")) {

			String result = addRecord(request);

			if (result.equals("FAIL") || result.equals("INVALID INPUT")) {
				response.sendRedirect("error.html");
			} else {
				response.sendRedirect("success.html");
			}
		} else if (operation.equals("viewRecord")) {

			PharmacyBean bean = viewRecord(request);

			if (bean == null) {
				request.setAttribute("message", "No matching records exists! Please try again!");
			} else {
				request.setAttribute("bean", bean);
			}

			RequestDispatcher rd = request.getRequestDispatcher("displayMedicine.jsp");
			rd.forward(request, response);
		} else if (operation.equals("viewAllRecords")) {

			List<PharmacyBean> list = viewAllRecords(request);

			if (list == null || list.isEmpty()) {
				request.setAttribute("message", "No records available!");
			} else {
				request.setAttribute("list", list);
			}

			RequestDispatcher rd = request.getRequestDispatcher("displayAllMedicines.jsp");
			rd.forward(request, response);
		}
	}
}
