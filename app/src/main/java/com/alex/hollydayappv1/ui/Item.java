package com.alex.hollydayappv1.ui;

public class Item {

    String name;
    String destination;
   String price;
   // boolean booking;
    int image;


    public Item(String name, String destination, String price, /*boolean booking,*/ int image) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        // this.booking = booking;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


//    public boolean isBooking() {
//        return booking;
//    }
//
//    public void setBooking(boolean booking) {
//        this.booking = booking;
//    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
