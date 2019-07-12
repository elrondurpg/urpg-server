package com.pokemonurpg.dto;

public class ResponseDto {
    private int status;
    private String message;
    private DataDto data;

    public ResponseDto() {

    }

    public static ResponseDto notFound() {
        ResponseDto response = new ResponseDto();
        response.setStatus(404);
        response.setMessage("Not Found");
        return response;
    }

    public static ResponseDto ok() {
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("OK");
        return response;
    }

    public static ResponseDto ok(DataDto data) {
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("OK");
        response.setData(data);
        return response;
    }

    public static ResponseDto badRequest() {
        ResponseDto response = new ResponseDto();
        response.setStatus(400);
        response.setMessage("Bad Request");
        return response;
    }

    public static ResponseDto unauthorized() {
        ResponseDto response = new ResponseDto();
        response.setStatus(403);
        response.setMessage("Unauthorized");
        return response;
    }

    public static ResponseDto internalServerError() {
        ResponseDto response = new ResponseDto();
        response.setStatus(500);
        response.setMessage("Internal Server Error");
        return response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataDto getData() {
        return data;
    }

    public void setData(DataDto data) {
        this.data = data;
    }
}
