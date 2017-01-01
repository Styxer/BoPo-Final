package com.example.ofir.bopofinal.PeopleInEvent;

/**
 * Created by ofir on 12/26/2016.
 */
public class MyData {

    private int  mId;
    private String mName,mRole, mImage_link;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }



    public String getmImage_link() {
        return mImage_link;
    }

    public void setmImage_link(String mImage_link) {
        this.mImage_link = mImage_link;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmRole(String mRole) {
        this.mRole = mRole;
    }

    public String getmName() {

        return mName;
    }

    public String getmRole() {
        return mRole;
    }

    public MyData(){}

    public MyData(int mId, String mName, String mRole, String mImage_link) {
        this.mId = mId;
        this.mName = mName;
        this.mRole = mRole;
        this.mImage_link = mImage_link;
    }
}
