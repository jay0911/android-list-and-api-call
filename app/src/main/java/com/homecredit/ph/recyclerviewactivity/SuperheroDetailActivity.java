package com.homecredit.ph.recyclerviewactivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SuperheroDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_detail);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("imageUrl") && getIntent().hasExtra("name")){
            String imageUrl = getIntent().getStringExtra("imageUrl");
            String imageName = getIntent().getStringExtra("name");

            setImage(imageUrl, imageName);
        }
    }

    private void setImage(String imageUrl, String imageName){
        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);

        ImageView image = findViewById(R.id.imageViewer);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }
}

