package com.example.listview_adapters.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listview_adapters.R;
import com.example.listview_adapters.databinding.ItemContactBinding;
import com.example.listview_adapters.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private List<Contact> contactList;
    public static final String TAG = "ContactAdapter";

    public ContactAdapter(@NonNull Context context, List<Contact> contactList) {
        super(context, R.layout.item_contact, contactList);
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemContactBinding binding;
        if (convertView == null) {
            Log.d(TAG, "Create: " + position);
            binding = ItemContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        } else {
            Log.d(TAG, "Update: " + position);
            binding = ItemContactBinding.bind(convertView);
        }
        Contact contact = contactList.get(position);
        binding.nameTv.setText(contact.getName());
        binding.numberTv.setText(contact.getNumber());
        return binding.getRoot();
    }
}
