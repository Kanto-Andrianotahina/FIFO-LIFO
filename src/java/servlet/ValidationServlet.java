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
import mapping.AmountMvt;
import mapping.ArticlePrice;
import mapping.MethodArticle;
import mapping.Movement;
import mapping.ObjectClass;
import mapping.QuantityLeftArt;
import mapping.StockArticle;
import mapping.Validate;
import mapping.ValidateMovement;

/**
 *
 * @author Kanto
 */
public class ValidationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String dateValidation = request.getParameter("dateValidation");
        String idMovement = request.getParameter("idMovement");
        
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            
            ObjectClass objectClass = new ObjectClass();
           // check date movement and validation  
            Movement movement  = objectClass.getMovementNotValidById(idMovement);
            ValidateMovement tempVMvt = objectClass.getAllMovementInValidate(movement.getArticle(), movement.getStore());
            
            // maka prix an'ilay article        
            ArticlePrice articlePrice = new ArticlePrice();
            Date datePrice = new Date(movement.getDate().getTime());
            Double price = articlePrice.getLastPriceArticle(movement.getArticle(), movement.getStore(), datePrice);
            
            // instantiation validation    
            Validate validate = new Validate();
            validate.setValidate(validate.constructPK(con));
            validate.setMovement(idMovement);
            validate.setDate(dateValidation);
            System.out.println("id " +validate.getValidate());
            System.out.println("movement " +validate.getMovement());
            System.out.println("date " +validate.getDate());
            System.out.println("Prix article " +price);
            
            // instansiation qtyelft
            QuantityLeftArt qtyleft = new QuantityLeftArt();
            
            //  maka stock article 
            StockArticle stockArticle = new StockArticle();
            Double stock = stockArticle.getlastQuantityArticle(movement.getArticle(), movement.getStore());
            
            //  mampiditra montant aminy movement
            AmountMvt amountMvt = new AmountMvt();
            
            MethodArticle articleMethod = new MethodArticle();
            
            System.out.println(">>>>" +movement.getType());
            if(movement.getType().equals("TMT0001") ){
                System.out.println("------------------------- IN -----------------------------");
                String formatDate = null;
                if(tempVMvt == null){
                    validate.insertValidation(c, validate.getValidate(), validate.getMovement(), validate.getDate());
                    qtyleft.insertQuantityLeftArt(validate.getMovement(), movement.getQuantity());

                    
                    Double newQty = movement.getQuantity() + stock;
                    stockArticle.insertStock(c, movement.getArticle(), movement.getStore(), newQty, movement.getDate(), movement.getUnity());
                    System.out.println("new qty " +newQty);
                    System.out.println("stock " +stock);

                    
                    Double amountIn = price*movement.getQuantity();
                }
                if(tempVMvt != null){
                    if(tempVMvt.getDateMovement().before(validate.getDate())){   
                        // afaka validena   
                        System.out.println("Ato aminxay azzzz z zjz bvcjgd");
                        // check type movement 
                        validate.insertValidation(c, validate.getValidate(), validate.getMovement(), validate.getDate());
                        qtyleft.insertQuantityLeftArt(validate.getMovement(), movement.getQuantity());


                        Double newQty = movement.getQuantity() + stock;
                        stockArticle.insertStock(c, movement.getArticle(), movement.getStore(), newQty, movement.getDate(), movement.getUnity());
                        System.out.println("new qty " +newQty);
                        System.out.println("stock " +stock);
                        
                        Double amount = price * movement.getQuantity(); 
                        System.out.println("PRIX:" +amount);
                        amountMvt.insertAmountMvt(c, validate.getMovement(),movement.getQuantity(),amount);
                     }
                    if(tempVMvt.getDateMovement().after(validate.getDate())){
                        throw new IllegalStateException("Date anterieur");
                    }
                }
               
            }
            if(movement.getType().equals("TMT0002")){
                System.out.println("------------------------- OUT -----------------------------");
                if(tempVMvt == null){
                    validate.insertValidation(c, validate.getValidate(), validate.getMovement(), validate.getDate());
                    Double newQtyStock = stock - movement.getQuantity();
                    System.out.println("new qty :" +newQtyStock);
                    
//                    stockArticle.insertStock(c, movement.getArticle(), movement.getStore(), newQtyStock, movement.getDate(), movement.getUnity());
                }
                if(tempVMvt != null){
                    if(tempVMvt.getDateMovement().before(validate.getDate())){ 
                        validate.insertValidation(c, validate.getValidate(), validate.getMovement(), validate.getDate());
                        Double newQtyStock = stock - movement.getQuantity();
                        System.out.println("stock :" +stock);
                        System.out.println("new qty :" +newQtyStock);
                        
                        if(articleMethod.getMethodArticle(movement.getArticle()).equals("MTP0001")){
                            
                        }
                        
                    }
                    if(tempVMvt.getDateMovement().after(validate.getDate())){
                        throw new IllegalStateException("Date anterieur");
                    }
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("idMovement", idMovement);
            request.setAttribute("exception", e);  // Ajoutez cette ligne pour transmettre l'exception à la JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("MakeValidation.jsp");
            dispatcher.forward(request, response);
        }

    }

}
