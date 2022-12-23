package com.mywebsite.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
private int CAMERA_REQUEST;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST&&resultCode==RESULT_OK)
        {
            Bitmap thumpnailBitmap=(Bitmap) data.getExtras().get("data");
            ImageView imgPerson=(ImageView)findViewById(R.id.imgScreen);
            imgPerson.setImageBitmap(thumpnailBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnImg=(Button) findViewById(R.id.btnFoto);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);
                }
                catch(ActivityNotFoundException ex)
                {
                    String errMessage="Ваше устройство не поддерживает работу с камерой";
                    Toast.makeText(MainActivity.this, errMessage.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnSend=(Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView img=(ImageView) findViewById(R.id.imgScreen);
                Drawable drawable=img.getDrawable();
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,baos);
                byte[] b=baos.toByteArray();
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("image",b);
                startActivity(intent);
            }
        });
    }
}