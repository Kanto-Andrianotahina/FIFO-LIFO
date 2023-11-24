<%-- 
    Document   : Search
    Created on : Nov 21, 2023, 9:47:47 PM
    Author     : Kanto
--%>

<%@page import="mapping.Unity"%>
<%@page import="mapping.TypeMovement"%>
<%@page import="mapping.Article"%>
<%@page import="mapping.ObjectClass"%>
<%@page import="mapping.Store"%>
<%
    ObjectClass o = new ObjectClass();
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
        <link rel="stylesheet" href="./assets/css/index.css">
        <link rel="stylesheet" href="./assets/css/search.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/fontawesome-5/css/all.min.css"> 
        <title> Mouvement </title>
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
                    <form action="./MovementServlet" method="post">
                        <div class="form-group">
                            <label for="article">Article:</label>
                            <select class="form-control" id="article" name="article">
                                <% 
                                    Article[] articles = o.getAllArticles();
                                    for(Article a : articles) { %>
                                    <option value="<%= a.getId() %>"><%= a.getArticle() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="movement">Mouvement</label>
                            <select class="form-control" id="movement" name="movement">
                                <%
                                    TypeMovement[] types = o.getAllTypesMovement();
                                    for(TypeMovement t: types){ %>
                                    <option value="<%= t.getId() %>"><%= t.getType()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="quantity">Quantite:</label>
                            <input type="text" class="form-control" id="quantity" name="quantity" required>
                        </div>
                        <div class="form-group">
                            <label for="unity">Unite:</label>
                            <select class="form-control" id="unity" name="unity">
                                <% 
                                    Unity[] unities = o.getAllUnities();
                                    for(Unity u : unities) { %>
                                    <option value="<%= u.getId() %>"><%= u.getUnity() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="store">Magasin:</label>
                            <select class="form-control" id="store" name="store">
                                <% 
                                    Store[] store = o.getAllStores();
                                    for(Store s : store) { %>
                                    <option value="<%= s.getId() %>"><%= s.getStore() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="date">Date</label>
                            <input type="datetime-local" class="form-control" id="date" name="date" required>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Search" class="btn btn-primary" style="margin-top: 30px; margin-left:283px;">
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
