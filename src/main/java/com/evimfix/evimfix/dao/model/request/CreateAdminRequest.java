package com.evimfix.evimfix.dao.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
