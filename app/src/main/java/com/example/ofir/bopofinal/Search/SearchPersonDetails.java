package com.example.ofir.bopofinal.Search;

/**
 * Created by Alonka on 22-Dec-16.
 */

public class SearchPersonDetails {
    private String m_name;

    public SearchPersonDetails(String name)
    {
       m_name = name;

    }

    public String getName(){
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }


}
