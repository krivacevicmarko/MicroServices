package com.raf.clientapplication.restclient.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientListDto {

    private List<ClientDto> content = new ArrayList<>();

    public ClientListDto() {
        System.out.println(content.toString());
    }

    public ClientListDto(List<ClientDto> content) {
        this.content = content;
    }

    public List<ClientDto> getContent() {
        return content;
    }

    public void setContent(List<ClientDto> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ClientListDto{" +
                "content=" + content +
                '}';
    }
}
