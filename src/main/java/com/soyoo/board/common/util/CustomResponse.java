package com.soyoo.board.common.util;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.soyoo.board.dto.response.ResponseDto;

public class CustomResponse {

    public static ResponseEntity<ResponseDto> success(){
        ResponseDto body = new ResponseDto("SU", "SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    public static ResponseEntity<ResponseDto> databaseError() {

        ResponseDto errorBody = 
            new ResponseDto("DE", "Database Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);

    }

    public static ResponseEntity<ResponseDto> validationFaild() {

        ResponseDto errorBody = new ResponseDto("VF", "validationFaile");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoardNumber(){

        ResponseDto errorBody = new ResponseDto("NB", "Non-Existent Board Number");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistUserEmail(){ // 니가 누구인지 모르겠다 : 인증

        ResponseDto errorBody = new ResponseDto("NU", "Non-Existent User Email");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
}

public static ResponseEntity<ResponseDto> noPermissions(){ // 니가 누구인지 알았는게 니는 권한이 없다!

    ResponseDto errorBody = new ResponseDto("NP", "No Permissions");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody);

}

}