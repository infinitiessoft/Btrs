<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<meta name="description" content="">
<meta name="author" content="" />
<link rel="shortcut icon" href="img/favicon.png" type="image/icon">
<link rel="icon" href="img/favicon.png" type="image/icon">
<link href="js/lib/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" />
<link href="js/lib/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<style type="text/css">
.table{
 	margin:5px;
    padding: 50px;
   }
.topcorner{
   position:absolute;
   top:20px;
   right:6px;
  }
  .btn{
	position:absolute;
	margin-top:30%;
	padding: 4px 8px;
    font: 14px sans-serif;
    text-decoration: none;
}
</style>
<body>
<!-- NAVBAR -->

<body>
    <div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-fixed-top">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Welcome : ${pageContext.request.userPrincipal.name} | </a>
              <div class="topcorner">
				<a href="<c:url value="/logout" />">Logout</a>
            </div>
          </div>
          </div>
        </nav>

      </div>
    </div>
    <!-- Message -->
<h2 class="sub-header">My Report</h2>
          <div class="table-responsive">
          <div class="table">
            <table class="table table-striped">
              <thead>
              <tr>  
    <td class="heading">Id</td>  
    <td class="heading">Created Date</td>  
    <td class="heading">Current Status</td>  
    <td class="heading">End Date</td>  
    <td class="heading">Reason</td>  
    <td class="heading">Edit</td>  
    <td class="heading">Delete</td>  
   </tr>  
   <c:forEach var="userReport" items="${userData}">  
    <tr>  
     <td>${userReport.Id}</td>  
     <td>${userReport.createdDate}</td>  
     <td>${userReport.currentStatus}</td>  
     <td>${userReport.endDate}</td>  
     <td>${userReport.reason}</td>  
     <td><a href="edit?id=${userReport.id}">Edit</a></td>  
     <td><a href="delete?id=${userReport.id}">Delete</a></td>  
    </tr>  
   </c:forEach>  
                
              </thead>
              <tbody>
              </tbody>
            </table>
            <div class="btn">
            <input action="action" type="button" value="Back"  onclick="history.go(-1);" class="btn btn-info" />
            </div>
            </div>
          </div>
      </body>
      </html>