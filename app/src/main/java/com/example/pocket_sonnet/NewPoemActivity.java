package com.example.pocket_sonnet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import androidx.appcompat.app.AppCompatActivity;

import cz.msebera.android.httpclient.Header;


public class NewPoemActivity extends AppCompatActivity {

    Button findImagesButton;
    String url_string =
            "https://collectionapi.metmuseum.org/public/collection/v1/search?q="
                    + "cat";
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poem);

        // set the vars
        findImagesButton = findViewById(R.id.FindImagesButton);

        // set a listener for making the request
        findImagesButton.setOnClickListener(new View.OnClickListener(){


            // onClick, run the Runnable()
            @Override
            public void onClick(View v) {


                // make a handler for the search
                handler.post(new Runnable(){
                    @Override
                    public void run() {

                        // Apply the search term to the string
                        AsyncHttpClient client = new AsyncHttpClient();

                        client.get(url_string, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                Log.d("success", "object found!");
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                Log.d("failure", "no object found");

                            }
                        });


                    }
                });





            }
        });



    }


}
