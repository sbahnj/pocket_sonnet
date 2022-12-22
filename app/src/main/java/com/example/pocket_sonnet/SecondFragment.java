package com.example.pocket_sonnet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pocket_sonnet.databinding.FragmentSecondBinding;

public class SecondFragment extends AppCompatActivity {

    private FragmentSecondBinding binding;

    Button findImagesButton;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);


        // make the vars
       // findImagesButton = findViewById(R.id.find_images_button);


        // Set a listener for making the request


        // onClick, run the Runnable()


    }




   /* public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // button returns to home screen
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    } */




}