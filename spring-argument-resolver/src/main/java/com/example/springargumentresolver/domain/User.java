package com.example.springargumentresolver.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    private String username;
}
