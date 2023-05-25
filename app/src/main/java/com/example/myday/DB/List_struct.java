package com.example.myday.DB;

public class List_struct {
    private String ID="id";
    private String DATE="date";
    private String TITLE="title";

    public List_struct(String ID, String DATE, String TITLE) {
        this.ID = ID;
        this.DATE = DATE;
        this.TITLE = TITLE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
