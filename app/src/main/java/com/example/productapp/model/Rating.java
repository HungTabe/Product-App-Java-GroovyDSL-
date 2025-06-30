package com.example.productapp.model;

import java.io.Serializable;

public class Rating implements Serializable {
    private double rate;
    private int count;

    public Rating(double rate, int count) {
        this.rate = rate;
        this.count = count;
    }

    public double getRate() { return rate; }
    public int getCount() { return count; }
}