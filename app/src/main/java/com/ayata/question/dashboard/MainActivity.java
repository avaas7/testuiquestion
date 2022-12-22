package com.ayata.question.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
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
    AlertDialog alertDialog;

    Button addButton;
    LoadingDialog loadingDialog;
    String messageAns;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressBar =findViewById(R.id.progressBar);
        createLoadingDialog();

        addButton = findViewById(R.id.button_add_question2);
        modelChatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                question = binding.etAskQuesiton.getEditText().getText().toString().trim();
                binding.tvYouAsked.setText(question.toString().trim());
                binding.etAskQuesiton.getEditText().setText("");
                messageAns = "";
                initChatdata();
            }
        });

    }


    private void initChatdata() {

        modelChatViewModel.getModelChat(question).observe(this, new Observer<ModelChat>() {
            @Override
            public void onChanged(ModelChat modelChat) {
                initReplyChatData(modelChat);
            }
        });

        //run progress bar using observer
        modelChatViewModel.getProgressBarVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer==View.VISIBLE) {
                    alertDialog.show();
                    answerFound(false);
                }
                else
                {
                    alertDialog.dismiss();
                    answerFound(true);
                }
            }
        });

    }

    private void answerFound(boolean b) {
        if (b==true)
        {
            binding.textViewAnwserFound.setText("We found your answer");
            binding.imageViewAnnserFound.setImageResource(R.drawable.ic_check);
        }
    }

    private void initReplyChatData(ModelChat modelChat) {

        if (!alertDialog.isShowing()) {
            messageAns = "";
            binding.tvAnswer2.setText("");
            messageAns = modelChat.getMessage().toString().trim();
            binding.tvAnswer2.setCharacterDelay(150);
            binding.tvAnswer2.animateText(messageAns);
        }
    }

    private void initList() {
        accessItems = new ArrayList<>();
        accessItems.add(new AccessItem("public",R.drawable.ic_public));
        accessItems.add(new AccessItem("private",R.drawable.ic_lock));
    }

    private void  createLoadingDialog() {
        loadingDialog = new LoadingDialog(this);
        alertDialog = loadingDialog.createLoadingDialog();
    }
}