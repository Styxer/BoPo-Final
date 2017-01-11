package com.example.ofir.bopofinal.Messages;

/**
 * Created by Alonka on 11-Jan-17.
 */

public class CountUnreadMessagesService {
    static private CountUnreadMessagesService m_instance;

    private int m_messages_number;

    private CountUnreadMessagesService(){}

    public static CountUnreadMessagesService getInstance() {
        if(m_instance == null)
            m_instance = new CountUnreadMessagesService();
        return m_instance;
    }

    public int getM_messages_number() {
        return m_messages_number;
    }

    public void setM_messages_number(int m_messages_number) {
        this.m_messages_number = m_messages_number;
    }
}
