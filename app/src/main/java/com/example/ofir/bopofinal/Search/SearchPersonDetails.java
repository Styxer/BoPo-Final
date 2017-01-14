package com.example.ofir.bopofinal.Search;

/**
 * Created by Alonka on 22-Dec-16.
 */

public class SearchPersonDetails {
    private String m_name;
    private String m_user_id;
    private String m_image;

    public SearchPersonDetails(String name,  String user_id, String image)
    {
       m_name = name;
       m_image = image;
       m_user_id = user_id;

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


}
