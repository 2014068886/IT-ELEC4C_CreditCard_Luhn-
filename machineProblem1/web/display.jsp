<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <meta name="theme-color" content="#2196F3">
    <title>Jumbo Fuel</title>

    <!-- CSS  -->
    <link href="min/plugin-min.css" type="text/css" rel="stylesheet">
    <link href="min/custom-min.css" type="text/css" rel="stylesheet" >
</head>
<body id="top" class="scrollspy">

<!-- Pre Loader -->
<div id="loader-wrapper">
    <div id="loader"></div>
 
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
 
</div>

<!--Navigation-->
 <div class="navbar-fixed">
    <nav id="nav_f" class="default_color" role="navigation">
        <div class="container">
            <div class="nav-wrapper">
            <a href="#" id="logo-container" class="brand-logo"><i class="mdi-maps-local-gas-station"></i></a>
                <ul class="right hide-on-med-and-down">
                    <li><a href="#intro">About</a></li>
                    <li><a href="#order">Order</a></li>
                    <li><a href="#team">Team</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
                <ul id="nav-mobile" class="side-nav">
                    <li><a href="#intro">About</a></li>
                    <li><a href="#order">Order</a></li>
                    <li><a href="#team">Team</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="mdi-navigation-menu"></i></a>
            </div>
        </div>
    </nav>
</div>


<!--Parallax-->
<div class="parallax-container">
    <div class="parallax"><img src="img/gasbg.jpg"></div>
</div>

<!--Intro and service-->
<div class="section scrollspy" id="order">
    <div class="container">
        <h2 class="header text_b">Ordered Successfully!</h2>
        <div class="row">
            <div class="col s12">
                <div class="card">
                   
                    <div class="card-content">
                        <span class="card-title activator grey-text text-darken-4"> <i class="mdi-navigation-more-vert right"></i></span>
                        <p><a href="#">Order Form</a></p>
                       	<div class="col s12">
	                       	<div class="row">
	                       	<p>Customer Name: <b>${customer.firstName} ${customer.lastName} </b></p>
							<p>Fuel Type: <b>${customer.gasoline}</b></p>
							<p>Price per Liter: <b>${customer.price}</b> </p>
							<p>Purchased Amount: <b>${customer.purchaseAmount}</b></p>
							<p>VAT: <b>${customer.priceVAT }</b></p>
							<p>Total Amount: <b>${customer.totalAmount}</b></p>
							<p>Credit Card: <b>${customer.last4Digits} successfully charged</b> </p> 
							
								<form action='index.jsp'>
									<button class="btn waves-effect waves-light" type='submit'>
									<i class="mdi-navigation-chevron-left"></i> Go back</button>
								</form> <br>
								<form action='viewlogs.action' method='post'>
									<button class="btn waves-effect waves-light" type='submit'>View Logs</button>
								</form>
								
							<% getServletContext().log("display.jsp accessed");%>
	                       	</div>
                       	</div>	
                    </div>              
                </div>
            </div>            
        </div>
    </div>
</div>
<!--Footer-->
<footer id="contact" class="page-footer default_color scrollspy">
    <div class="container">  
        <div class="row">
            <div class="col l6 s12">
                <form class="col s12" action="contact.php" method="post">
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="mdi-action-account-circle prefix white-text"></i>
                            <input id="icon_prefix" name="name" type="text" class="validate white-text">
                            <label for="icon_prefix" class="white-text">First Name</label>
                        </div>
                        <div class="input-field col s6">
                            <i class="mdi-communication-email prefix white-text"></i>
                            <input id="icon_email" name="email" type="email" class="validate white-text">
                            <label for="icon_email" class="white-text">Email-id</label>
                        </div>
                        <div class="input-field col s12">
                            <i class="mdi-editor-mode-edit prefix white-text"></i>
                            <textarea id="icon_prefix2" name="message" class="materialize-textarea white-text"></textarea>
                            <label for="icon_prefix2" class="white-text">Message</label>
                        </div>
                        <div class="col offset-s7 s5">
                            <button class="btn waves-effect waves-light red darken-1" type="submit">Submit
                                <i class="mdi-content-send right white-text"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            
            <div class="col l3 s12">
                <h5 class="white-text"><i class="mdi-maps-local-gas-station"></i>Jumbo Fuel </h5>
                <a class="white-text" href="#about">About</a><br>
                <a href="#intro">About</a><br>
                <a href="#order">Order</a><br>
                <a href="#team">Team</a><br>
                <a href="#contact">Contact</a><br>
            </div>
        </div>
    </div>
    <div class="footer-copyright default_color">
        <div class="container">
           Made by Bayocot & Hernandez. Thanks to <a class="white-text" href="http://materializecss.com/">materializecss</a>
        	&copy; 2018 
        </div>
    </div>
</footer>


    <!--  Scripts-->
    <script src="min/plugin-min.js"></script>
    <script src="min/custom-min.js"></script>

    </body>
</html>
