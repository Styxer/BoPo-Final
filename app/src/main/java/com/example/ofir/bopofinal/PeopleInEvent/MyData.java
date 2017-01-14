package com.example.ofir.bopofinal.PeopleInEvent;

import java.util.Comparator;

/**
 * Created by ofir on 12/26/2016.
 */
public class MyData  {

    private int  mId;
    private String mName,mRole,mBirthday,mPhone_number,mAddress  ,mImage_link ;

    public MyData(int mId, String mName, String mRole, String mBirthday, String mPhone_number, String mAddress, String mImage_link) {
        this.mId = mId;
        this.mName = mName;
        this.mRole = mRole;
        this.mBirthday = mBirthday;
        this.mPhone_number = mPhone_number;
        this.mAddress = mAddress;
        this.mImage_link = mImage_link;
    }

    public void setmId(int mId) {

        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmRole(String mRole) {
        this.mRole = mRole;
    }

    public void setmBirthday(String mBirthday) {
        this.mBirthday = mBirthday;
    }

    public void setmPhone_number(String mPhone_number) {
        this.mPhone_number = mPhone_number;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public void setmImage_link(String mImage_link) {
        this.mImage_link = mImage_link;
    }

    public int getmId() {

        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmRole() {
        return mRole;
    }

    public String getmBirthday() {
        return mBirthday;
    }

    public String getmPhone_number() {
        return mPhone_number;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmImage_link() {
        return mImage_link;
    }
}
