package org.example;

import org.example.Librarian;

public class Library {
    private Librarian librarian;

    public Library(){
        this.librarian = new Librarian(this);
    }

    public Librarian getLibrarian(){
        return this.librarian;
    }
}
