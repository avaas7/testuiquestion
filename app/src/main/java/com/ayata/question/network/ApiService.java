package com.ayata.question.network;

import com.ayata.question.dashboard.chatmodel.InputText;
import com.ayata.question.dashboard.chatmodel.ModelChat;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("chatai")
    @FormUrlEncoded
    Call<ModelChat> getModelChat(@FieldMap Map<String,String> params);
//field name token, message object to json, retrofit ko flow bujne
}
