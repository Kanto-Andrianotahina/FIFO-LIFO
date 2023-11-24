<%-- 
    Document   : Search
    Created on : Nov 21, 2023, 9:47:47 PM
    Author     : Kanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assets/css/index.css">
        <link rel="stylesheet" href="./assets/css/search.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/fontawesome-5/css/all.min.css"> 
        <title>Search</title>
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
                            <label for="category">Categorie:</label>
                            <select class="form-control" id="category" name="category">
                                <option value=""></option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="store">Magasin:</label>
                            <select class="form-control" id="store" name="store">
                                <option value=""></option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="dateDebut">Date Debut:</label>
                            <input type="datetime-local" class="form-control" id="dateDebut" name="dateDebut" required>
                        </div>
                        <div class="form-group">
                            <label for="dateFin">Date Fin:</label>
                            <input type="datetime-local" class="form-control" id="dateFin" name="dateFin" required>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Search" class="btn btn-primary" style="margin-top: 30px; margin-left:283px;">
                        </div>
                    </form>  
                </div>
                
            
            </div>
        </div>
    </body>
</html>
