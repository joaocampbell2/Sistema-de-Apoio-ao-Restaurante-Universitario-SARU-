package saru.saru_rest.dtos;

public class ResponseDTO {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }
}
