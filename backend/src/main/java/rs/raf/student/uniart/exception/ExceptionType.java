package rs.raf.student.uniart.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import rs.raf.student.uniart.entity.Organization;
import rs.raf.student.uniart.entity.Project;

@AllArgsConstructor
public enum ExceptionType implements IException {

    //region Organization Service

    /**
     * Invoke when there is no <code>Organization</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li>{@link Organization#name}</li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    FIND_ORGANIZATION_NOT_FOUND_NAME("""
                                     Could not find organization. Organization with name "{0}" does not exist.\
                                     """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no <code>Organization</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li>{@link Organization#name}</li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    UPDATE_ORGANIZATION_NOT_FOUND_NAME("""
                                       Could not update organization. Organization with name "{0}" does not exist.\
                                       """, Severity.DEBUG, HttpStatus.BAD_REQUEST),

    //endregion Organization Service

    //region Project Service

    /**
     * Invoke when there is no <code>Project</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li>{@link Project#name Project.name()}</li>
     *   <li>{@link Project#organization Project.organization().name()}</li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    FIND_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION("""
                                                Could not find project. Project with name "{0}" and organization name "{1}" does not exist.\
                                                """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no <code>Project</code> with <code>organization</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li>{@link Project#organization Project.organization()}</li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    CREATE_PROJECT_NOT_FOUND_ORGANIZATION("""
                                          Could not create project. Organization with name "{0}" does not exist.\
                                          """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no <code>Project</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li>{@link Project#name Project.name()}</li>
     *   <li>{@link Project#organization Project.organization().name()}</li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    UPDATE_PROJECT_NOT_FOUND_NAME_OR_ORGANIZATION("""
                                                  Could not update project. Project with name "{0}" and organization name "{1}" does not exist.\
                                                  """, Severity.DEBUG, HttpStatus.BAD_REQUEST),

    //endregion Project Service

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
                                                              """, Severity.DEBUG, HttpStatus.UNAUTHORIZED),
    /**
     * Invoke when there is no <code>UserRole</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li><code>UserRole.name</code></li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    CREATE_USER_NOT_FOUND_USER_ROLE("""
                                          Could not create user. User role with name "{0}" does not exist.\
                                          """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no <code>UserRole</code> with <code>name</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li><code>UserRole.name</code></li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    UPDATE_USER_NOT_FOUND_USER_ROLE("""
                                          Could not update user. User role with name "{0}" does not exist.\
                                          """, Severity.DEBUG, HttpStatus.BAD_REQUEST),
    /**
     * Invoke when there is no <code>User</code> with <code>username</code>.<br><br>
     * Arguments order:
     * <ol>
     *   <li><code>User.username</code></li>
     * </ol><br>
     * Severity: <code>Debug</code> | HttpStatus: <code>Bad Request</code>
     */
    UPDATE_USER_NOT_FOUND_USERNAME("""
                                         Could not update user. User with username "{0}" does not exist.\
                                         """, Severity.DEBUG, HttpStatus.BAD_REQUEST),

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
