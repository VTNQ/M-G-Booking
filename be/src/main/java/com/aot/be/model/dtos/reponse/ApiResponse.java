package com.aot.be.model.dtos.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<T>(HttpStatus.OK.value(), "OK",data);
    }
    public static <T> ApiResponse<T> created(String message) {
        return new ApiResponse<T>(HttpStatus.CREATED.value(),message,null );
    }
    public static <T> ApiResponse<T> badRequest(String message, T data) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), message, data);
    }
    public static <T> ApiResponse<T> badGateway(String message, T data) {
        return new ApiResponse<>(HttpStatus.BAD_GATEWAY.value(), message, data);
    }

    public static <T> ApiResponse<T> unauthorized(String message, T data) {
        return new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), message, data);
    }
    public static <T> ApiResponse<T> success(String message,T data) {
        return new ApiResponse<>(HttpStatus.OK.value(), message,data);
    }
}
