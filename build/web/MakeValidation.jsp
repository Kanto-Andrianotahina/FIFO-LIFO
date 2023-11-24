<%-- 
    Document   : Search
    Created on : Nov 21, 2023, 9:47:47 PM
    Author     : Kanto
--%>
<%@page import="mapping.Unity"%>
<%@page import="mapping.Article"%>
<%@page import="mapping.Movement"%>
<%@page import="mapping.ObjectClass"%>
<%
    ObjectClass o = new ObjectClass();
    String idMovement = request.getParameter("idMovement");
    Movement movementNotValidate = o.getMovementNotValidById(idMovement);
    
    Exception except = (Exception) request.getAttribute("exception");
    String errorMessage =null;
    if (except != null) {
        errorMessage = except.getMessage();
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/makevalidation.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/fontawesome-5/css/all.min.css"> 
        <title>Make validation</title>
    </head>
    <body>
        <div class="container">
            <div class="nav">
            <div class="nav-list">
                <ul>
                    <li>
                        <a href="Index.jsp"><i class="fas fa-home"></i></a>
                    </li>
                    <li>
                        <a href="Search.jsp"><i class="fas fa-search"></i></a>
                    </li>
                    <li>
                        <a href="ListAttente.jsp"><i class="fas fa-list-alt "></i></a>
                    </li>
                    <li>
                        <a href="Validation.jsp"><i class="fas fa-check"></i></a>
                    </li>
                    <li>
                        <a href="Movement.jsp"><i class="fas fa-plus"></i></a>
                    </li>
                </ul>
            </div>
            </div>
            <div class="box-containt">
                <div class="box">
                </div>
                <div class="search">
                    <form action="./ValidationServlet" method="post">
                        <div class="form-group">
                            <% String idArticle = movementNotValidate.getArticle(); %>
                            <label for="article">Article: <%= o.getInfoArticle(idArticle).getArticle() %></label>
                        </div>
                        <div class="form-group">
                            <label for="movement">Mouvement: <%= movementNotValidate.getId() %> </label>
                            <input type="hidden" name="idMovement" value="<%= idMovement %>">
                        </div>
                        <div class="form-group">
                            <%String idType = movementNotValidate.getType();  %>
                            <label for="type">Type de mouvement: <%= o.getInfoTypeMovement(idType).getType() %></label>
                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantity:  <%= movementNotValidate.getQuantity() %></label>
                        </div>
                        <div class="form-group">
                            <% String idUnity = movementNotValidate.getUnity(); %>
                            <label for="unity">Unity:  <%= o.getInfoUnity(idUnity).getUnity() %></label>
                        </div>
                        <div class="form-group">
                            <% String idStore = movementNotValidate.getStore();%>
                            <label for="Store">Store:  <%=o.getInfoStore(idStore).getStore()  %></label>
                        </div>
                        <div class="form-group">
                            <label for="datemvt">Date mouvement:  <%= movementNotValidate.getDate() %></label>
                        </div>
                        <div class="form-group">
                            <label for="dateValidation">Date validation:</label>
                            <input type="datetime-local" class="form-control" id="dateValidation" name="dateValidation" required>
                        </div>
                        
                        <div class="form-group">
                            <input type="submit" value="Valider" class="btn btn-primary" style="margin-top: 30px; margin-left:283px;">
                        </div>
                    </form>  
                </div>
                <% if (errorMessage != null) { %>
                <div class="alert alert-danger" role="alert" style="width: 401px; margin: auto; margin-top: 9px; margin-left: 340px;">
                    <%= errorMessage %>
                </div>
                <% } %>
            
            </div>
        </div>
    </body>
</html>
