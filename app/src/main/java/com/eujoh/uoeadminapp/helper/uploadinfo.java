package com.eujoh.uoeadminapp.helper;

public class uploadinfo {
    public String itemName;
    public String itemDesc;
    public String imageURL;
    public uploadinfo(){
        //Empty constructor needed
    }

    public uploadinfo(String name, String desc, String url) {
        this.itemName = name;
        this.itemDesc = desc;
        this.imageURL = url;
    }

    public String getItemName() {
        return itemName;
    }
    public String getItemDesc() {
        return itemDesc;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
