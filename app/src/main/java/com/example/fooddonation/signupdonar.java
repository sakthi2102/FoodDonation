package com.example.fooddonation;

public class signupdonar {
    String name,number,email,password,key;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public signupdonar() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public signupdonar(String name, String number, String email, String password ) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
    }
}
