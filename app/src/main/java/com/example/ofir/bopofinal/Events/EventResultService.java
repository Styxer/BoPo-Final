package com.example.ofir.bopofinal.Events;

import java.util.ArrayList;

/**
 * Created by Alonka on 16-Dec-16.
 */

public class EventResultService {

    static private EventResultService m_instance;
    ArrayList<EventDetails> m_arrayList = new ArrayList<>();

    public static EventResultService getInstance() {
        if(m_instance == null)
            m_instance = new EventResultService();
        return m_instance;
    }

    private EventResultService()
    {

    }

    public void reset()
    {
        m_arrayList.clear();
    }

    public ArrayList<EventDetails> getArray()
    {
        return m_arrayList;
    }

    public void setArray(ArrayList<EventDetails> arrayList)
    {
        m_arrayList = arrayList;
    }
}
