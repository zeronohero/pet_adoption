package com.example;
import java.util.ArrayList;
import java.util.HashMap;

public class Account {
    private String username;
    private String password;
    private static HashMap<String, String> accountMap = new HashMap<>();
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        accountMap.put(username, password);
    }
    public void adopt(Pet pet, String name){
        System.out.print("pet is adopted");
        Pet.petAdoptionList.put(pet, new Account(name, name));
    }

    public String getusername() {
        return username;
    }

    // Setter for username
    public void setusername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    //
    public static HashMap<String, String> getAccountMap() {
        return accountMap;
    }

    //
    public static void setAccountMap(String username, String password) {
        accountMap.put(username, password);
    }
}
