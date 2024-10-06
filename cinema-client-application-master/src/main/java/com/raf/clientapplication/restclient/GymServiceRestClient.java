package com.raf.clientapplication.restclient;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.dto.ClientDto;
import com.raf.clientapplication.restclient.dto.GymDto;
import com.raf.clientapplication.restclient.dto.GymListDto;

import com.raf.clientapplication.restclient.dto.TrainingListDto;
import okhttp3.*;

public class GymServiceRestClient {

	public static final String URL = "http://localhost:8081/training";


	public static final MediaType JSON
		= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();


	public void editGym(GymDto gymDto) throws  IOException{
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(gymDto));
		System.out.println(gymDto.toString());
		Request request = new Request.Builder()
				.url(URL + "/gym/profile")
				.put(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Uspesno azuriranje");

		}
	}
	public GymDto fetchGym(Integer id) throws IOException{

		id++;
		Request request = new Request.Builder()
				.url(URL + "/gym/" + id)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			GymDto dto = objectMapper.readValue(json, GymDto.class);
			return dto;
		}

		throw new RuntimeException();

	}
	public GymListDto getGyms() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
			.url(URL + "/gym")
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
	public TrainingListDto getTrainings() throws IOException{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/training")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			System.out.println(json);
			return objectMapper.readValue(json, TrainingListDto.class);
		}

		throw new RuntimeException();
	}

	public GymListDto getSortedList() throws  IOException{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/gym/sort")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = "{ \"content\" : " + response.body().string() + " } ";
			System.out.println("Sort Json: " + json);
			return objectMapper.readValue(json, GymListDto.class);
		}

		throw new RuntimeException();
	}
	public TrainingListDto getFilterDanList(String dan) throws IOException{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/training/day/" + dan)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = "{ \"content\" : " + response.body().string() + " } ";
			System.out.println(json);
			return objectMapper.readValue(json, TrainingListDto.class);
		}

		throw new RuntimeException();

	}

	public TrainingListDto getFilterTrenerList(String trener) throws IOException{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/training/trener/" + trener)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = "{ \"content\" : " + response.body().string() + " } ";
			System.out.println(json);
			return objectMapper.readValue(json, TrainingListDto.class);
		}

		throw new RuntimeException();

	}
	public TrainingListDto getFilterTipList(String tip) throws IOException{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/training/type/" + tip)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = "{ \"content\" : " + response.body().string() + " } ";
			System.out.println(json);
			return objectMapper.readValue(json, TrainingListDto.class);
		}

		throw new RuntimeException();

	}
}
