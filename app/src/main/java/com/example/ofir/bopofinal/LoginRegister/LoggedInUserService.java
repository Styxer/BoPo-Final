package com.example.ofir.bopofinal.LoginRegister;

//user singelton
/**
 * Created by ofir on 11/29/2016.
 */
public class LoggedInUserService {

    static boolean flag = false;
    static private LoggedInUserService m_instance;

    private int m_id;
    private String m_role;
    private String m_name;
    private String m_email;
    private String m_birthday;
    private String m_rating;
    private String m_gender;
    private String m_password;
    private String m_phone_number;
    private String m_address;
    private String m_image;

    private LoggedInUserService(){}

    public static LoggedInUserService getInstance() {
      if(m_instance == null)
          m_instance = new LoggedInUserService();
      return m_instance;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getM_role() {
        return m_role;
    }

    public void setM_role(String m_role) {
        this.m_role = m_role;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_email() {
        return m_email;
    }

    public void setM_email(String m_emal) {
        this.m_email = m_emal;
    }

    public String getM_birthday() {
        return m_birthday;
    }

    public void setM_birthday(String m_birthday) {
        this.m_birthday = m_birthday;
    }

    public String getM_rating() {
        return m_rating;
    }

    public void setM_rating(String m_rating) {
        this.m_rating = m_rating;
    }

    public String getM_gender() {
        return m_gender;
    }

    public void setM_gender(String m_gender) {
        this.m_gender = m_gender;
    }

    public String getM_password() {
        return m_password;
    }

    public void setM_password(String m_password) {
        this.m_password = m_password;
    }

    public String getM_phone_number() {
        return m_phone_number;
    }

    public void setM_phone_number(String m_phone_number) {
        this.m_phone_number = m_phone_number;
    }

    public String getM_address() {
        return m_address;
    }

    public void setM_address(String m_address) {
        this.m_address = m_address;
    }

    public String getM_image() {
        return m_image;
    }

    public void setM_image(String m_image) {
        this.m_image = m_image;
    }

    public void reset()
    {
        m_instance = null;
    }
}

