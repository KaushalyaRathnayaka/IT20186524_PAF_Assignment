<%@page import="com.Billing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery.min.js"></script>
<script src="Components/billing.js"></script>
<script src="Components/jquery.min.js"></script>
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<div class="row">
		<div class="col-lg-12">
			<div class="card m-b-32">
				<div class="card-body">

					<form id="formUser" name="formUser" method="post"
						action="index.jsp">

							<br><label>Billing Type</label>
							<select id="billingtype" name="billingtype" class="form-control form-control-sm">
							<option value="domestic">Domestic</option>
							<option value="business">Business</option>
							<option value="other">Other</option>
							</select>
							
							<br> 
							<label>Account Number</label><input id="account_number"
							name="account_number" type="text"
							class="form-control form-control-sm">
						
							<br><label>Total Price</label> <input id="totalprice" name="totalprice"
							type="number" class="form-control form-control-sm"> 
							
							<br><label>Remark</label> <input id="remark" name="remark"
							type="text" class="form-control form-control-sm"> 
							
							<br><label>Date</label> <input id="date"
							name="date" type="date"
							class="form-control form-control-sm">
							
							 <br>
							 <input id="btnSave" name="btnSave" type="button" value="Save"
							class="btn btn-primary"> <input type="hidden"
							id="hididSave" name="hididSave" value="">
					</form>
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					
					<div id="divUserGrid">
						<%
						Billing empObj = new Billing();
																							out.print(empObj.readBillings());
						%>
					</div>
				</div>
</div>
</div>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>