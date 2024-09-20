package rs.raf.student.uniart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Accessors(fluent = true, chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = UserRole.Meta.Table.NAME,
    indexes = @Index(
        name = "index_user_role_on_name",
        columnList = UserRole.Meta.Column.NAME
    ),
    uniqueConstraints = @UniqueConstraint(
        name = "unique_user_role_on_name",
        columnNames = UserRole.Meta.Column.NAME
    )
)
public class UserRole {

    @Id
    @Column(name = Meta.Column.IDENTIFIER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Meta.Column.NAME, nullable = false, length = 32)
    private String name;

    @CreatedDate
    @Column(name = Meta.Column.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = Meta.Column.MODIFIED_AT, nullable = false)
    private LocalDateTime modifiedAt;

    //region Constructors

    public UserRole() { }

    public UserRole(Long id, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        setId(id);
        setName(name);
        setCreatedAt(createdAt);
        setModifiedAt(modifiedAt);
    }

    //endregion Constructors

    //region Data

    public UserRole setId(Long id) {
        this.id = id;

        return this;
    }

    public Long id() {
        return id;
    }

    public UserRole setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public UserRole setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public UserRole setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;

        return this;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserRole role)
            return Objects.equals(role.id, id) &&
                   Objects.equals(role.name, name);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String toString() {
        return MessageFormat.format("""
                                    UserRole: '{' id = {0} | name = {1} | createdAt = {2} | modifiedAt = {3} '}'\
                                    """, id, name, createdAt, modifiedAt);
    }

    //endregion Object

    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Table {

            public static final String NAME = "user_role";

        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Column {

            public static final String IDENTIFIER  = "id";
            public static final String NAME        = "name";
            public static final String CREATED_AT  = "created_at";
            public static final String MODIFIED_AT = "modified_at";

        }

    }

    //endregion Meta

}
