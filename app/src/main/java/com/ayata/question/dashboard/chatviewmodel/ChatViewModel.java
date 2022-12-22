package com.ayata.question.dashboard.chatviewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ayata.question.dashboard.MainActivity;
import com.ayata.question.dashboard.chatmodel.InputText;
import com.ayata.question.dashboard.chatmodel.ModelChat;
import com.ayata.question.network.ApiService;
import com.ayata.question.network.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends AndroidViewModel {


    private ApiService chatApi;
    private MutableLiveData<ModelChat> modelChat;
    private MutableLiveData<Integer> progressBarVisibility = new MutableLiveData<>();
    InputText inputText;
    private static String token = "d68c19a0a345b7eab78d5e11e991c026ec60db63";


    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatApi = RetrofitClient.getInstance().getApi();
        modelChat = new MutableLiveData<>();
        progressBarVisibility.setValue(View.GONE);
    }

    public MutableLiveData<Integer> getProgressBarVisibility() {
        return progressBarVisibility;
    }


    public LiveData<ModelChat> getModelChat(String inputQuestion)
    {
        progressBarVisibility.setValue(View.VISIBLE);

       // inputText = new InputText(token,inputQuestion);
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("text",inputQuestion);
        chatApi.getModelChat(map).enqueue(new Callback<ModelChat>() {
            @Override
            public void onResponse(Call<ModelChat> call, Response<ModelChat> response) {

                progressBarVisibility.setValue(View.GONE);

                if (response.isSuccessful())
                {
                    modelChat.setValue(response.body());
                }
                else
                {
                    Log.e("tag",response.message());
                }

            }

            @Override
            public void onFailure(Call<ModelChat> call, Throwable t) {
                Log.e("tag",t.getMessage());
                progressBarVisibility.setValue(View.GONE);
            }
        });
        return modelChat;
    }

}
