package com.example.wildflyee.bean;

import java.util.ArrayList;
import java.util.List;

public class TableBean {

    private final List<ResponseBean> responses = new ArrayList<>();

    public void add(ResponseBean response) {
        responses.add(response);
    }

    public List<ResponseBean> getResponses() {
        return responses;
    }

}