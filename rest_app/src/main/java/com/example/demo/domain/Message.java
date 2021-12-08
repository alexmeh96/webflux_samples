package com.example.demo.domain;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private String data;

    public Message() {};

    public Message(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public Message(String data) {
        this.data = data;
    }
}
