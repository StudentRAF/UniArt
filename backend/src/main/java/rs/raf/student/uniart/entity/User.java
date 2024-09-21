package rs.raf.student.uniart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Accessors(fluent = true, chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = User.Meta.Table.NAME,
    indexes = {
        @Index(
            name = "index_user_on_email",
            columnList = User.Meta.Column.EMAIL
        ),
        @Index(
            name = "index_user_on_username",
            columnList = User.Meta.Column.USERNAME
        ),
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_user_on_email",
            columnNames = User.Meta.Column.EMAIL
        ),
        @UniqueConstraint(
            name = "unique_user_on_username",
            columnNames = User.Meta.Column.USERNAME
        ),
    }
)
public class User implements UserDetails {

    @Id
    @Column(name = Meta.Column.IDENTIFIER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Meta.Column.FIRST_NAME, nullable = false, length = 32)
    private String firstName;

    @Column(name = Meta.Column.LAST_NAME, nullable = false, length = 32)
    private String lastName;

    @Column(name = Meta.Column.USERNAME, nullable = false, length = 64)
    private String username;

    @Column(name = Meta.Column.PASSWORD, nullable = false, length = 44)
    private String password;

    @Column(name = Meta.Column.SALT, nullable = false, length = 24)
    private String salt;

    @Column(name = Meta.Column.EMAIL, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = Meta.Column.ROLE, nullable = false)
    private UserRole userRole;

    @Column(name = Meta.Column.DATE_OF_BIRTH)
    private LocalDate dateOfBirth;

    @Column(name = Meta.Column.ACCESS, nullable = false)
    private boolean access = true;

    @Column(name = Meta.Column.ACTIVATED, nullable = false)
    private boolean activated = true;

    @CreatedDate
    @Column(name = Meta.Column.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = Meta.Column.MODIFIED_AT, nullable = false)
    private LocalDateTime modifiedAt;

    //region Constructors

    public User() { }

    public User(Long id, String firstName, String lastName, String username, String password, String salt, String email, UserRole userRole,
                LocalDate dateOfBirth, boolean access, boolean activated, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setSalt(salt);
        setEmail(email);
        setUserRole(userRole);
        setDateOfBirth(dateOfBirth);
        setAccess(access);
        setActivated(activated);
        setCreatedAt(createdAt);
        setModifiedAt(modifiedAt);
    }

    //endregion Constructors

    //region Data

    public User setId(Long id) {
        this.id = id;

        return this;
    }

    public Long id() {
        return id;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public User setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public User setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    public User setSalt(String salt) {
        this.salt = salt;

        return this;
    }

    public String salt() {
        return salt;
    }

    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;

        return this;
    }

    public UserRole userRole() {
        return userRole;
    }

    public User setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        return this;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public User setAccess(boolean access) {
        this.access = access;

        return this;
    }

    public boolean access() {
        return access;
    }

    public User setActivated(boolean activated) {
        this.activated = activated;

        return this;
    }

    public boolean activated() {
        return activated;
    }

    public User setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public User setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;

        return this;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

    //endregion Data
    
    //region UserDetails

    @Override
    public boolean isEnabled() {
        return access;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return activated;
    }

    //endregion UserDetails

    //region Object

    public boolean equals(final Object object) {
        if (object == this)
            return true;

        if (object instanceof User user)
            return Objects.equals(user.id, id)               &&
                   Objects.equals(user.salt, salt)           &&
                   Objects.equals(user.email, email)         &&
                   Objects.equals(user.access, access)       &&
                   Objects.equals(user.lastName, lastName)   &&
                   Objects.equals(user.password, password)   &&
                   Objects.equals(user.username, username)   &&
                   Objects.equals(user.userRole, userRole)   &&
                   Objects.equals(user.activated, activated) &&
                   Objects.equals(user.firstName, firstName) &&
                   Objects.equals(user.dateOfBirth, dateOfBirth);

        return false;
    }

    public int hashCode() {
        return Objects.hash(id, salt, email, access, lastName, password, username, userRole, activated, firstName, dateOfBirth);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' id = {1} | firstName = {2} | lastName = {3} | username = {4} | password = {5} | salt = {6} | \
                                    email = {7} | userRole = {8} | dateOfBirth = {9} | access = {10} | activated = {11} | createdAt = {12} | \
                                    modifiedAt = {13} '}'\
                                    """,
                                    User.class.getSimpleName(), id, firstName, lastName, username, password, salt, email, userRole, dateOfBirth,
                                    access, activated, createdAt, modifiedAt);
    }
    
    //endregion Object
    
    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Table {

            public static final String NAME = "\"user\"";

        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Column {

            public static final String IDENTIFIER    = "id";
            public static final String FIRST_NAME    = "first_name";
            public static final String LAST_NAME     = "last_name";
            public static final String USERNAME      = "username";
            public static final String PASSWORD      = "password";
            public static final String SALT          = "salt";
            public static final String EMAIL         = "email";
            public static final String ROLE          = "user_role_id";
            public static final String DATE_OF_BIRTH = "date_of_birth";
            public static final String ACCESS        = "access";
            public static final String ACTIVATED     = "activated";
            public static final String CREATED_AT    = "created_at";
            public static final String MODIFIED_AT   = "modified_at";

        }

    }

    //endregion Meta

}
