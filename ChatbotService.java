package com.example.feelgood;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatbotService {
    private static final String BASE_URL = "https://api.yourchatbot.com/";

    public void getResponse(String message, ChatbotCallback callback) {
        ChatbotApi api = ApiClient.getClient(BASE_URL).create(ChatbotApi.class);
        Call<ChatbotResponse> call = api.sendMessage(new ChatbotRequest(message));
        call.enqueue(new Callback<ChatbotResponse>() {
            @Override
            public void onResponse(Call<ChatbotResponse> call, Response<ChatbotResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getResponse());
                } else {
                    callback.onError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ChatbotResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface ChatbotCallback {
        void onSuccess(String response);
        void onError(String error);
    }
}
