package com.ayata.question.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DownloadManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ayata.question.dashboard.adapter.AccessItem;
import com.ayata.question.dashboard.adapter.AcessAdapter;
import com.ayata.question.R;
import com.ayata.question.dashboard.chatmodel.InputText;
import com.ayata.question.dashboard.chatmodel.ModelChat;
import com.ayata.question.dashboard.chatviewmodel.ChatViewModel;
import com.ayata.question.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    ActivityMainBinding binding;
    private ArrayList<AccessItem> accessItems;
    private AcessAdapter acessAdapter;

    ChatViewModel modelChatViewModel;
    String question;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initList();
        Spinner spinnerAccess = findViewById(R.id.spinner_access);
        acessAdapter = new AcessAdapter(this,accessItems );
        spinnerAccess.setAdapter(acessAdapter);

        spinnerAccess.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AccessItem clickedItem = (AccessItem) adapterView.getItemAtPosition(i);
                String clickedAcessItemName = clickedItem.getName();

            //    Toast.makeText(MainActivity.this, clickedAcessItemName+" clicked!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void SendChatData(View view) {
        question = binding.etAskQuesiton.getEditText().getText().toString().trim();
        binding.tvYouAsked.setText(question.toString().trim());
        binding.etAskQuesiton.getEditText().setText("");
     //   if (validateQuestion()==true) {
            initChatdata();
     //   }
    }

    private void initChatdata() {
        modelChatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        modelChatViewModel.getModelChat(question).observe(this, new Observer<ModelChat>() {
            @Override
            public void onChanged(ModelChat modelChat) {

                initReplyChatData(modelChat);
            }
        });
    }

    private void initReplyChatData(ModelChat modelChat) {
        binding.tvAnswer.setText(modelChat.getMessage().toString().trim());
    }

    private void initList() {
        accessItems = new ArrayList<>();
        accessItems.add(new AccessItem("public",R.drawable.ic_public));
        accessItems.add(new AccessItem("private",R.drawable.ic_lock));
    }

    private boolean validateQuestion() {
        String questionInput = binding.etAskQuesiton.getEditText().getText().toString();

        if (questionInput.isEmpty()) {
            binding.etAskQuesiton.setError("Field can't be empty");
            return false;
        }
        return true;
    }

}