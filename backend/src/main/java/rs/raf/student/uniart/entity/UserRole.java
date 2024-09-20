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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
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
