package com.example.listview_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.listview_adapters.databinding.ActivityDetailBinding;
import com.example.listview_adapters.databinding.ActivityMainBinding;
import com.example.listview_adapters.model.Contact;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        binding.textView.setText(contact.getName() + " " + contact.getNumber());

    }
}