package com.example.springexceptionhandler.controller;

import com.example.springexceptionhandler.controller.request.MyRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    /**
     * URI : 존재하는 경우
     * HTTP_METHOD : 잘못된 경우
     * EXCEPTION : HttpRequestMethodNotSupportedException
     */

    /**
     * URI : /test-request-param
     * EXCEPTION : MissingServletRequestParameterException
     * MESSAGE : Required request parameter 'one' for method parameter type String is not present
     * ---
     * URI : /test-request-param?one
     * EXCEPTION : MissingServletRequestParameterException
     * MESSAGE : Required request parameter 'two' for method parameter type String is not present
     */
    @GetMapping("/test-request-param")
    public ResponseEntity<String> requestParam(@RequestParam String one, @RequestParam String two) {
        return ResponseEntity.ok(one + two);
    }

    /**
     * URI : /test-request-body
     * BODY : {}
     * CONTENT_TYPE : application/json
     * EXCEPTION : MethodArgumentNotValidException
     * ---
     * URI : /test-request-body
     * BODY : { "one" : 1, "two" : 2 }
     * CONTENT_TYPE : text/html
     * EXCEPTION : HttpMediaTypeNotSupportedException
     * MESSAGE : Content-Type 'text/html;charset=UTF-8' is not supported
     * ---
     * URI : /test-request-body
     * BODY : { "one" : 1, "two" : 2 }
     * CONTENT_TYPE : javascript
     * EXCEPTION : HttpMediaTypeNotSupportedException
     * MESSAGE : Content-Type 'application/javascript;charset=UTF-8' is not supported
     * ---
     * URI : /test-request-body
     * BODY : { "one" : 1, "two" : 2 }
     * CONTENT_TYPE : none
     * EXCEPTION : HttpMessageNotReadableException
     * MESSAGE : Required request body is missing
     */
    @PostMapping("/test-request-body")
    public ResponseEntity<String> requestBody(@RequestBody @Valid MyRequest myRequest) {
        return ResponseEntity.ok(myRequest.toString());
    }

    /**
     * URI : /test-path-variable/a
     * EXCEPTION : MethodArgumentTypeMismatchException
     * MESSAGE : Failed to convert value of type 'java.lang.String' to required type 'long'; For input string: \"a\"
     */
    @GetMapping("/test-path-variable/{id}")
    public ResponseEntity<Long> pathVariable(@PathVariable long id) {
        return ResponseEntity.ok(id);
    }
}
