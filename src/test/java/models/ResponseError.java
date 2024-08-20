package models;

import lombok.Data;

@Data
public class ResponseError {
    String message, requestId;
}
