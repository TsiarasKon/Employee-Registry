package com.unisystems.registry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

public class GenericResponse<T> {
    private T data;
    private GenericError error;

    public GenericResponse(T data) {
        this.data = data;
    }

    public GenericResponse(GenericError error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public GenericError getError() {
        return error;
    }

    public void setError(GenericError error) {
        this.error = error;
    }

    public ResponseEntity getResponseEntity(@Nullable MultiValueMap<String, String> headers, HttpStatus errorStatus) {
        if (getError() != null){
            return new ResponseEntity(
                    getError(),
                    headers,
                    errorStatus
            );
        } else{
            return new ResponseEntity(
                    getData(),
                    null,
                    HttpStatus.OK
            );
        }
    }
}
