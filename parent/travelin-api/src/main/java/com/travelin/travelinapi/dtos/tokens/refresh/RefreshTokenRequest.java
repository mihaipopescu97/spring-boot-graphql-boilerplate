package com.travelin.travelinapi.dtos.tokens.refresh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class RefreshTokenRequest implements Serializable {

    private String refreshToken;

}
