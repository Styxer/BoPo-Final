package com.example.ofir.bopofinal.PeopleInEvent;

/**
 * Created by ofir on 12/26/2016.
 */
public class MyData {

    private int  mId;
    private String mDescription, mImage_link;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmImage_link() {
        return mImage_link;
    }

    public void setmImage_link(String mImage_link) {
        this.mImage_link = mImage_link;
    }

    public MyData(int mId, String mDescription, String mImage_link) {

        this.mId = mId;
        this.mDescription = mDescription;
        this.mImage_link = mImage_link;
    }
    public MyData(){}


}
