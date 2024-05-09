package com.gyanendra.mainproject.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.gyanendra.mainproject.R;

public class EditDetailsActivity extends AppCompatActivity {

    EditText updateAdharNumber;
    Button submitDataBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
       updateAdharNumber = (EditText) findViewById(R.id.updateAdharNumberEditTxt);
       submitDataBtn = (Button) findViewById(R.id.submitDetailsBtn);
        String userId = GoogleSignIn.getLastSignedInAccount(getApplicationContext()).getId();
        submitDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adharNumber = updateAdharNumber.getText().toString();
                if (adharNumber.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enetr your Adharcard number ",Toast.LENGTH_SHORT).show();

                }else {
                    FirebaseDatabase.getInstance().getReference().child("users")
                            .child(userId)
                            .child("adharNumber")
                            .setValue(adharNumber).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"AdharNumber updated successfully",Toast.LENGTH_SHORT).show();
                                    updateAdharNumber.setText("");
                                    }
                                }
                            });
                }
            }
        });

    }
}