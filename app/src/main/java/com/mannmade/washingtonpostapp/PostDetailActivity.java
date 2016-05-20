package com.mannmade.washingtonpostapp;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        if(getIntent() != null){
            int index = getIntent().getIntExtra("index", -1);
            TextView detail = (TextView) findViewById(R.id.post_content);
            if(JSONParser.getInstance().jsonArrayList!= null){
                //uncomment next line to have article title on detail screen!
                getSupportActionBar().setTitle(JSONParser.getInstance().jsonArrayList.get(index).title);
                detail.setText(Html.fromHtml(JSONParser.getInstance().jsonArrayList.get(index).content));
            }
        }
    }
}
