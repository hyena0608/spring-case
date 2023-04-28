package com.example.springexceptionhandler.controller.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record MyRequest(@NotBlank String one, String two) {
}
