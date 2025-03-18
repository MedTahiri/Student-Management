package com.mohamed.tahiri.studentmanagement.models;

public class createNote {
    public String matiere;
    public String score;
    public boolean status;

    public createNote(String matiere, String score, boolean status) {
        this.matiere = matiere;
        this.score = score;
        this.status = status;
    }

    public createNote() {
    }
}
