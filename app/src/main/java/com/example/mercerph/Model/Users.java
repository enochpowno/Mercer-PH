package com.example.mercerph.Model;

public class Users
{
    private String address, email, name, password, phone, username;

    public Users()
    {

    }

    public Users(String address, String email, String name, String password, String phone, String username) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
