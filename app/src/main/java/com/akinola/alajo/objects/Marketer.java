package com.akinola.alajo.objects;

/**
 * Created by guze100 on 5/10/2016.
 * The marketer class, a subclass of user
 */
public class Marketer extends User {
    private int companyId;
    private float rating;

    public Marketer(int id, String firstname, String lastname, String email, String phone, int companyId, float rating) {
        super(id, firstname, lastname, email, phone);
        this.companyId = companyId;
        this.rating = rating;
    }

    public Marketer(int companyId, float rating) {
        this.companyId = companyId;
        this.rating = rating;
    }

    public Marketer() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
