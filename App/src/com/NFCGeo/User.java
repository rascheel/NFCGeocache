package com.NFCGeo;

public class User {
    
    private String userName;
    private UserType userType;
    
    //Default construct sets user up with lowest permissions
    public User(String name)
    {
        userName = name;
        userType = UserType.USER;
    }

    public User(String name, UserType type)
    {
        userName = name;
        userType = type;
    }

    public String name()
    {
        return userName;
    }

    public UserType type()
    {
        return userType;
    }
}


