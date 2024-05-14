package com.example;

/* 
TODO: 
Implement Stack in PET ADOPTION SYSTEM and STAFF ACTIVITY 
 */

public class Account2{
    private String name;
    private String password;

    //getter and setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

class User extends Account2 {
    public void adopt(Pet pet, String name){
       
    }
    public void reqAdopt(Pet pet){

    }
}

class Staff extends Account2{
    public void reqAdopt(Pet pet){

    }
    public void addPet(Pet pet){

    }

}
