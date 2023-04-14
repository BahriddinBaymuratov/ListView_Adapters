package com.example.listview_adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listview_adapters.adapter.ContactAdapter;
import com.example.listview_adapters.adapter.ContactBaseAdapter;
import com.example.listview_adapters.databinding.ActivityMainBinding;
import com.example.listview_adapters.databinding.ItemContactBinding;
import com.example.listview_adapters.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    private List<String> stringList;   //
    private ActivityMainBinding binding;
    private List<Contact> contactList;
    private ContactBaseAdapter contactBaseAdapter;
    private int lastClickPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadContact();

//        // custom array adapter
//        ContactAdapter contactAdapter = new ContactAdapter(this,contactList);
//        binding.listView.setAdapter(contactAdapter);

        contactBaseAdapter = new ContactBaseAdapter(contactList, new ContactBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Contact contact, int position) {
                contactList.remove(position);
                contactBaseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemEdit(Contact contact, int position) {
                binding.editName.setText(contact.getName());
                binding.editNumber.setText(contact.getNumber());
                lastClickPosition = position;
            }
        });
        binding.listView.setAdapter(contactBaseAdapter);

        binding.listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("contact", contactList.get(i));
            startActivity(intent);
        });

        binding.btnSave.setOnClickListener(view -> {
            String name = binding.editName.getText().toString();
            String number = binding.editNumber.getText().toString();
            if (lastClickPosition != -1) {
                // edit
                contactList.get(lastClickPosition).setName(name);
                contactList.get(lastClickPosition).setNumber(number);
                lastClickPosition = -1;
            } else {
                Contact c1 = new Contact(name, number);
                contactList.add(c1);
            }
            contactBaseAdapter.notifyDataSetChanged();
            binding.editName.setText("");
            binding.editNumber.setText("");
        });

//        loadContactUi();    // 1 - misol

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList );
//        binding.listView.setAdapter(adapter);    // 2 - misol

    }

//    private void loadContactUi() {
//        for (int i = 0; i < contactList.size(); i++) {
////            View itemView = LayoutInflater.from(this).inflate(R.layout.item_contact, null, false);  // layoutInflater orqali ulab olish
//
//            ItemContactBinding itemContactBinding = ItemContactBinding.inflate(getLayoutInflater());
//            itemContactBinding.nameTv.setText(contactList.get(i).getName());
//            itemContactBinding.numberTv.setText(contactList.get(i).getNumber());
//            binding.layout.addView(itemContactBinding.getRoot());
//
//            int finalI = i;
//            itemContactBinding.getRoot().setOnClickListener(view -> {
//                Toast.makeText(this, contactList.get(finalI).getName(), Toast.LENGTH_SHORT).show();
//            });
//
//        }
//    }

    private void loadContact() {
//        stringList = new ArrayList<>();    // 2 -misol
//        for (int i = 0; i < 100; i++) {
//            stringList.add("Android -> " + i);
//        }

        contactList = new ArrayList<>();   // 1 - misol
//        for (int i = 0; i < 100; i++) {
//            contactList.add(new Contact("Android -> " + i, "+998934322234" + i));
//        }
    }
}