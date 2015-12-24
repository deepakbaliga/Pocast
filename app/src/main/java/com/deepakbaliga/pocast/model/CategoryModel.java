package com.deepakbaliga.pocast.model;

/**
 * Created by deezdroid on 24/12/15.
 */
public class CategoryModel  {

    private String categoryName;
    private int categoryId;

    public CategoryModel(String categoryName, int categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
