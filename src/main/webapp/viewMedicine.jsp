<!DOCTYPE html>
<html>
<head>
<title>View Medicine</title>
</head>
<body>

<h2>View Medicine Record</h2>

<form action="MainServlet" method="post">

<input type="hidden" name="operation" value="viewRecord">

Medicine Name:
<input type="text" name="medicineName"><br><br>

Purchase Date:
<input type="date" name="purchaseDate"><br><br>

<input type="submit" value="View">

</form>

</body>
</html>
