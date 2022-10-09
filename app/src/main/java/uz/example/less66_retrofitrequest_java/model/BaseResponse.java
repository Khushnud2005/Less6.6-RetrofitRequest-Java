package uz.example.less66_retrofitrequest_java.model;

import java.io.Serializable;

public class BaseResponse<T>{
    String status;
    T data;
    String message;

    public BaseResponse(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
