package com.example.pocket_sonnet;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

//TODO: once title is found, display images and title on Compose screen

public class MakeTitleActivity extends AppCompatActivity {

    Button make_title_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_title);

        // Set the screen items
        make_title_button = findViewById(R.id.find_title_button);
        TextView textView = findViewById(R.id.poem_title);

        final Handler handler = new Handler();



        // Generate a title using random title from Met API
        make_title_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Make a handler for the search
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        // define the range for the ID number
                        int max = 470000;
                        int min = 1;
                        int range = max - min + 1;

                        int rand = (int) (Math.random() * range) + min;

                        int object_ID = rand;

                        String url_string =
                                "https://collectionapi.metmuseum.org/public/collection/v1/objects/" + object_ID;


                        // Find the object matching the random ID
                        AsyncHttpClient client = new AsyncHttpClient();

                        client.get(url_string, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                String object_info = new String(responseBody);


                                Log.d("object info", object_info);

                                // Get the title from the object info
                                // Split the object info on commas
                                String[] object_info_split = object_info.split(",");

                                // Find the part that contains the title
                                for(int k = 0; k < object_info_split.length; k++){
                                    if(object_info_split[k].contains("title")){
                                        Log.d("object title", object_info_split[k]);


                                        String[] split_title = object_info_split[k].split(":");
                                        // split at colon, use second item
                                        // Load the title into the TextView
                                        textView.setText(split_title[1]);




                                    }


                                }

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                Log.d("failure", "no object found for title generation");



                            }
                        });





                    }
                });







            }
        });





    }

}
