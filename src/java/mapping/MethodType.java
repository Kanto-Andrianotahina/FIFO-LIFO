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
public class MethodType {
    String id;
    String method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    
    
    
    public MethodType() {
    }
    
    public MethodType(String id, String method) {
        this.setId(id);
        this.setMethod(method);
    }
    
    
    
    
}
