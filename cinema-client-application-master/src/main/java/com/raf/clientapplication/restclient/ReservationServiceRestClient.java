package com.raf.clientapplication.restclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.dto.GymListDto;
import com.raf.clientapplication.restclient.dto.ReservationDto;
import okhttp3.*;

import java.io.IOException;

public class ReservationServiceRestClient {

    public static final String URL = "http://localhost:8081/training";


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public GymListDto getGyms() throws IOException {

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/reservation")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .get()
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            System.out.println(json);
            return objectMapper.readValue(json, GymListDto.class);
        }

        throw new RuntimeException();
    }

    public void addReservation(int userId,int gymId ) throws  IOException{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ReservationDto reservationDto = new ReservationDto(userId, gymId);
        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(reservationDto));
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(URL + "/reservation")
                .header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            System.out.println(json);

        }
    }
}
