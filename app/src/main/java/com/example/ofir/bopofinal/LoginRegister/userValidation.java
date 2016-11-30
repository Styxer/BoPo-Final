package com.example.ofir.bopofinal.LoginRegister;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormatter;
import org.joda.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ofir on 11/29/2016.
 */
public class userValidation {

  static   LoginActivity loginActivity = new LoginActivity();

    public static boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean emptyFields(EditText[] editTexts,String text) {
        for (EditText edit : editTexts) {
            if (edit.getText().toString().matches("")) {
                setError(text, edit);
                return false;
            }
                //   alertDialog(loginActivity, "Please fill al the filed,", "Retry");
            else {
                setError(null, edit);
               // return true;
            }


            //  if (TextUtils.isEmpty(edit.getText())) {
            //   alertDialog(loginActivity, "Please enter a " + edit.getHint().toString(), "Retry");
            //  }
        }
        return true;
    }
    public static void setError(String error,EditText editText){
        editText.setError(error);
    }

    public static   void alertDialog(Context context, CharSequence message, CharSequence type){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setNegativeButton(type, null)
                .create()
                .show();
    }

    @NonNull
    public static Boolean CheckAge(String dateOfBirth) {
      //  int minYear = Calendar.getInstance().get(Calendar.YEAR);
      //  minYear -= 18;
        String[] strings = dateOfBirth.split("/");
        String string = strings[2];
        int birthday = Integer.parseInt(string);
        if (birthday <= 18 )
            return false;

        return true;



    }

}
