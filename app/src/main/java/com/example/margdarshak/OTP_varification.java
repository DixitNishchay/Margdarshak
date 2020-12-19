package com.example.margdarshak;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OTP_varification extends AppCompatActivity {

    ImageButton ibSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_varification);

        TextView tvResend = findViewById(R.id.tvResend);
        tvResend.setPaintFlags(tvResend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        ibSignup = findViewById(R.id.ibSignup);
        ibSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OTP_varification.this, HomeActivity.class));
            }
        });
    }
}