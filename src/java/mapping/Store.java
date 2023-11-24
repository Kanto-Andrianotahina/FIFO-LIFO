package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import generalisation.Connect;

public class Store {
    String id;
    String store;

    // Encapsulation
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStore() {
        return store;
    }
    public void setStore(String store) {
        this.store = store;
    }

    // Methods

    // get list stores 
    public Store[] getAllStores(){
        List<Store> listStore = new ArrayList<>();
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from store";
            PreparedStatement state = con.prepareStatement(sql);
            ResultSet result = state.executeQuery();
            while(result.next()){
                Store store = new Store();
                store.setId(result.getString("id"));
                store.setStore(result.getString("store"));
                
                listStore.add(store);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listStore.toArray(new Store[listStore.size()]);
    }

    // get list stores 
    public Store getInfoStore(String idStore ){
        Store store = null;
        try{
            Connect c = new Connect();
            Connection con = c.connectPostgres();
            String sql = "select * from store where id = ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, idStore);
            ResultSet result = state.executeQuery();
            while(result.next()){
                store = new Store();
                store.setId(result.getString("id"));
                store.setStore(result.getString("store"));
                
            }
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return store;
    }


    // Constructors 
    public Store(){}
    public Store(String id, String store) {
        this.setId(id);
        this.setStore(store);
    }


    
    
    
}
