package com.example.ofir.bopofinal.Search;

import java.util.ArrayList;

/**
 * Created by Alonka on 23-Dec-16.
 */

public class SearchResultService {


    static private SearchResultService m_instance;
    ArrayList<SearchPersonDetails> m_arrayList = new ArrayList<>();

    public static  SearchResultService getInstance() {
        if(m_instance == null)
            m_instance = new  SearchResultService();
        return m_instance;
    }

    private  SearchResultService()
    {

    }

    public void reset()
    {
        m_arrayList.clear();
    }

    public ArrayList<SearchPersonDetails> getArray()
    {
        return m_arrayList;
    }

    public void setArray(ArrayList<SearchPersonDetails> arrayList)
    {
        m_arrayList = arrayList;
    }
}
