package org.jjcouple.termproject.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import org.jjcouple.termproject.R;
import org.jjcouple.termproject.Sign.SignUp;

public class loading extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_activity);

        Handler handler;
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

}
