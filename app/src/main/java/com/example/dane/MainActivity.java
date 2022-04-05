package com.example.dane;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        insertData();

        mPhoneViewModel.getAllPhones().observe(this, phones -> {
            mAdapter.setPhoneList(phones);
        });
    }

    private void insertData() {
        ArrayList<Phone> phoneList = new ArrayList<>(Arrays.asList(
                new Phone(null, "Samsung", "Galaxy-S22", "11.0", "https://www.samsung.com/pl/smartphones/galaxy-s22/"),
                new Phone(null, "Samsung", "Galaxy Z Fold3 5G", "9.0", "https://www.samsung.com/pl/smartphones/galaxy-z-fold3-5g/"),
                new Phone(null, "Samsung", "Galaxy Z Flip3 5G", "9.0", "https://www.samsung.com/pl/smartphones/galaxy-z-flip3-5g/"),
                new Phone(null, "Samsung", "Galaxy M52 5G", "9.0", "https://www.samsung.com/pl/smartphones/galaxy-m/galaxy-m52-5g-light-blue-128gb-sm-m526blbdeue/"),
                new Phone(null, "Samsung", "Galaxy A53 5G", "9.0", "https://www.samsung.com/pl/smartphones/galaxy-a/galaxy-a53-5g-awesome-blue-128gb-sm-a536blbneue/"),
                new Phone(null, "ASUS", "Zenfone 8", "10.0", "https://www.asus.com/pl/Mobile/Phones/ZenFone/Zenfone-8/"),
                new Phone(null, "ASUS", "ROG Phone 5s Pro", "10.0", "https://rog.asus.com/pl/phones/rog-phone-5s-pro-model/"),
                new Phone(null, "ASUS", "ROG Phone 5s", "10.0", "https://rog.asus.com/pl/phones/rog-phone-5s-model/"),
                new Phone(null, "ASUS", "ROG Phone 5s Ultimate", "10.0", "https://rog.asus.com/pl/phones/rog-phone-5-ultimate-model/"),
                new Phone(null, "Nokia", "Nokia-G21", "11.0", "https://www.nokia.com/phones/pl_pl/nokia-g-21")

        ));

        phoneList.forEach((v) -> mPhoneViewModel.insert(v));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.first_option){
            mPhoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}