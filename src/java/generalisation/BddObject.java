package generalisation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class BddObject {
    String prefix = "";
    int lengthPK = 7;
    String functionName = "";
    

    /// Encapsulation
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setLengthPK(int lengthPK) {
        this.lengthPK = lengthPK;
    }
    public int getLengthPK() {
        return lengthPK;
    }
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
    public String getFunctionName() {
        return functionName;
    }

    /// Constructor
    public BddObject(String prefix, String functionName){
        this.setPrefix(prefix);
        this.setFunctionName(functionName);
    }
    public BddObject(){}
    


    /// creation of serial primary key like 'PRS0001'
    public String completeZero(int seq, int lc){
        String result = "";
        int lengthPref = getPrefix().length();
        int lengthPK = getLengthPK();
        int L= lengthPK - lengthPref - String.valueOf(seq).length();
        for (int i = 0; i < L; i++) {
            result = result.concat("0");
        }
        String id = getPrefix() + result+ seq;
        return id;
    }

    public int getseq(Connection c) throws Exception {
        String sql = "SELECT " + getFunctionName() + "()";
        // System.out.println(sql);
        Statement stmt = c.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        result.next();
        int seq = result.getInt(1);
        return seq;
    }

    public String constructPK(Connection c) throws Exception{
        int seq= getseq(c);
        return completeZero(seq, getLengthPK());
    }


    // function who add simple quote
    public Object separate(Object object){
        if (object instanceof String) {
            object = "'" +object + "'";
        }
        if (object instanceof Date) {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        object = date.format(object);
        }
        return object;
    }

    /// Upper case
    private String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    

    /// insertion without condition 
    public void insert(Connect c) throws Exception{
        Connection con = c.connectPostgres();
        Statement statement = con.createStatement();
        String className = this.getClass().getSimpleName();
        String sql = "INSERT INTO " + className + " VALUES (";
        Field[] attributes = this.getClass().getDeclaredFields();
        String temp = null;
        String temps = "";
        for (int i = 0; i < attributes.length; i++) {
            temp = ""  + separate(this.getClass().getMethod("get"+ capitalize(attributes[i].getName())).invoke(this)) + "";
            temps = temps + "," + temp;
        } 
        char[] tt = temps.toCharArray();
        char[] ttt = new char[tt.length - 1];
        for (int f = 0; f < ttt.length; f++) {
            ttt[f] = tt[f + 1];
        }
        String ss = ttt.toString().copyValueOf(ttt);
        sql = sql + "" + ss + ")";
        System.out.println("request:" + sql);
        statement.executeUpdate(sql);

        // statement.close();
    }

    /// select without condition 
    public Vector<Object> select(Connect c)throws Exception{
        Connection con = c.connectPostgres();
        Statement statement = con.createStatement();
        String className = this.getClass().getSimpleName();

        ResultSet result = statement.executeQuery("SELECT * FROM " + className);
        System.out.print(result);
        Vector<Object> tabObj = new Vector<Object>();
        Field[] attribut = this.getClass().getDeclaredFields();
        while (result.next()) {
            Object newObjet = this.getClass().newInstance(); // this.obje ts.getClass().newInstance();
            for (int i = 0; i < attribut.length; i++) {
                if (attribut[i].getType().getName() == "double") {
                    double val = result.getDouble(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + attribut[i].getName(), double.class).invoke(newObjet, val);
                }
                if (attribut[i].getType().isInstance(new String())) {
                    String val = result.getString(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + capitalize(attribut[i].getName()), String.class).invoke(newObjet, val);
                }
                if (attribut[i].getType().isInstance(new Date())) {
                    Date val = result.getDate(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + capitalize(attribut[i].getName()), Date.class).invoke(newObjet, val);
                }
            }
            tabObj.add(newObjet);
        }
        // statement.close();
        // result.close();
        return tabObj;
    }

    /// select with condition
    public Vector<Object> selectConditioned(Connect c, String table, String condition)throws Exception{
        Connection con = c.connectPostgres();
        Statement statement = con.createStatement();
        String className = this.getClass().getSimpleName();

        String sql = "SELECT * FROM " + table + " WHERE " +condition;
        System.out.println(sql);
        
        ResultSet result = statement.executeQuery(sql);
        Vector<Object> tabObj = new Vector<Object>();
        Field[] attribut = this.getClass().getDeclaredFields();
        while (result.next()) {
            Object newObjet = this.getClass().newInstance(); 
            for (int i = 0; i < attribut.length; i++) {
                if (attribut[i].getType().getName() == "double") {
                    double val = result.getDouble(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + attribut[i].getName(), double.class).invoke(newObjet, val);
                }
                if (attribut[i].getType().isInstance(new String())) {
                    String val = result.getString(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + capitalize(attribut[i].getName()), String.class).invoke(newObjet, val);
                }
                if (attribut[i].getType().isInstance(new Date())) {
                    Date val = result.getDate(attribut[i].getName());
                    newObjet.getClass().getMethod("set" + capitalize(attribut[i].getName()), Date.class).invoke(newObjet, val);
                }
            }
            tabObj.add(newObjet);
        }
        // statement.close();
        // result.close();
        return tabObj;
    }


    
}