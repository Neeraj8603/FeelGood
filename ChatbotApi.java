package com.example.feelgood.network;

import com.example.feelgood.models.ChatbotRequest;
import com.example.feelgood.models.ChatbotResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChatbotApi {
    @POST("chatbot/message")
    Call<ChatbotResponse> sendMessage(@Body ChatbotRequest request);
}
