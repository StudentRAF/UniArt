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

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Accessors(fluent = true, chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = Project.Meta.Table.NAME,
    indexes = {
        @Index(
            name = "index_project_on_name",
            columnList = Project.Meta.Column.NAME
        ),
        @Index(
            name = "index_project_on_organization",
            columnList = Project.Meta.Column.ORGANIZATION
        ),
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_project_on_name_and_organization",
            columnNames = {
                Project.Meta.Column.NAME,
                Project.Meta.Column.ORGANIZATION
            }
        ),
    }
)
public class Project {

    @Id
    @Column(name = Meta.Column.IDENTIFIER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Meta.Column.NAME, nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = Meta.Column.ORGANIZATION, nullable = false)
    private Organization organization;

    //TODO: add document that is saved as jsonb

    @CreatedDate
    @Column(name = Meta.Column.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = Meta.Column.MODIFIED_AT, nullable = false)
    private LocalDateTime modifiedAt;

    //region Constructors

    public Project() { }

    public Project(Long id, String name, Organization organization, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        setId(id);
        setName(name);
        setOrganization(organization);
        setCreatedAt(createdAt);
        setModifiedAt(modifiedAt);
    }

    //endregion Constructors

    //region Data

    public Project setId(Long id) {
        this.id = id;

        return this;
    }

    public Long id() {
        return id;
    }

    public Project setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public Project setOrganization(Organization organization) {
        this.organization = organization;

        return this;
    }

    public Organization organization() {
        return organization;
    }

    public Project setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Project setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;

        return this;
    }

    public LocalDateTime modifiedAt() {
        return modifiedAt;
    }

    //endregion Data

    //region Object

    public boolean equals(final Object object) {
        if (object == this)
            return true;

        if (object instanceof Project project)
            return Objects.equals(project.id, id)                  &&
                   Objects.equals(project.name, name)              &&
                   Objects.equals(project.organization, organization);

        return false;
    }

    public int hashCode() {
        return Objects.hash(id, name, organization);
    }

    public String toString() {
        return MessageFormat.format("""
                                    Project: '{' id = {0} | name = {1} | organization = {2} | createdAt = {3} | modifiedAt = {4} '}'\
                                    """, id, name, organization, createdAt, modifiedAt);
    }

    //endregion Object

    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Table {

            public static final String NAME = "project";

        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Column {

            public static final String IDENTIFIER   = "id";
            public static final String NAME         = "name";
            public static final String ORGANIZATION = "organization_id";
            public static final String DOCUMENT     = "document";
            public static final String CREATED_AT   = "created_at";
            public static final String MODIFIED_AT  = "modified_at";

        }

    }

    //endregion Meta

}
