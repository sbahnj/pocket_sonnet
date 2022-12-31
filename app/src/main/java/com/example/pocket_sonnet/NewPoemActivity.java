package com.example.pocket_sonnet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;


public class NewPoemActivity extends AppCompatActivity {

    Button findImagesButton;

    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poem);

        // set the vars
        findImagesButton = findViewById(R.id.FindImagesButton);
        final String[] info = {""};
        ImageView imageView = findViewById(R.id.imageView);



        // set a listener for making the request
        findImagesButton.setOnClickListener(new View.OnClickListener(){


            // onClick, run the Runnable()
            @Override
            public void onClick(View v) {


                    // make a handler for the search
                    handler.post(new Runnable() {


                                         @Override
                                         public void run () {


                                                 // define the range
                                                 int max = 470000;
                                                 int min = 1;
                                                 int range = max - min + 1;

                                                 int rand = (int) (Math.random() * range) + min;

                                                 int object_ID = rand;

                                                 Log.d("object ID", String.valueOf(object_ID));


                                                 // TODO: catch case if the object doesn't have an image


                                                 String url_string =
                                                         "https://collectionapi.metmuseum.org/public/collection/v1/objects/" + object_ID;


                                                 // Find the object matching the random ID
                                                 AsyncHttpClient client = new AsyncHttpClient();

                                                 client.get(url_string, new AsyncHttpResponseHandler() {
                                                     @Override
                                                     public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                                                         String object_info = new String(responseBody);
                                                         info[0] = object_info;

                                                         Log.d("object info", object_info);

                                                         // Get the image url from the object info:

                                                         // Split the object info on commas
                                                         String[] object_info_split = object_info.split(",");

                                                         // Find the part that contains the small object image
                                                         for(int k = 0; k < object_info_split.length; k++){
                                                             if(object_info_split[k].contains("primaryImageSmall")){
                                                                 Log.d("object image", object_info_split[k]);

                                                                 // Get the web address for the image
                                                                 String[] object_image_split = object_info_split[k].split("\"");
                                                                 Log.d("image address", Arrays.toString(object_image_split));

                                                                 // If the length of the array is less than 4 (meaning no image url), try again
                                                                 if (object_image_split.length < 4){

                                                                     Log.d("no image message", "no image found");
                                                                     // If no image is found, click the button again
                                                                     findImagesButton.callOnClick();
                                                                 }

                                                                 // Load the image into the ImageView
                                                                 Picasso.get().load(object_image_split[3]).into(imageView);






                                                             }
                                                         }





                                                     }



                                                     @Override
                                                     public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                                         Log.d("failure", "no object found");

                                                         // If no object is found, click the button again
                                                         findImagesButton.callOnClick();



                                                     }
                                                 });



                                         }

            }
                    );

            }
        });

    }


}
