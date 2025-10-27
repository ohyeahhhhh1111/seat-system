package com.example.seatsystem.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全域例外處理類別（Global Exception Handler）
 * 
 * 當系統中任何 Controller 層發生未處理的例外（Exception）時，
 * 會統一由這個類別攔截並回傳適當的錯誤訊息給前端。
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Invalid Input"); // 錯誤類型描述
        error.put("message", ex.getMessage()); // 錯誤詳細訊息
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // 回傳 400 狀態碼
    }
    //處理所有其他未特別定義的例外（Exception）
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Server Error"); // 通用錯誤描述
        error.put("message", ex.getMessage()); // 錯誤訊息
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
