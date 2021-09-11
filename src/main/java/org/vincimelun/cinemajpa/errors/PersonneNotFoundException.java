package org.vincimelun.cinemajpa.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "La personne est inexistante")
public class PersonneNotFoundException extends RuntimeException {
}
