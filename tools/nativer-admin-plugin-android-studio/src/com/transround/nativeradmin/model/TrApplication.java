package com.transround.nativeradmin.model;

import java.util.List;

/**
 * Created by szeibert on 2014.11.27..
 */
public class TrApplication {
    private Long id;
    private List<CategoryDescription> categoryDescription;
    private String trJavaPackage;

    public String getTrJavaPackage() {
        return trJavaPackage;
    }

    public void setTrJavaPackage(String trJavaPackage) {
        this.trJavaPackage = trJavaPackage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CategoryDescription> getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(List<CategoryDescription> categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
