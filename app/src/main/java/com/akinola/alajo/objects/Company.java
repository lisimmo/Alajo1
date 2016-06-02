package com.akinola.alajo.objects;

/**
 * Created by Akinola on 5/10/2016.
 * The company class
 */
public class Company {
    private int id,status;
    private String name,abbreviation,slogan,logo;

    public Company(int id, int status, String name, String abbreviation, String slogan, String logo) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.abbreviation = abbreviation;
        this.slogan = slogan;
        this.logo = logo;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
