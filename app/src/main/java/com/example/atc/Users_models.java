package com.example.atc;

public class Users_models {

    private String email;
    private String fName;
    private String phone;
    private String image;
    private String imageURL;
    private String id;

    private Users_models(){}
    private Users_models(String email, String fName, String phone, String image){

        this.email = email;
        this.fName = fName;
        this.image = image;
        this.id = id;
        this.phone = phone;
        this.imageURL = imageURL;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
