package com.raf.clientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.dto.ClientListDto;
import com.raf.clientapplication.restclient.dto.NotificationListDto;
import com.raf.clientapplication.restclient.dto.TrainingListDto;
import okhttp3.*;

import java.io.IOException;

public class NotificationServiceRestClient {
    public static final String URL = "http://localhost:8082/notification";


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public NotificationListDto getNotifications() throws IOException{

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/notification")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, NotificationListDto.class);
        }

        throw new RuntimeException();

    }

    public NotificationListDto getFilterTip(String tip) throws IOException{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/notification/tip/" + tip)
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = "{ \"content\" : " + response.body().string() + " } ";
            System.out.println(json);
            return objectMapper.readValue(json, NotificationListDto.class);
        }

        throw new RuntimeException();

    }

}
