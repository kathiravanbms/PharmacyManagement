<!DOCTYPE html>
<html>
<head>
    <title>Add Medicine</title>
</head>
<body>

<h2>Add Medicine Record</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="newRecord">

Medicine Name:
<input type="text" name="medicineName"><br><br>

Supplier Name:
<input type="text" name="supplierName"><br><br>

Purchase Date:
<input type="date" name="purchaseDate"><br><br>

Quantity:
<input type="number" name="quantity"><br><br>

Price:
<input type="number" name="price"><br><br>

Remarks:
<input type="text" name="remarks"><br><br>

<input type="submit" value="Add">

</form>

</body>
</html>
