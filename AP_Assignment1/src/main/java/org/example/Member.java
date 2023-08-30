package org.example;

import javax.swing.text.html.parser.TagElement;

public class Member {
    private String name;
    private float age;
    private int n_books;
    private int phone_no;
    private int ID;
    private boolean isRemoved;

    public Member(String name, float age, int phone_no, int ID){
        this.name = name;
        this.age = age;
        this.phone_no = phone_no;
        this.ID = ID;
        this.isRemoved = false;
        this.n_books = 0;
    }

    public int getPhone_no(){
        return this.phone_no;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public void setRemovedStatus(boolean value){
        this.isRemoved = value;
    }
}
