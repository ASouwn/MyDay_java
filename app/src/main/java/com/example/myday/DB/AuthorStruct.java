package com.example.myday.DB;
public class AuthorStruct {

    //made by the function focus on
    //the list about the author your focus


    private Integer ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String BIRTH;
    private String SELF;

    public AuthorStruct(Integer ID, String FIRST_NAME, String LAST_NAME, String BIRTH, String self) {
        this.ID = ID;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.BIRTH = BIRTH;
        SELF = self;
    }

    public String getSELF() {
        return SELF;
    }

    public void setSELF(String SELF) {
        this.SELF = SELF;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getBIRTH() {
        return BIRTH;
    }

    public void setBIRTH(String BIRTH) {
        this.BIRTH = BIRTH;
    }
}


