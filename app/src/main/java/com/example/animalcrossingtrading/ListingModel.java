package com.example.animalcrossingtrading;

public class ListingModel {

    private String listing;
    private long count;

    private ListingModel() {}

    private  ListingModel(String name, long count) {
        this.listing = listing;
        this.count = count;
    }


    public String getListing() {
        return  listing;
    }

    public void setListing(String listing) {
        this.listing = listing;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
