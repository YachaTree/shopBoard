package com.tree.shop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice //모든 controller 파일의 에러를 캐치
public class MyExceptionHandler {

//모든 에러에 적용
//    @ExceptionHandler(Exception.class) // 같은 클래스에 있는 모든 api에서 Exception 발생시 안의 코드 실행해줌
//    public ResponseEntity<String> handler() {
//        return ResponseEntity.status(400).body("ㅇㅇ");
//    }

    //특정에러에 적용
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handler() {
        return ResponseEntity.status(400).body("ㅇㅇ");
    }

}
