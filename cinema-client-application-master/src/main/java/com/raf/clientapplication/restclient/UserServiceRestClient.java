package com.raf.clientapplication.restclient;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.clientapplication.ClientApplication;
import com.raf.clientapplication.restclient.dto.*;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.w3c.dom.ls.LSOutput;

public class UserServiceRestClient {

	//public static final String URL = "http://localhost:8084/users";
	public static final String URL = "http://localhost:8080/user";

	public static final MediaType JSON
			= MediaType.get("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();
	ObjectMapper objectMapper = new ObjectMapper();

	public String login(String email, String password) throws IOException {

		TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

		Request request = new Request.Builder()
				.url(URL + "/client/login")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

			return dto.getToken();
		}

		throw new RuntimeException("Invalid username or password");
	}

	public String loginManager(String email, String password) throws IOException{

		TokenRequestDto tokenRequestDto = new TokenRequestDto(email, password);

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

		Request request = new Request.Builder()
				.url(URL + "/manager/login")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			System.out.println("dobro");
			TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

			return dto.getToken();
		}

		throw new RuntimeException("Invalid username or password");
	}


	public void register(ClientDto dto) throws IOException {

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(dto));
		System.out.println(dto.toString());
		Request request = new Request.Builder()
				.url(URL + "/client/register")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Uspesna registracija");

		}

	}

	public void registerManager(ManagerDto managerDto) throws IOException{

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(managerDto));
		System.out.println(managerDto.toString());
		Request request = new Request.Builder()
				.url(URL + "/manager/register")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Uspesna registracija");

		}
	}

	public ClientListDto getClients() throws IOException {

		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Request request = new Request.Builder()
				.url(URL + "/client")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			System.out.println(json);
			return objectMapper.readValue(json, ClientListDto.class);
		}

		throw new RuntimeException();
	}
	public void blockClient(Integer clientId) throws IOException{
		System.out.println(clientId);
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientId));
		Request request = new Request.Builder()
				.url(URL + "/admin/" + clientId +"/block")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Uspesan block");

		}
	}
	public void unblockClient(Integer clientId) throws IOException{
		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientId));
		Request request = new Request.Builder()
				.url(URL + "/admin/" + clientId +"/unblock")
				.post(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Uspesan unblock");

		}
	}

	public void editUserProfile(ClientDto clientDto) throws IOException {

		RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientDto));

		Request request = new Request.Builder()
				.url(URL + "/client/profile")
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.put(body)
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			System.out.println("Nalog je uspesno promenjen");

		}

	}

	public ClientDto fetchUser(Integer id) throws IOException {
		System.out.println(id);
		Request request = new Request.Builder()
				.url(URL + "/client/" + id)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			ClientDto dto = objectMapper.readValue(json, ClientDto.class);
			System.out.println(dto.toString());
			return dto;
		}

		throw new RuntimeException("fetchUser error");
	}

	public ManagerDto fetchManager(Integer id) throws  IOException{
		System.out.println(id);
		Request request = new Request.Builder()
				.url(URL + "/manager/" + id)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			ManagerDto dto = objectMapper.readValue(json, ManagerDto.class);
			System.out.println(dto.toString());
			return dto;
		}

		throw new RuntimeException("fetchManager error");
	}

	public Integer fetchManagerId(String email) throws IOException {

		System.out.println(email);
		Request request = new Request.Builder()
				.url(URL + "/manager/email/" + email)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			ManagerDto managerDto = objectMapper.readValue(json, ManagerDto.class);

			return managerDto.getId();
		}

		throw new RuntimeException("Invalid username or password");
	}

	public String fetchManagerRoomName(String email) throws IOException {

		System.out.println(email);
		Request request = new Request.Builder()
				.url(URL + "/manager/email/" + email)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			ManagerDto managerDto = objectMapper.readValue(json, ManagerDto.class);

			return managerDto.getRoomName();
		}

		throw new RuntimeException("Invalid username or password");
	}


	public Integer fetchID(String email) throws IOException {

		Request request = new Request.Builder()
				.url(URL + "/client/email/" + email)
				.header("Authorization", "Bearer " + ClientApplication.getInstance().getToken())
				.get()
				.build();

		Call call = client.newCall(request);

		Response response = call.execute();

		if (response.code() == 200) {
			String json = response.body().string();
			ClientDto clientDto = objectMapper.readValue(json, ClientDto.class);

			return clientDto.getId();
		}

		throw new RuntimeException("Invalid username or password");
	}
}