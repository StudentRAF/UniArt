package rs.raf.student.uniart.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public enum ExceptionType implements IException {
    ;

    private final String     pattern;
    private final Severity   severity;
    private final HttpStatus httpStatus;

    @Override
    public String pattern() {
        return pattern;
    }

    @Override
    public HttpStatusCode httpStatus() {
        return httpStatus;
    }

    @Override
    public Severity severity() {
        return severity;
    }
}
