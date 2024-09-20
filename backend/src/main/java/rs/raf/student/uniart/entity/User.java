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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
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
