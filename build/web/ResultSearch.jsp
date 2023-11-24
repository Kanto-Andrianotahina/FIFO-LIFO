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
        <link rel="stylesheet" href="./assets//css/index.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/fontawesome-5/css/all.min.css"> 
        <title>Result search</title>
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
                <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Article</th>
                                <th scope="col">Type movement</th>                        
                                <th scope="col">Quantity init</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Quantity final</th>
                                <th scope="col">PU</th>
                                <th scope="col">Amount</th>
                                <th scope="col">Store</th>
                                <th scope="col">Date</th>



                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row"><%= 1 %></th>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
            
            </div>
        </div>
    </body>
</html>
