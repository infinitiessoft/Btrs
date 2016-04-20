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
<script src="js/lib/jquery/dist/jquery.min.js"></script>
<script src="js/lib/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<style type="text/css">
 .topcorner{
   color: grey;
   position:absolute;
   top:20px;
   right:6px;
  }
  .container{
   padding: 50px;
   }
@import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700); /* written by riliwan balogun http://www.facebook.com/riliwan.rabo*/ .board{ width: 75%; margin: 60px auto; height: 650px; background: #fff; /*box-shadow: 10px 10px #ccc,-10px 20px #ddd;*/ } .board .nav-tabs { position: relative; /* border-bottom: 0; */ /* width: 80%; */ margin: 40px auto; margin-bottom: 15px; box-sizing: border-box; } .board > div.board-inner{ background: #fafafa url(http://subtlepatterns.com/patterns/geometry2.png); background-size: 30%; } p.narrow{ width: 60%; margin: 10px auto; } .liner{ height: 2px; background: #ddd; position: absolute; width: 80%; margin: 0 auto; left: 0; right: 0; top: 50%; z-index: 1; } .nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus { color: #555555; cursor: default; /* background-color: #ffffff; */ border: 0; border-bottom-color: transparent; } span.round-tabs{ width: 70px; height: 70px; line-height: 70px; display: inline-block; border-radius: 100px; background: white; z-index: 2; position: absolute; left: 0; text-align: center; font-size: 25px; } span.round-tabs.one{ color: rgb(34, 194, 34);border: 2px solid rgb(34, 194, 34); } li.active span.round-tabs.one{ background: #fff !important; border: 2px solid #ddd; color: rgb(34, 194, 34); } span.round-tabs.two{ color: #febe29;border: 2px solid #febe29; } li.active span.round-tabs.two{ background: #fff !important; border: 2px solid #ddd; color: #febe29; } span.round-tabs.three{ color: #3e5e9a;border: 2px solid #3e5e9a; } li.active span.round-tabs.three{ background: #fff !important; border: 2px solid #ddd; color: #3e5e9a; } span.round-tabs.four{ color: #f1685e;border: 2px solid #f1685e; } li.active span.round-tabs.four{ background: #fff !important; border: 2px solid #ddd; color: #f1685e; } span.round-tabs.five{ color: #999;border: 2px solid #999; } li.active span.round-tabs.five{ background: #fff !important; border: 2px solid #ddd; color: #999; } .nav-tabs > li.active > a span.round-tabs{ background: #fafafa; } .nav-tabs > li { width: 20%; } /*li.active:before { content: " "; position: absolute; left: 45%; opacity:0; margin: 0 auto; bottom: -2px; border: 10px solid transparent; border-bottom-color: #fff; z-index: 1; transition:0.2s ease-in-out; }*/ li:after { content: " "; position: absolute; left: 45%; opacity:0; margin: 0 auto; bottom: 0px; border: 5px solid transparent; border-bottom-color: #ddd; transition:0.1s ease-in-out; } li.active:after { content: " "; position: absolute; left: 45%; opacity:1; margin: 0 auto; bottom: 0px; border: 10px solid transparent; border-bottom-color: #ddd; } .nav-tabs > li a{ width: 70px; height: 70px; margin: 20px auto; border-radius: 100%; padding: 0; } .nav-tabs > li a:hover{ background: transparent; } .tab-content{ } .tab-pane{ position: relative; padding-top: 50px; } .tab-content .head{ font-family: 'Roboto Condensed', sans-serif; font-size: 25px; text-transform: uppercase; padding-bottom: 10px; } .btn-outline-rounded{ padding: 10px 40px; margin: 20px 0; border: 2px solid transparent; border-radius: 25px; } .btn.green{ background-color:#5cb85c; /*border: 2px solid #5cb85c;*/ color: #ffffff; } @media( max-width : 585px ){ .board { width: 90%; height:auto !important; } span.round-tabs { font-size:16px; width: 50px; height: 50px; line-height: 50px; } .tab-content .head{ font-size:20px; } .nav-tabs > li a { width: 50px; height: 50px; line-height:50px; } li.active:after { content: " "; position: absolute; left: 35%; } .btn-outline-rounded { padding:20px 20px; } }
.row{
	margin:10px;
}
.file{
margin: 50px;}
</style>

<script type="text/javascript">
$(function(){
    $('a[title]').tooltip();

    $('.btn-submit').on('click', function(e) {
		
        var formname = $(this).attr('name'); 
        var tabname = $(this).attr('href');
        
        if ($('#' + formname)[0].checkValidity()) {
            e.preventDefault();
            $('ul.nav li a[href="' + tabname + '"]').parent().removeClass('disabled');
            $('ul.nav li a[href="' + tabname + '"]').trigger('click');
        }

    });

    $('ul.nav li').on('click', function(e) {
        if ($(this).hasClass('disabled')) {
            e.preventDefault();
            return false;
        }
    });
});

</script>
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
<section style="background:#efefe9;">

    <div class="container"> 
        <div class="row"> 
        <div class="board"> <!-- <h2>Welcome to IGHALO!<sup>™</sup></h2>--> 
            <div class="board-inner"> 
                <ul class="nav nav-tabs" id="myTab">
                    <div class="liner"></div>
                    <li class="active"> <a href="#home" data-toggle="tab" title="welcome"> <span class="round-tabs one"> <i class="glyphicon glyphicon-calendar"></i> </span> </a></li>
                    <li class="disabled"><a href="#profile" data-toggle="tab" title="profile"> <span class="round-tabs two"> <i class="glyphicon glyphicon-pencil"></i> </span> </a> </li>
                    <li class="disabled"><a href="#messages" data-toggle="tab" title="expense"> <span class="round-tabs three"> <i class="glyphicon glyphicon-usd"></i> </span> </a> 
                    <li class="disabled"><a href="#settings" data-toggle="tab" title="photo"> <span class="round-tabs four"> <i class="glyphicon glyphicon-picture"></i> </span> </a>
                    <li class="disabled"><a href="#doner" data-toggle="tab" title="completed"> <span class="round-tabs five"> <i class="glyphicon glyphicon-ok"></i> </span> </a> </li>
                </ul>
            </div>
            
            <div class="tab-content">
                <div class="tab-pane fade in active" id="home">
                    <h3 class="head text-center">Welcome to Business Trip Please Select Dates<sup>™</sup> <span style="color:#f48260;">♥</span></h3>
					<div class="col-sm-12">
					<div class="col-sm-6 form-group"> 
					 <label for="startDate" class="col-sm-8 control-label">Start Date</label> 
						<div class="col-sm-8"> 
						<input type="date" id="startDate" class="form-control"> 
						</div> 
					</div>
			<div class="col-sm-6 form-group"> 
				<label for="endDate" class="col-sm-8 control-label">End Date</label> 
					<div class="col-sm-8"> 
					<input type="date" id="endDate" class="form-control"> 
					</div> 
			</div>   
			 </div>             
                    <form class="form-horizontal text-center" id="home_form" name="home_form" role="form">
                          <button  type="submit" href="#profile" name="home_form" class="btn-submit btn btn-success btn-outline-rounded green" style=" position: initial;top:120px;right:0px;"> Next tab <span  class="glyphicon glyphicon-send">
                            </span></button>
                    </form>
                </div>
                <div class="tab-pane fade" id="profile">
                    <h4 class="head text-center">Create a Record<sup>™</sup></h4>
						<div class="col-sm-4 form-group">
								<label for="leaveRecord" class="col-sm-9 control-label">Leave Record</label>
									<select id="leave record" class="form-control"> 
										<option>Annual</option> 
										<option>Personal</option>
										<option>Other</option> 
									</select> 
							</div>
						
						
						<div class="row">
							<div class="col-sm-4 form-group">
								<label for="route" class="col-sm-4 control-label">Route</label>
									<input type="text" placeholder="Enter City Name Here.." class="form-control">
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label for="to" class="col-sm-4 control-label">To</label>
								<select id="to" class="form-control"> 
										<option>ganapari06@gmail.com</option> 
										<option>admin@gmail.com</option>
							</select>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label for="comment" class="col-sm-4 control-label">Reason</label>
									<textarea placeholder="Enter Comment Here.." rows="3" class="form-control"></textarea>
							</div>
						</div> 
						<form class="form-horizontal text-center" id="profile_form" name="profile_form" role="form">
                        <fieldset>
                            <button type="submit" href="#messages" name="profile_form" class="btn-submit btn btn-success btn-outline-rounded green" > Next tab <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></button> 
                        </fieldset> 
                    </form>
                </div>
                <div class="tab-pane fade" id="messages">
                    <h4 class="head text-center">Expense Record</h4>
                   <div class="col-sm-4 form-group">
								<label for="expenseCategory" class="col-sm-9 control-label">Expense Category</label>
									<select id="expense category" multiple class="form-control"> 
										<option>Accomodation</option> 
										<option>Meal</option>
										<option>Transport</option> 
										<option>Other</option>
									</select> 
							</div>
						<div class="row">
							<div class="col-sm-4 form-group">
								<label for="expenseType" class="col-sm-9 control-label" style="float: right" >Expense Type</label>
									<select id="expense type" multiple class = "form-control" style="float: right">
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
					</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<label for="comment" class="col-sm-4 control-label">Comment</label>
									<textarea placeholder="Enter Comment Here.." rows="3" class="form-control"></textarea>
							</div>
						</div> 
						<form class="form-horizontal text-center" id="messages_form" name="messages_form" role="form">                 
                        <fieldset>
                            <button type="submit" href="#settings" name="messages_form" class="btn-submit btn btn-success btn-outline-rounded green" style="float: right; width: 150px;margin-left:20px"> Next tab <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></button>
                        </fieldset>
                    </form>
                </div> 
                <div class="tab-pane fade" id="settings">
                    <h4 class="head text-center">Photo Record</h4> 
                    <!-- image-preview-input -->
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
							<form class="form-horizontal text-center" id="settings_form" name="settings_form" role="form"> 
                				<fieldset> 
                					<button type="submit" href="#doner" name="settings_form" class="btn-submit btn btn-success btn-outline-rounded green"> Next tab <span style="margin-left:10px;" class="glyphicon glyphicon-send"></span></button> 
                				</fieldset> 
               			 	</form> 
                		</div> 
                      <div class="tab-pane fade" id="doner">
							<h2 class="head text-center">Thanks for Reporting! <span style="color:#f48260;">♥</span></h2>
					  </div>
                <div class="clearfix">
                </div> 
              </div> 
            </div> 
         </div>    
     </div> 
  </section>
 </body>
 </html>
    