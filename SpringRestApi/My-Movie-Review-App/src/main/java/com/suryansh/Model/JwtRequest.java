package com.suryansh.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@Setter
@CrossOrigin
public class JwtRequest {
    private String userName;
    private String userPassword;
}
