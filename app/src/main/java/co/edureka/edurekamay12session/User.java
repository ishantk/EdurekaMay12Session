package co.edureka.edurekamay12session;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

    // Attributes
    String name;
    String phone;
    int age;

    public User(){

    }

    public User(String name, String phone, int age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
}
