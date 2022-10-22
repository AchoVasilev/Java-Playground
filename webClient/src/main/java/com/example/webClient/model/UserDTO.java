package com.example.webClient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonProperty("data")
    private DataDTO dataDTO;

    @JsonProperty("support")
    private SupportDTO supportDTO;

    @JsonProperty("data")
    public DataDTO getData() {
        return dataDTO;
    }

    @JsonProperty("data")
    public void setData(DataDTO dataDTO) {
        this.dataDTO = dataDTO;
    }

    @JsonProperty("support")
    public SupportDTO getSupport() {
        return supportDTO;
    }

    @JsonProperty("support")
    public void setSupport(SupportDTO supportDTO) {
        this.supportDTO = supportDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "data=" + dataDTO +
                ", support=" + supportDTO +
                '}';
    }
}
