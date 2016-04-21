<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html lang="en">
    <head>
    <meta charset="utf-8">
    <title>Create BTRS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style type="text/css">
.topcorner{
   color: grey;
   position:absolute;
   top:20px;
   right:6px;
  }
  .container{
   padding: 30px;
   }
body {
	margin-top:40px;
}
.stepwizard-step p {
	margin-top: 10px;
}
.stepwizard-row {
	display: table-row;
}
.stepwizard {
	display: table;
	width: 50%;
	position: relative;
}
.stepwizard-step button[disabled] {
	opacity: 1 !important;
	filter: alpha(opacity=100) !important;
}
.stepwizard-row:before {
	top: 25px;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 100%;
	height: 5px;
	background-color: #ccc;
	z-order: 0;
}
.stepwizard-step {
	display: table-cell;
	text-align: center;
	position: relative;
}
.btn-circle {
	width: 60px;
	height: 60px;
	text-align: center;
	padding: 7px 7px;
	font-size: 25px;
	line-height: 1.428571429;
	border-radius: 15px;
	top:25px;
 	display: inline-block; 
 	border-radius: 100px; 
 	background: white; 
 	z-index: 2; 
 	left: 0; 
 } 
.btn-circle.one{ color: rgb(34, 194, 34);border: 2px solid rgb(34, 194, 34); } 
.btn-circle.two{ color: #febe29;border: 2px solid #febe29; } 
.btn-circle.three{ color: #3e5e9a;border: 2px solid #3e5e9a; } 
.btn-circle.four{ color: #f1685e;border: 2px solid #f1685e; } 
.btn-circle.five{ color: #999;border: 2px solid #999; } 
h3 {
    text-align: center;
}

</style>
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    </head>
    
    
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
    <!-- Circular tabs -->
<div class="container">

      <div class="stepwizard col-md-offset-3">
    <div class="stepwizard-row setup-panel">
          <div class="stepwizard-step">
        <a href="#step-1" type="button" class="btn btn-primary btn-circle one" ><span class="glyphicon glyphicon-calendar"></span> </a>
      </div>
          <div class="stepwizard-step">
        <a href="#step-2" type="button" class="btn btn-default btn-circle two" disabled="disabled"><span class="glyphicon glyphicon-pencil"></span> </a>
      </div>
          <div class="stepwizard-step">
        <a href="#step-3" type="button" class="btn btn-default btn-circle three" disabled="disabled"><span  class="glyphicon glyphicon-usd"></span> </a> 
      </div>
      <div class="stepwizard-step">
        <a href="#step-4" type="button" class="btn btn-default btn-circle four" disabled="disabled"><span  class="glyphicon glyphicon-picture"></span> </a>
        </div>
        <div class="stepwizard-step">
        <a href="#step-5" type="button" class="btn btn-default btn-circle five" disabled="disabled"><span  class="glyphicon glyphicon-ok"></span> </a>
        </div>
        </div>
  </div>
      <form role="form" action="${pageContext.request.contextPath}/create/report/add/${pageContext.request.userPrincipal.name}" method="post">
    <div class="row setup-content" id="step-1">
          <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-20">
         <h3> Welcome to Business Trip Please Select Dates<sup>™</sup> <span style="color:#f48260;">♥</span></h3>
         <div class="col-md-30">
         <div class="form-group"> 
					 <label class="col-sm-8 control-label" for="startDate" >Start Date</label>
					 <div class="col-sm-5">  
						<input type="date"  id="startDate" class="form-control"> 
						</div> 
					</div><!--required="required"  -->
					<div class="form-group"> 
				<label for="endDate" class="col-sm-8 control-label">End Date</label> 
				<div class="col-sm-5">
					<input  type="date"  id="endDate" class="form-control"> 
					</div> 
					</div>
					</div>
              <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next<span style="margin-left:10px;" class="glyphicon glyphicon-forward"></span></button>
            </div>
      </div>
        </div>
    <div class="row setup-content" id="step-2">
          <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
              <h3>Create a Record<sup>™</sup></h3>
							<div class="col-sm-7 form-group">
								<label for="to" class="control-label">To</label>
								<select id="to" class="form-control"> 
										<option>ganapari06@gmail.com</option> 
										<option>admin@gmail.com</option>
							</select>
							</div>
						<div class="col-sm-7 form-group">
								<label for="leaveRecord" class="control-label">Leave Record</label>
									<select id="leave record" class="form-control"> 
										<option>Annual</option> 
										<option>Personal</option>
										<option>Other</option> 
									</select> 
							</div>
              <div class="col-sm-8 form-group">
            <label class="control-label">Route</label>
            <input maxlength="200" type="text" class="form-control" placeholder="Enter City Name..." />
          </div>
              <div class="col-sm-8 form-group">
            <label class="control-label">Reason</label>
			<textarea placeholder="Enter Reason Here.." rows="3" class="form-control"></textarea>
          </div>
          </div>
              <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next<span style="margin-left:10px;" class="glyphicon glyphicon-forward"></span></button>
      </div>
        </div>
        <div class="row setup-content" id="step-3">
          <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
              <h3>Expense Record<sup>™</sup></h3>
							<div class="col-sm-7 form-group">
								<label for="expenseCategory" class="control-label">Expense Category</label>
								<select id="expenseCategory"  multiple class="form-control"> 
										<option>Accomodation</option> 
										<option>Meal</option>
										<option>Transport</option> 
										<option>Other</option>
							</select>
							</div>
						<div class="col-sm-7 form-group">
								<label for="expenseType" class="control-label">Expense Type</label>
									<select id="expenseType"  multiple class="form-control"> 
										<option>Accomodation</option> 
										<option>Meal</option>
										<option>Bus Ticket</option>
										<option>Taxi</option>
										<option>High Speed Train</option>
										<option>Mass Rapid Transit</option>
										<option>Train</option>
										<option>Other</option> 
									</select> 
							</div>
              <div class="col-sm-8 form-group">
            <label class="control-label">Comment</label>
			<textarea placeholder="Enter Comment Here.." rows="3" class="form-control"></textarea>
          </div>
          </div>
              <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next<span style="margin-left:10px;" class="glyphicon glyphicon-forward"></span></button>
      </div>
        </div>
       <div class="row setup-content" id="step-4">
          <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12"> 
         <h3>Upload Photo<sup>™</sup></h3>
          <div class="file">
                    	<form id="myForm" method="post" enctype="multipart/form-data">
						Files: <input type="file" id="files" name="files" multiple style="margin-right:30px;"><br/>
						<div id="selectedFiles"></div>
						</form>
					</div>
    					<script>
    							var selDiv = "";
    							document.addEventListener("DOMContentLoaded", init, false);
    							function init() {
      								document.querySelector('#files').addEventListener('change', handleFileSelect, false);
        							selDiv = document.querySelector("#selectedFiles");
    							}
    								function handleFileSelect(e) {
        								if(!e.target.files) return;
        								selDiv.innerHTML = "";
        								var files = e.target.files;
       			 						for(var i=0; i<files.length; i++) {
            								var f = files[i];
            								selDiv.innerHTML += f.name + "<br/>";
										}
									}
    					</script>
           <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next<span style="margin-left:10px;" class="glyphicon glyphicon-forward"></span></button>
      </div>
        </div>
        </div>
    <div class="row setup-content" id="step-5">
          <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
              <h3> Thanks for Reporting! <span style="color:#f48260;">♥</span></h3>
              <button class="btn btn-success btn-lg" style="float: center; width: 160px;margin-left:100px" type="submit">Submit<span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></button>
            </div>
      </div>
        </div>
  </form>
   </div>


<script type="text/javascript">
  $(document).ready(function () {
  var navListItems = $('div.setup-panel div a'),
		  allWells = $('.setup-content'),
		  allNextBtn = $('.nextBtn');

  allWells.hide();

  navListItems.click(function (e) {
	  e.preventDefault();
	  var $target = $($(this).attr('href')),
			  $item = $(this);

	  if (!$item.hasClass('disabled')) {
		  navListItems.removeClass('btn-primary').addClass('btn-default');
		  $item.addClass('btn-primary');
		  allWells.hide();
		  $target.show();
		  $target.find('input:eq(0)').focus();
	  }
  });

  allNextBtn.click(function(){
	  var curStep = $(this).closest(".setup-content"),
		  curStepBtn = curStep.attr("id"),
		  nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
		  curInputs = curStep.find("input[type='text'],input[type='url'],textarea[textarea],input[type='date']"),
		  isValid = true;

	  $(".form-group").removeClass("has-error");
	  for(var i=0; i<curInputs.length; i++){
		  if (!curInputs[i].validity.valid){
			  isValid = false;
			  $(curInputs[i]).closest(".form-group").addClass("has-error");
		  }
	  }

	  if (isValid)
		  nextStepWizard.removeAttr('disabled').trigger('click');
  });

  $('div.setup-panel div a.btn-primary').trigger('click');
});
  </script>
</body>
</html>
