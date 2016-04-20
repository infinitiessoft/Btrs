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
<link href="${pageContext.request.contextPath}/js/lib/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/js/lib/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath}/js/lib/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/lib/jquery/dist/jquery.min.js"></script>



</head>


<style>
    body    {
             background-image: url('http://www.icanread.asia/uploads/homepage/2013/09/22/112_o_background.jpg');             //Image Credit to www.icanread.asia
             background-repeat: no-repeat;
            }
            @import url(https://fonts.googleapis.com/css?family=Lobster);
/*panel heading*/
h1 {
    text-align:center;
    font-size: 28px;
    font-family: 'Lobster', cursive;
}
/*panel align center*/
.col-centered{
    float: none;
    margin: 0 auto;
    padding-top:5px;
}
/*property for the bootstrap panel*/
.panel  {
-webkit-box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.3);
-moz-box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.3);
box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.3);
max-height: 15;
}



/*property for the HTML input placeholder*/
input:focus::-webkit-input-placeholder { color:transparent; }
input:focus:-moz-placeholder { color:transparent; } /* FF 4-18 */
input:focus::-moz-placeholder { color:transparent; } /* FF 19+ */
input:focus::-moz-placeholder { color:transparent; } /* FF 19+ */
input:focus:-ms-input-placeholder { color:transparent; } /* IE 10+ */

/*property for reset button*/
button[type="reset"]    {
    background-color:transparent;
    border:1px solid black;
}

</style>
<script type="text/javascript">
function isCheckedByGenger(gender) {
	if(document.getElementByGender('gender').checked) {
	    $("${userData.firstName}").show();
	} else {
	    $("${userData.firstName}").hide();
	}
}
</script>
<body>
<div class="container">
            <div class="row">
                <div class="col-sm-6 col-centered">
                    <div class="panel panel-default">
                        <div class="panel-heading" >
                            <h1>User Details</h1>
                        </div>
                    <form action="" method="POST">
                    <div class="panel-body">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user black"></i></span>
                                <input type="text" name="InputName" placeholder="Username" class="form-control" autofocus="autofocus" value="${userData.username}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope black"></i></span>
                                <input type="email" name="InputEmail" placeholder="Email" class="form-control" value="${userData.email}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-pencil black"></i></span>
                                <input type="password" name="InputPassword" placeholder="Password" class="form-control" value="${userData.password}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-check black"></i></span>
                                <input type="password" name="InputPassword" placeholder="Confirm Password" class="form-control" value="${userData.password}">
                            </div>
                            </div>
                            <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user black"></i></span>
                                <input type="text" name="InputName" placeholder="First Name" class="form-control" value="${userData.firstName}">
                            </div>
                            </div>
                            <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user black"></i></span>
                                <input type="text" name="InputName" placeholder="Last Name" class="form-control" value="${userData.lastName}">
                            </div>
                            </div>
                            <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-briefcase black"></i></span>
                                <input type="text" name="InputName" placeholder="Job Title" class="form-control" value="${userData.jobTitle}">
                            </div>
                            </div>
                            <div class="form-group">
                            <div class="col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-king black"></i></span>
                                <input type="text" name="InputName" placeholder="Gender" class="form-control" value="${userData.gender}">
                            </div>
                            </div>
                            </div>
                             <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar black"></i></span>
                                <div class="input-group date" data-provide="datepicker">
   								 <input type="date" id="startDate" class="form-control" value="${userData.dateOfBirth}" > 
    								<div class="input-group-addon">
   								    </div>
								</div>
                            </div>
                            </div>
                            <div class="form-group">
				
				
                        <div class="">
                        <button type="submit" class="btn btn-info pull-right">Save <span class="glyphicon glyphicon-floppy-disk"></span></button>
                            <button type="reset" value="Reset" name="reset" class="btn">Reset <span class="glyphicon glyphicon-refresh"></span></button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
</body>