package rs.raf.student.uniart.model.document.animation.ease;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;

import java.util.Objects;
import java.util.UUID;

public class Ease implements IUniqueIdentifier {

    @JsonProperty(Meta.Json.IDENTIFIER)
    private UUID id;

    @JsonProperty(Meta.Json.NAME)
    private String name;

    //region Constructors

    public Ease() { }

    public Ease(UUID id, String name) {
        setId(id);
        setName(name);
    }

    //endregion Constructors

    //region Data

    public Ease setId(UUID id) {
        this.id = id;

        return this;
    }

    @Override
    public UUID id() {
        return id;
    }

    public Ease setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof Ease ease)
            return Objects.equals(ease.id, id);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String IDENTIFIER = "id";
            public static final String NAME       = "name";

        }

    }

    //endregion Meta

}
