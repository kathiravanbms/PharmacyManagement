<%@ page import="java.util.*, com.wipro.pharmacy.bean.PharmacyBean" %>

<h2>All Medicine Records</h2>

<%
String message = (String)request.getAttribute("message");
if(message != null){
%>
<h3><%= message %></h3>
<%
}

List<PharmacyBean> list = (List<PharmacyBean>)request.getAttribute("list");

if(list != null){
%>

<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Supplier</th>
<th>Date</th>
<th>Quantity</th>
<th>Price</th>
<th>Remarks</th>
</tr>

<%
for(PharmacyBean b : list){
%>
<tr>
<td><%= b.getRecordId() %></td>
<td><%= b.getMedicineName() %></td>
<td><%= b.getSupplierName() %></td>
<td><%= b.getPurchaseDate() %></td>
<td><%= b.getQuantity() %></td>
<td><%= b.getPrice() %></td>
<td><%= b.getRemarks() %></td>
</tr>
<%
}
%>
</table>

<%
}
%>
