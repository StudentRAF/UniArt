package rs.raf.student.uniart.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
public enum ExceptionType implements IException {

    //region User Service

    /**
     * Invoke when there is no <code>User</code> with <code>username</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li><code>username</code></li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    FIND_USER_NOT_FOUND_USERNAME("""
                                 Could not find user. User with username "{0}" does not exist.\
                                 """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no authenticated <code>User</code>, so authorization <code>token</code> cannot be generated.<br><br>
     * Arguments order:
     * <pre>
     *  <code>no arguments</code>
     * </pre>
     * Severity: <code>Debug</code> | HttpStatus: <code>Unauthorized</code>
     */
    GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER("""
                                                              Could not generate authentication token. There is no authenticated user.\
                                                              """, Severity.DEBUG, HttpStatus.UNAUTHORIZED)

    //endregion User Service
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
