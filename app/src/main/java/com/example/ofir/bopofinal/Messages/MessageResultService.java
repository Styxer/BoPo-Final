package com.example.ofir.bopofinal.Messages;

import java.util.ArrayList;

/**
 * Created by Alonka on 09-Jan-17.
 */

public class MessageResultService {
    static private MessageResultService m_instance;
    ArrayList<MessageDetails> m_arrayList = new ArrayList<>();

    public static MessageResultService getInstance() {
        if(m_instance == null)
            m_instance = new MessageResultService();
        return m_instance;
    }

    private MessageResultService()
    {

    }

    public void reset()
    {
        m_arrayList.clear();
    }

    public ArrayList<MessageDetails> getArray()
    {
        return m_arrayList;
    }

    public void setArray(ArrayList<MessageDetails> arrayList)
    {
        m_arrayList = arrayList;
    }
}
