package com.example.ofir.bopofinal;

/**
 * Created by Alonka on 26-Nov-16.
 */


public class LoggedInUserService
{
    static boolean flag = false;
    static private LoggedInUserService m_instance;

    private int m_ID;

    private LoggedInUserService()
    {

    }

    public static LoggedInUserService getInstance()
    {
        if (null == m_instance)
        {
            m_instance = new LoggedInUserService();
        }
        return m_instance;
    }

    public void setID(int id)
    {
        m_ID = id;
    }

    public int getID()
    {
        return m_ID;
    }

//    how to call a singleton class:
//
//    LoggedInUserService loggedInUserService = LoggedInUserService.getInstance();
//    loggedInUserService.setID(9);
//
//    LoggedInUserService loggedInUserService1 = LoggedInUserService.getInstance();
//    int id  = loggedInUserService.getID();
}
