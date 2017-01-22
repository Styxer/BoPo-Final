package com.example.ofir.bopofinal.Search;

/**
 * Created by Alonka on 22-Dec-16.
 */

public class SearchPersonDetails {
    private String m_name;
    private String m_user_id;
    private String m_image;
    private String m_email;
    private String m_birthday;
    private String m_phone_number;
    private String m_address;

    public SearchPersonDetails(String name,  String user_id, String image, String email, String birthday, String phone_number , String address)
    {
       m_name = name;
       m_image = image;
       m_user_id = user_id;
       m_email = email;
       m_birthday = birthday;
       m_phone_number = phone_number;
       m_address = address;
    }

    public String getName(){
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getUserId(){
        return m_user_id;
    }

    public void setUserId(String user_id)
    {
        m_user_id = user_id;
    }

    public String getImage(){
        return m_image;
    }

    public void setImage(String image)
    {
        m_image = image;
    }

    public String getEmail(){
        return m_email;
    }

    public void setEmail(String email)
    {
        m_email = email;
    }

    public String getBirthday(){
        return m_birthday;
    }

    public void setBirthday(String birthday)
    {
        m_birthday = birthday;
    }

    public String getPhoneNumber(){
        return m_phone_number;
    }

    public void setPhoneNumber(String phone_number)
    {
        m_phone_number = phone_number;
    }

    public String getAddress(){
        return m_address;
    }

    public void setAddress(String address)
    {
        m_address = address;
    }
}
