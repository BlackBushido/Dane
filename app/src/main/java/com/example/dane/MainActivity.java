package com.example.dane;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String PHONE_KEY = "com.example.database.PHONE_KEY";
    private PhoneViewModel mPhoneViewModel;
    private PhoneListAdapter mAdapter;
    private FloatingActionButton fab;
    public static ActivityResultLauncher<Intent> mActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new PhoneListAdapter(this);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);

        //insertData();

        mPhoneViewModel.getAllPhones().observe(this, phones -> mAdapter.setPhoneList(phones));

        serveAddPhone();

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

    private void serveAddPhone(){
        mActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){

                        Bundle bundle = result.getData().getExtras();
                        Phone phone = new Phone(
                                null,
                                bundle.getString(addPhoneActivity.PRODUCER_KEY),
                                bundle.getString(addPhoneActivity.MODEL_KEY),
                                bundle.getString(addPhoneActivity.ANDROID_VERSION_KEY),
                                bundle.getString(addPhoneActivity.WEB_SITE_KEY)
                        );

                        mPhoneViewModel.insert(phone);
                        Toast.makeText(getApplicationContext(), getString(R.string.added), Toast.LENGTH_SHORT).show();

                    }
                    else if(result.getResultCode() == RESULT_CANCELED){
                        Toast.makeText(getApplicationContext(), getString(R.string.canceled), Toast.LENGTH_SHORT).show();
                    }
                    else if (result.getResultCode() == RESULT_FIRST_USER + 1 && result.getData() != null){
                        Bundle bundle = result.getData().getExtras();
                        mPhoneViewModel.update(bundle.getParcelable(UpdatePhoneActivity.PHONE_OUT_KEY));
                        Toast.makeText(getApplicationContext(), getString(R.string.updated), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        fab = findViewById(R.id.fabMain);
        fab.setOnClickListener(view -> mActivityLauncher.launch(new Intent(this, addPhoneActivity.class)));
    }

    public void startUpdateActivity(Phone phone) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PHONE_KEY, phone);
        Intent intent = new Intent(this, UpdatePhoneActivity.class);
        intent.putExtras(bundle);
        mActivityLauncher.launch(intent);
    }
}