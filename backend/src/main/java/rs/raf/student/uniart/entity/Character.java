package rs.raf.student.uniart.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
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
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import rs.raf.student.uniart.model.document.Document;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Accessors(fluent = true, chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = Character.Meta.Table.NAME,
    indexes = {
        @Index(
            name = "index_character_on_name",
            columnList = Character.Meta.Column.NAME
        ),
        @Index(
            name = "index_character_on_project",
            columnList = Character.Meta.Column.PROJECT
        ),
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_character_on_name_and_project",
            columnNames = {
                Character.Meta.Column.NAME,
                Character.Meta.Column.PROJECT
            }
        ),
    }
)
public class Character {

    @Id
    @Column(name = Meta.Column.IDENTIFIER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Meta.Column.NAME, nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn(name = Meta.Column.PROJECT, nullable = false)
    private Project project;

    @Type(JsonType.class)
    @Column(name = Meta.Column.DOCUMENT, columnDefinition = "jsonb", nullable = false)
    private Document document;

    @CreatedDate
    @Column(name = Meta.Column.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = Meta.Column.MODIFIED_AT, nullable = false)
    private LocalDateTime modifiedAt;

    //region Constructors

    public Character() { }

    public Character(Long id, Project project, Document document, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        setId(id);
        setProject(project);
        setDocument(document);
        setCreatedAt(createdAt);
        setModifiedAt(modifiedAt);
    }

    //endregion Constructors

    //region Data

    public Character setId(Long id) {
        this.id = id;

        return this;
    }

    public Long id() {
        return id;
    }

    public Character setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public Character setProject(Project project) {
        this.project = project;

        return this;
    }

    public Project project() {
        return project;
    }

    public Character setDocument(Document document) {
        this.document = document;

        return this;
    }

    public Document document() {
        return document;
    }

    public Character setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public Character setModifiedAt(LocalDateTime modifiedAt) {
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

        if (object instanceof Character projectDocument)
            return Objects.equals(projectDocument.id, id)       &&
                   Objects.equals(projectDocument.name, name)   &&
                   Objects.equals(projectDocument.project, project);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, project);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' id = {1} | name = {2} | project = {3} | document = {4} | createdAt = {5} | modifiedAt = {6} '}'\
                                    """,
                                    Character.class.getSimpleName(), id, name, project, document, createdAt, modifiedAt);
    }

    //endregion Object

    //region Meta

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Meta {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Table {

            public static final String NAME = "character";

        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Column {

            public static final String IDENTIFIER   = "id";
            public static final String NAME         = "name";
            public static final String PROJECT      = "project_id";
            public static final String DOCUMENT     = "document";
            public static final String CREATED_AT   = "created_at";
            public static final String MODIFIED_AT  = "modified_at";

        }

    }

    //endregion Meta

}
