package com.example.atc;

public class modll {

    String fName,email,imageURL;


    modll(){

    }

    public modll(String fName,String email){
        this.fName = fName;
        this.email=email;
        this.imageURL=imageURL;
    }


    public String getfName(){
        return fName;
    }
    public void setfName(String fName){
        this.fName = fName;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getImageURL(){

        return imageURL;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
}
