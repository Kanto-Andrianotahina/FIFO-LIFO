/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kanto
 */
public class Main {
    public static void main(String[] args) {
        
        ObjectClass o = new ObjectClass();
        String id = o.getInfoArticle("ART0001").getId();
        System.out.println("id" +id);
        // Movement[] movement = o.getAllMovementsNotValid();
        // for (int i = 0; i < movement.length; i++) {
        //     System.out.println("id" + movement[i].getId() );
        //     System.out.println("qty" + movement[i].getQuantity());
        //     System.out.println("date"+ movement[i].getDate());
        // }
        
        
        
        
//        ObjectClass o = new ObjectClass();
//        Store[] stores = o.getAllStores();
//        for (Store s : stores) {
//            System.out.println("id " + s.getId());
//            System.out.println("store " + s.getStore());
//
//        }
//        
//        Article article = new Article();
//        String temp = article.getUnityArticle("ART0001").getUnity();
//        System.out.println("Article unity " +temp);


//Date datePrice = new Date(movement.getDate().getTime());  // maka prix ilay article par rapport amin ilay date mouvement
//                    Double price = articlePrice.getLastPriceArticle(movement.getArticle(),movement.getStore(),datePrice);
//                    System.out.println("Price article " +price); 
    }
}
