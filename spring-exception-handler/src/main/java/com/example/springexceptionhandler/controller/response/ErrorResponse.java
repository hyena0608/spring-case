package com.example.springexceptionhandler.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record ErrorResponse(String message, String exception) {
}
