/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import generalisation.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mapping.Article;
import mapping.ArticlePrice;
import mapping.Conversion;
import mapping.Movement;
import mapping.StockArticle;
import mapping.Unity;

/**
 *
 * @author Kanto
 */
public class MovementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String article = request.getParameter("article");
        String typeMovement = request.getParameter("movement");
        String quantity = request.getParameter("quantity");
        String unity = request.getParameter("unity");
        String store = request.getParameter("store");
        String date = request.getParameter("date");
        
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            
            Movement movement = new Movement();
            movement.setId(movement.constructPK(con));
            movement.setArticle(article);
            movement.setType(typeMovement);
            movement.setQuantity(quantity);
            movement.setUnity(unity);
            movement.setStore(store);
            movement.setDate(date);
            
            System.out.println("id " +movement.getId());
            System.out.println("Article " +movement.getArticle());
            System.out.println("Type " +movement.getType());
            System.out.println("Qty " +movement.getQuantity());
            System.out.println("Unity " +movement.getUnity());
            System.out.println("Store " +movement.getStore());
            System.out.println("Date " +movement.getDate());
            
            Article a = new Article();
            String unityArticle = a.getInfoArticle(movement.getArticle()).getUnity();  // maka oe inona ny unite de base an'ilay article
//            System.out.println("Unity Article " +unityArticle);
            
            ArticlePrice articlePrice = new ArticlePrice();
            Conversion conversion = new Conversion();
            StockArticle stockArticle = new StockArticle();
            
            
//----------------------------------- IN --------------------------------------------\\
            if(typeMovement.equals("TMT0001")){
                System.out.println("------------------- IN --------------------------");
                if(unity.contentEquals(unityArticle)){  //jerena oe meme unite ve ilay article sy ilay action nataony
                    movement.insertMovement(c, movement.getId(), movement.getType(),movement.getArticle(), movement.getQuantity(), movement.getUnity(),movement.getStore(), movement.getDate());   
                }else{
                    Double qtyConverted = conversion.makeConversion(movement.getUnity(), unityArticle)*movement.getQuantity();
                    movement.insertMovement(c, movement.getId(), movement.getType(),movement.getArticle(), qtyConverted, movement.getUnity(),movement.getStore(), movement.getDate());
                }
            }

//----------------------------------- OUT --------------------------------------------\\
            if(typeMovement.equals("TMT0002")){
                System.out.println("----------------------- OUT -------------------------");
                double qtyInStock  = stockArticle.getlastQuantityArticle(movement.getArticle(), movement.getStore());
                if(unity.contentEquals(unityArticle)){
                    if(qtyInStock <  movement.getQuantity()){
                        throw new IllegalArgumentException("Quantite insuffisante");
                    }
                    if(movement.getQuantity()<= qtyInStock){
                        movement.insertMovement(c, movement.getId(), movement.getType(), movement.getArticle(), movement.getQuantity(), movement.getUnity(), movement.getStore(), movement.getDate());
                    }
                }else{
                    Double qtyConverted = conversion.makeConversion(movement.getUnity(), unityArticle)*movement.getQuantity();
                    if(qtyInStock <  qtyConverted){
                        throw new IllegalArgumentException("Quantite insuffisante");
                    }
                    if(qtyConverted<= qtyInStock){
                        movement.insertMovement(c, movement.getId(), movement.getType(), movement.getArticle(), qtyConverted, movement.getUnity(), movement.getStore(), movement.getDate());
                    }
                }
                
                
            }

        }
        catch(Exception e){
            e.printStackTrace();
            request.setAttribute("exception", e);

            // Rediriger vers la page JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("Movement.jsp");
            dispatcher.forward(request, response);
        }
        
    } 

}
