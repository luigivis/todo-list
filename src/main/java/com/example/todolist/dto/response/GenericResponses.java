package com.example.todolist.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * The type Generic responses.
 *
 * @param <Body>  the type parameter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponses<Body> {

    @NonNull
    private StatusDTO status;

    private Body body;

    public ResponseEntity<GenericResponses<Body>> getResponseHttp(GenericResponses<Body> genericResponse){
        return ResponseEntity.status(genericResponse.getStatus().getHttpStatus()).body(genericResponse);
    }

    public ResponseEntity<GenericResponses<Body>> getResponseHttpHeader(GenericResponses<Body> genericResponse, HttpHeaders headers){
        return new ResponseEntity<>(genericResponse, headers, genericResponse.getStatus().getHttpStatus());
    }

    public ResponseEntity<?> getResponseHttpObject(Object value, GenericResponses<Body> genericResponse){
        return new ResponseEntity<>(value, genericResponse.getStatus().getHttpStatus());
    }

	public GenericResponses(@NonNull Body body) {
        this.body = body;
    }
}
