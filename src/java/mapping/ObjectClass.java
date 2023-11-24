/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

/**
 *
 * @author Kanto
 */
public class ObjectClass {
    Store store;
    Article article;
    TypeMovement type;
    Unity unity;
    Movement movement;
    ValidateMovement validateMovement;
    
    
//    get a list of stores
    public Store[] getAllStores(){
        store = new Store();
        return store.getAllStores();
    }
    
//    get a list of articles 
    public Article[] getAllArticles(){
        article = new Article();
        return article.getAllArticle();
    }
    
//    get a list of type of movement 
    public TypeMovement[] getAllTypesMovement(){
        type = new TypeMovement();
        return type.getAllTypesMovement();
    } 
    
//    get a list of unity
    public Unity[] getAllUnities(){
        unity = new Unity();
        return unity.getAllUnities();
    }
    
//    get a info of an article 
    public Article getInfoArticle(String idArticle){
        article = new Article();
        return article.getInfoArticle(idArticle);
    }
    
//     get list of movement non validate 
    public Movement[] getAllMovementsNotValid(){
        movement = new Movement();
        return movement.getAllMovementsNotValid();
    }

//  get movemnt not valiadte
    public Movement getMovementNotValidById(String idMovement){
        movement = new Movement();
        return movement.getMovementNotValidById(idMovement);
    }

//  get info of unity 
    public Unity getInfoUnity(String idUnity){
        unity = new Unity();
        return unity.getInfoUnity(idUnity);
    }

    // get info type movement
    public TypeMovement getInfoTypeMovement(String idType){
        type = new TypeMovement();
        return type.getInfoTypeMovement(idType);
    }

    // get info store
    public Store getInfoStore(String idStore){
        store = new Store();
        return store.getInfoStore(idStore);
    }

    // get last info validationn article 
    public ValidateMovement getAllMovementInValidate(String article, String store){
        validateMovement = new ValidateMovement();
        return validateMovement.getAllMovementInValidate(article, store);
    }
}
