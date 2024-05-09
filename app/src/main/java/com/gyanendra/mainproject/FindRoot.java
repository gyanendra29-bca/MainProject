package com.gyanendra.mainproject;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class FindRoot extends AppCompatActivity {
    private EditText editText1,editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_root);
        editText1=findViewById(R.id.Ed1);
        editText2=findViewById(R.id.Ed2);
        button=findViewById(R.id.btn1);

        button.setOnClickListener(view ->{
            String userlocation = editText1.getText().toString();
            String userDestination = editText2.getText().toString();

            if(userlocation.equals("") || userDestination.equals("")){
                Toast.makeText(this, "please enter your location & destination", Toast.LENGTH_SHORT).show();
            }else
            {
                getDirections(userlocation,userDestination);

            }
        });
    }
    private void getDirections(String from,String to)
    {
        try{
            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+from+"/"+to);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}
