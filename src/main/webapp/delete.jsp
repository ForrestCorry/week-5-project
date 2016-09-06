<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.ravens.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ravens</title>
<link rel="stylesheet" type="text/css" href="player.css">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>



	<table>
		   <caption align="center">Roster</caption>
  			<tr>
    			<th>ID</th>
    			<th>Name</th> 
    			<th>Position</th>
    			<th>Number</th>
    		</tr>
    		<%DAO.readFromDB(); %>
							<% 
							Players readToJSP = new Players();
							
							for(int i = 0; i < DAO.ourPlayers.size(); i++) { 
							readToJSP = DAO.ourPlayers.get(i);
							%>
							<tr>
								<td><%=readToJSP.getPlayerID() %></td>
								<td><%=readToJSP.getName() %></td>
								<td><%=readToJSP.getPosition() %></td>
								<td><%=readToJSP.getNumber() %></td>
							</tr>
							<%} %>
							<% DAO.ourPlayers.clear();%>
  		
  		
  	</table>
	
	<form class="form-horizontal" action="deleteFromDB" method="post">
  		<div class="form-group">
    		<label for="species" class="col-sm-2 control-label">Player ID</label>
    			<div class="col-sm-10">
      				<input class="form-control" placeholder="Player ID" name="PlayerID">
   			 	</div>
 		</div>
 		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-10">
      			<button type="submit" class="btn btn-info" value="Submit">Submit</button>
    		</div>
  		</div>
  	</form>
 		
 	<div class="button" align="center">
	   <a class="btn btn-primary btn-lg" href="index.html" role="button">Home</a>
	</div>

</body>
</html>