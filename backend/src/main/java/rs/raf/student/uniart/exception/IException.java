package rs.raf.student.uniart.exception;

import org.springframework.http.HttpStatusCode;

public interface IException {

    String pattern();

    HttpStatusCode httpStatus();

    Severity severity();

}
