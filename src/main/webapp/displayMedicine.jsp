<%@ page import="com.wipro.pharmacy.bean.PharmacyBean" %>

<%
PharmacyBean bean = (PharmacyBean)request.getAttribute("bean");

if(bean == null){
%>
<h3>No matching records exists! Please try again!</h3>
<%
}else{
%>

<table border="1">
<tr><th>ID</th><td><%= bean.getRecordId() %></td></tr>
<tr><th>Name</th><td><%= bean.getMedicineName() %></td></tr>
<tr><th>Supplier</th><td><%= bean.getSupplierName() %></td></tr>
<tr><th>Date</th><td><%= bean.getPurchaseDate() %></td></tr>
<tr><th>Quantity</th><td><%= bean.getQuantity() %></td></tr>
<tr><th>Price</th><td><%= bean.getPrice() %></td></tr>
<tr><th>Remarks</th><td><%= bean.getRemarks() %></td></tr>
</table>

<%
}
%>
