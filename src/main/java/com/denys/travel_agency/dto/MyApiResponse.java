package com.denys.travel_agency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MyApiResponse<T>  {
    private String statusCode;
    private String statusMessage;
    private T data;

}
