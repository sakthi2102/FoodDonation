package com.example.fooddonation;

public class signup {
    String Uname,oname,member,number,email,password,address,city,district,state,key;

    public signup() {
    }

    public String getName() {
        return Uname;
    }

    public void setName(String name) {
        this.Uname = name;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public signup(String Uname, String oname, String member, String number, String email, String password, String address, String city, String district, String state) {
        this.Uname = Uname;
        this.oname = oname;
        this.member = member;
        this.number = number;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.district = district;
        this.state = state;
    }
}
