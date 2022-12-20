package com.travelin.travelinapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 1/2/2023, Mon
 **/

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ErrorResponse implements Serializable {

    private HttpStatus status;
    private String body;

    private String uri;
    private String message;

}
