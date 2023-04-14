package com.example.listview_adapters.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listview_adapters.databinding.ItemContactBinding;
import com.example.listview_adapters.model.Contact;

import java.util.List;

public class ContactBaseAdapter extends BaseAdapter {

    private List<Contact> contactList;
    private ContactBaseAdapter.OnItemClickListener listener;
    private static final String TAG = "ContactBaseAdapter";

    public ContactBaseAdapter(List<Contact> contactList, ContactBaseAdapter.OnItemClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
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

        binding.btnDelete.setOnClickListener(view -> {
            listener.onItemDelete(contact, position);
        });
        binding.btnEdit.setOnClickListener(view -> {
            listener.onItemEdit(contact, position);
        });
        return binding.getRoot();
    }

    public interface OnItemClickListener {
        void onItemDelete(Contact contact, int position);

        void onItemEdit(Contact contact, int position);
    }
}
