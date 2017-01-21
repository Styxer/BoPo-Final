package com.example.ofir.bopofinal.Rides;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ofir.bopofinal.LoginRegister.userValidation;
import com.example.ofir.bopofinal.MainAppScreenActivity;
import com.example.ofir.bopofinal.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.turkialkhateeb.materialcolorpicker.ColorChooserDialog;
import com.turkialkhateeb.materialcolorpicker.ColorListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class InputRideDetailsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener ,TextWatcher {

    private EditText mEtStartLocation, mEtMaxPeople, etMCarName, etMCarColor;
    private Button bMcreateRide, mBchangeStartLocation;
    private TextView mTvEventName;
    private Spinner mRideSpinner;

    private  Intent searchIntent;
    private  ProgressDialog progressDialog;

    private List<String> carTypes;
    EditText[] fields;


    String mAfterStartLocation,mAfterMaxPeople,mAfterCarModel, mAfetrCarColor, mAfterCarSize;
    int mUserID, mEventID;
    final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ride_details);

        getSupportActionBar().setTitle("Give a ride");

        mEtStartLocation = (EditText) findViewById(R.id.etRideStartLocation);
        mEtMaxPeople = (EditText) findViewById(R.id.etRideMaxMember);
        etMCarName = (EditText) findViewById(R.id.etRideCadModel);
        etMCarColor = (EditText) findViewById(R.id.etRideCarColor);

        bMcreateRide = (Button) findViewById(R.id.bRideCreateRide);
        mBchangeStartLocation = (Button) findViewById(R.id.bChangeStartLocation);

        mRideSpinner = (Spinner) findViewById(R.id.rideSpinner);
        mRideSpinner.setOnItemSelectedListener(this);
        carTypes = Arrays.asList("Choose car type","private(2 places)","private(5 places)", "private(7 places)", "private(8 places)");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,carTypes);
        mRideSpinner.setAdapter(dataAdapter);


        progressDialog = new ProgressDialog(this);

        bMcreateRide.setOnClickListener(this);
        etMCarColor.setOnClickListener(this);
        mBchangeStartLocation.setOnClickListener(this);

        mTvEventName = (TextView) findViewById(R.id.tvRideName);
        Intent intent = getIntent();
        mTvEventName.setText(Html.fromHtml(intent.getStringExtra("eventName")));
        mEtStartLocation.setText(Html.fromHtml(intent.getStringExtra("eventLocation")));
        mUserID = Integer.parseInt(intent.getStringExtra("userID"));
        mEventID = Integer.parseInt(intent.getStringExtra("eventID"));

        fields = new EditText[]{mEtStartLocation, mEtMaxPeople, etMCarName, etMCarColor};

        for (EditText editText: fields)
            editText.addTextChangedListener(this);





    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bChangeStartLocation:
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                setProgressDialogText("Loading map...","");
                progressDialog.show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },3000);

                try {
                    searchIntent = builder.build(this);
                    startActivityForResult(searchIntent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

                break;


            case R.id.bRideCreateRide:
                sendDataToServer();

                break;

            case R.id.etRideCarColor:
                pickColor();
                break;
        }

    }

    private void sendDataToServer() {
        Boolean emptyFields = userValidation.emptyFields(fields,"please fill in this field");
        int maxSize = Integer.parseInt(mAfterMaxPeople);
        int carSize = Integer.parseInt(mAfterCarSize);
        if (carSize > maxSize){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("error");
            builder.setMessage("maximum people cannot be bigger than car size");
            builder.setPositiveButton("okay",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });


            AlertDialog dialog = builder.create();
            dialog.show();
        }
       else if (emptyFields){
            setProgressDialogText("Creating ride...","");
            progressDialog.show();
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.i("ResponseRide",response.toString());
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            progressDialog.hide();
                            Toast.makeText(InputRideDetailsActivity.this,"A new ride created successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(InputRideDetailsActivity.this, MainAppScreenActivity.class));
                        }

                    } catch (JSONException e) {
                        progressDialog.hide();
                        e.printStackTrace();
                    }
                }
            };

            RideRequest rideRequest = new RideRequest( mUserID, mEventID,"driver", mAfterStartLocation,mAfterMaxPeople,
                    mAfterCarModel, mAfetrCarColor,mAfterCarSize, responseListener);
            RequestQueue queue = Volley.newRequestQueue(InputRideDetailsActivity.this);
            queue.add(rideRequest);
        }
    }

    private void pickColor() {
        ColorChooserDialog dialog = new ColorChooserDialog(this);
        dialog.setTitle("");
        dialog.setColorListener(new ColorListener() {
            @Override
            public void OnColorClick(View view, int color) {
                switch (color){
                    case -769226:
                        etMCarColor.setText("dark red");
                        break;
                    case -1499549:
                        etMCarColor.setText("cyan");
                        break;
                    case -6543440:
                        etMCarColor.setText("purple");
                        break;
                    case -10011977:
                        etMCarColor.setText("dark purple");
                        break;
                    case -12627531:
                        etMCarColor.setText("dark blue");
                        break;
                    case-14575885:
                        etMCarColor.setText("blue");
                        break;
                    case -16537100:
                        etMCarColor.setText("light blue");
                        break;
                    case -16728876:
                        etMCarColor.setText("azure");
                        break;
                    case -16738680:
                        etMCarColor.setText("dark green");
                        break;
                    case -11751600:
                        etMCarColor.setText("green");
                        break;
                    case -7617718:
                        etMCarColor.setText("light green");
                        break;
                    case -3285959:
                        etMCarColor.setText("bottle green");
                        break;
                    case -5317:
                        etMCarColor.setText("yellow");
                        break;
                    case -16121:
                        etMCarColor.setText("light orange");
                        break;
                    case -26624:
                        etMCarColor.setText("orange");
                        break;
                    case -43230:
                        etMCarColor.setText("dark orange");
                        break;
                    case -8825528:
                        etMCarColor.setText("brown");
                        break;
                    case -6381922:
                        etMCarColor.setText("gray");
                        break;
                    case -10453621:
                        etMCarColor.setText("dark gray");
                        break;
                    case -16777216:
                        etMCarColor.setText("black");
                        break;
                }

            }
        });
        //customize the dialog however you want
        dialog.show();
    }

    private void setProgressDialogText( String text , String title ) {
        progressDialog.setMessage(text);
        progressDialog.setTitle(title);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode ==  PLACE_PICKER_REQUEST){
            if(requestCode == 1){
                Place place = PlacePicker.getPlace(this,data);
                String address = String.format("%s",place.getAddress());
                mEtStartLocation.setText(address);
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//        if(position > 0) carTypes.remove(0);
          mRideSpinner.setSelection(position);
         mAfterCarSize = mRideSpinner.getSelectedItem().toString();
        mAfterCarSize = mAfterCarSize.replaceAll("[^0-9]", "");

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
      //  carTypes.add(0,"Choose car type");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {



        mAfterStartLocation = mEtStartLocation.getText().toString();
        mAfterMaxPeople = mEtMaxPeople.getText().toString();
        mAfterCarModel = etMCarName.getText().toString();
        mAfetrCarColor = etMCarColor.getText().toString();


      /*  for(EditText editText: fields){
            editText.setSingleLine();
            editText.setMaxLines(1);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            editText.setOnEditorActionListener((TextView.OnEditorActionListener) this);


        }*/

    }
}
