package com.example.wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtViewResults;
    //updated another line from local 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);
        actionBar.setTitle(R.string.txtTitle);

        txtViewResults = findViewById(R.id.txtViewResults);
        EditText editTextInputWt = findViewById(R.id.editTextInputWt);
        RadioGroup radGroupConv = findViewById(R.id.radGroupConv);
        Button btnConvertWt = findViewById(R.id.btnConvertWt);
        radGroupConv.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radBtnLbsToKgs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert Pounds to Kilos",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.radBtnKgsToLbs){
                    Toast.makeText(MainActivity.this,
                            "Let us convert kilos to pounds",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        // radGroupConv.getCheckedRadioButtonId() --> -1 if nothing is onContextItemSelected()
        int radGroupBtnid = radGroupConv.getCheckedRadioButtonId();
        if(radGroupBtnid ==-1)
        {
            Toast.makeText(MainActivity.this,"It is not selected for the Converted",
                    Toast.LENGTH_SHORT).show();
        }else{
            // --> R.id.radBtnLbsToKgs if that radio button is checked
            if(radGroupBtnid == R.id.radBtnLbsToKgs){
                Toast.makeText(MainActivity.this,"Start to Change the values from Lbs to Kgs",
                        Toast.LENGTH_SHORT).show();
            }
            // --> R.id.radBtnKgsToLbs if the other radio button is checked
            else if (radGroupBtnid == R.id.radBtnKgsToLbs) {
                Toast.makeText(MainActivity.this,"Start to Convert the values from Kgs to Lbs",
                        Toast.LENGTH_SHORT).show();
            }
        };
        //In that listener, do the following:
        //First, set up a button click listener for btnConvertWt
        btnConvertWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Display an error message if no radio button is checked (id is -1)
                if (radGroupBtnid == -1) {
                    Toast.makeText(MainActivity.this, "Please select the direction to change", Toast.LENGTH_SHORT).show();
                }
                //Display an error message if editTextInputWt is empty
                else if (editTextInputWt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please input values for Convert", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        double WeightValues = Double.parseDouble(editTextInputWt.getText().toString());
                        //If Pounds to Kilos is checked and parsed inputWt is > 1000, display an error message saying input weight in pounds must be <= 1000
                        if (WeightValues > 1000 && radGroupBtnid == R.id.radBtnLbsToKgs)
                            Toast.makeText(MainActivity.this, "input weight in pounds must be <= 1000", Toast.LENGTH_SHORT).show();
                            //If Kilos to Pounds is checked and parsed inputWt is > 500, display an error message saying input weight in pounds must be <= 500
                        else if (WeightValues > 500 && radGroupBtnid == R.id.radBtnKgsToLbs)
                            Toast.makeText(MainActivity.this, "input weight in pounds must be <= 500", Toast.LENGTH_SHORT).show();
                        //Otherwise compute outputWtInKilos = inputWt/2.2
                                double ConvertedLbstoKGgs = WeightValues/2.2;
                                Toast.makeText(MainActivity.this,"The Result is " + ConvertedLbstoKGgs,Toast.LENGTH_SHORT).show();
                        //Otherwise compute outputWtInPounds = inputWt*2.2
                                double ConvertedKgstoLbs = WeightValues*2.2;
                        Toast.makeText(MainActivity.this,"The Result is " + ConvertedKgstoLbs,Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(MainActivity.this,
                                "Invalid input, must be a whole positive number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            });

        //Display the output weight in txtViewResults using correct
        //unit as Kgs (if converting from pounds to kilos) and
        //Lbs (if converting kilos to pounds)

    }
}