package model;

import jakarta.persistence.Id;

public class User {


    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    private int id;
    private String name;
    private String address;

    public User(String email, String phoneNumber, String username, String password, int id, String name, String address){
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public User(){};

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public int getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

}
