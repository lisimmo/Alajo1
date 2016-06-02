package com.akinola.alajo.objects;

/**
 * Created by Akinola on 5/10/2016.
 * The customer class, a subclass of user
 */
public class Customer  extends User{
    private String nextOfKin,nextOfKinPhone;
    private int companyId;
    private float rating;
    private String uniqueId;

    public Customer(int id, String firstname, String lastname, String email, String phone, String nextOfKin, String nextOfKinPhone, int companyId, float rating, String uniqueId) {
        super(id, firstname, lastname, email, phone);
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.companyId = companyId;
        this.rating = rating;
        this.uniqueId = uniqueId;
    }

    public Customer(String nextOfKin, String nextOfKinPhone, int companyId, float rating, String uniqueId) {
        this.nextOfKin = nextOfKin;
        this.nextOfKinPhone = nextOfKinPhone;
        this.companyId = companyId;
        this.rating = rating;
        this.uniqueId = uniqueId;
    }

    public Customer() {
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = nextOfKinPhone;
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

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
