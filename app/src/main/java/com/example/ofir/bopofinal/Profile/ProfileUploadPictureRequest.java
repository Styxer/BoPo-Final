package com.example.ofir.bopofinal.Profile;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alonka on 13-Jan-17.
 */

public class ProfileUploadPictureRequest extends StringRequest {
    private static final String UPLOAD_PICTURE_REQUEST_URL = "http://tower.site88.net/ChangeProfilePicture.php";
    private Map<String, String> params;

    public ProfileUploadPictureRequest(String image, String name, Response.Listener<String> listener) {
        super(Request.Method.POST, UPLOAD_PICTURE_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("image", image);
        params.put ("name", name);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

