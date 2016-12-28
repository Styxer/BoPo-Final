package com.example.ofir.bopofinal.Administrator;

import com.example.ofir.bopofinal.Events.EventDetails;
import com.example.ofir.bopofinal.Events.EventResultService;

import java.util.ArrayList;

/**
 * Created by Alonka on 27-Dec-16.
 */

public class ManageCategoriesResultService {
    static private ManageCategoriesResultService m_instance;
    ArrayList<CategoryDetails> m_arrayList = new ArrayList<>();

    public static ManageCategoriesResultService getInstance() {
        if(m_instance == null)
            m_instance = new ManageCategoriesResultService();
        return m_instance;
    }

    private ManageCategoriesResultService()
    {

    }

    public void reset()
    {
        m_arrayList.clear();
    }

    public ArrayList<CategoryDetails> getArray()
    {
        return m_arrayList;
    }

    public void setArray(ArrayList<CategoryDetails> arrayList)
    {
        m_arrayList = arrayList;
    }
}
