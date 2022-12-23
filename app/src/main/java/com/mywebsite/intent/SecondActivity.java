package com.mywebsite.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
Button send;
EditText address, subject, emailText;
ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        foto=(ImageView) findViewById(R.id.imgFoto);
        Bundle bundle=this.getIntent().getExtras();
        byte[] b=bundle.getByteArray("image");
        Bitmap bmp= BitmapFactory.decodeByteArray(b, 0,b.length);
        foto.setImageBitmap(bmp);

        Button send=(Button) findViewById(R.id.btnSendEmail);
        address=(EditText) findViewById(R.id.edtEmail);
        subject=(EditText) findViewById(R.id.subject);
        emailText=(EditText) findViewById(R.id.text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent=new Intent(Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{address.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT,emailText.getText().toString());
                SecondActivity.this.startActivity(Intent.createChooser(emailIntent, "Отправка письма"));
            }
        });
    }
}