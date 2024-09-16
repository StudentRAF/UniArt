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
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(fluent = true, chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = "\"user\"",
    indexes = {
        @Index(name = "index_user_on_email", columnList = "email"),
        @Index(name = "index_user_on_username", columnList = "username"),
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_on_email", columnNames = { "email" }),
        @UniqueConstraint(name = "unique_user_on_username", columnNames = { "username" }),
    }
)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 44)
    private String password;

    @Column(nullable = false, length = 24)
    private String salt;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRoleEntity userRole;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private boolean access = true;

    @Column(nullable = false)
    private boolean activated = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

}
