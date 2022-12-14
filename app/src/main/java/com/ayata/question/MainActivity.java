package com.ayata.question;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AccessItem> accessItems;
    private AcessAdapter acessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();
        Spinner spinnerAccess = findViewById(R.id.spinner_access);

        acessAdapter = new AcessAdapter(this,accessItems );
        spinnerAccess.setAdapter(acessAdapter);

        spinnerAccess.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AccessItem clickedItem = (AccessItem) adapterView.getItemAtPosition(i);
                String clickedAcessItemName = clickedItem.getName();

                Toast.makeText(MainActivity.this, clickedAcessItemName+" clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initList() {
        accessItems = new ArrayList<>();
        accessItems.add(new AccessItem("public",R.drawable.ic_public));
        accessItems.add(new AccessItem("private",R.drawable.ic_lock));
    }
}