package com.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class GymListDto {


	private List<GymDto> content = new ArrayList<>();

	public GymListDto() {
		System.out.println(content.toString());
	}

	public GymListDto(List<GymDto> content) {
		this.content = content;
	}

	public List<GymDto> getContent() {
		return content;
	}

	public void setContent(List<GymDto> content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "GymListDto{" +
				"content=" + content +
				'}';
	}
}
