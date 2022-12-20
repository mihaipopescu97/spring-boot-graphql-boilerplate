package com.travelin.travelinapi.dtos.tokens.refresh;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Data @NoArgsConstructor @ToString
public class RefreshTokenResponse implements Serializable {

    private String accessToken;

    private String refreshToken;

    private String tokenType = "Bearer";

    public RefreshTokenResponse(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefreshTokenResponse that)) return false;
        return getAccessToken().equals(that.getAccessToken()) && getRefreshToken().equals(that.getRefreshToken()) && getTokenType().equals(that.getTokenType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getRefreshToken(), getTokenType());
    }
}
