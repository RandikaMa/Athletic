package com.example.atc.Model;

public class User {



    private String imageURL;
    private String fName;
    private String id;

    private String status;
    private String search;



    public User (String id, String fName, String imageURL,String status,String search){

        this.imageURL = imageURL;
        this.fName = fName;
        this.id = id;

        this.status = status;
        this.search = search;

    }

    public User(){

    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
