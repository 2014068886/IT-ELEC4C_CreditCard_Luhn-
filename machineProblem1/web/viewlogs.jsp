<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title> Audit Trail </title>
    <!-- CSS  -->
    <link href="min/plugin-min.css" type="text/css" rel="stylesheet">
    <link href="min/custom-min.css" type="text/css" rel="stylesheet" >

</head>
<body id="top" class="scrollspy">
<%@page import="java.sql.*,ust.session.utility.Security"%>

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
	
<% ResultSet log = (ResultSet) request.getAttribute("logRecord"); %>
<div id="intro" class="section scrollspy">
	<div class="container">
		<div class="row">
			<div class="col s12">
				<div class="center">
					<h2> Activity Logs </h2>
					<table border = "1">
						<tr>
							<th> # </th>
							<th> Event </th>
							<th> Event Timestamp </th>
						</tr>
						<% while(log.next()) { %>
							<tr align="center">
								<td><%=log.getInt("id") %></td>
								<td><%=Security.decrypt(log.getString("event")) %> </td>
								<td><%=Security.decrypt(log.getString("eventDateTime")) %></td>
							</tr>
						<% } %>
					</table> <br> <br>
					
					<form action='index.jsp'>
						<button class="btn waves-effect waves-light" type='submit'>
						<i class="mdi-navigation-chevron-left"></i> Go back</button> <br> <br> <br> <br>
					</form>
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
                <a class="white-text" href="#about">About</a>
                <a href="#intro">About</a>
                <a href="#order">Order</a>
                <a href="#team">Team</a>
                <a href="#contact">Contact</a>
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