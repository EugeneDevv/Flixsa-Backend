package com.eujoh.uoeadminapp.helper;

public class uploadinfo {
    public String itemName;
    public String itemDesc;
    public String imageURL;
    public uploadinfo(){}

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
}
