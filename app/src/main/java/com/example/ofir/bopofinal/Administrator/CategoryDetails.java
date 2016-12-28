package com.example.ofir.bopofinal.Administrator;


/**
 * Created by Alonka on 14-Dec-16.
 */

public class CategoryDetails {
    private String category_name, category_status, user_id, request_id;
    private boolean isSelected;

    public CategoryDetails(String category_name, String category_status, String user_id, String request_id, boolean isSelected)
    {
        this.category_name = category_name;
        this.category_status = category_status;
        this.user_id = user_id;
        this.request_id = request_id;
        this.isSelected = isSelected;
    }

    public String getCategoryName(){
        return category_name;
    }

    public void setCategoryName(String name)
    {
        category_name = name;
    }

    public String getCategoryStatus(){
        return category_status;
    }

    public void setCategoryStatus(String location)
    {
        category_status = location;
    }

    public String getUserId(){
        return user_id;
    }

    public void setUserId(String userId)
    {
        user_id = userId;
    }

    public String getRequestId(){
        return request_id;
    }

    public void setRequestId(String requestId)
    {
        request_id = requestId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
