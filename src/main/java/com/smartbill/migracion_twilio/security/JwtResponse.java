package com.smartbill.migracion_twilio.security;

import com.fasterxml.jackson.annotation.JsonProperty;

//Clase S3
public record JwtResponse(@JsonProperty(value = "access_token") String accessToken) {
}
