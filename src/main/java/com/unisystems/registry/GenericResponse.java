package com.unisystems.registry;

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
}
