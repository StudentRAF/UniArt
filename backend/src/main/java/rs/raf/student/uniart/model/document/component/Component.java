package rs.raf.student.uniart.model.document.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;
import rs.raf.student.uniart.model.document.component.configuration.ComponentConfiguration;

import java.util.Objects;
import java.util.UUID;

public class Component implements IUniqueIdentifier {

    @JsonProperty(Meta.Json.IDENTIFIER)
    private UUID id;

    @JsonProperty(Meta.Json.NAME)
    private String name;

    @JsonProperty(Meta.Json.CONFIGURATION)
    private ComponentConfiguration configuration;

    //region Constructors

    public Component() { }

    public Component(UUID id, String name, ComponentConfiguration configuration) {
        setId(id);
        setName(name);
        setConfiguration(configuration);
    }

    //endregion Constructors

    //region Data

    public Component setId(UUID id) {
        this.id = id;

        return this;
    }

    @Override
    public UUID id() {
        return id;
    }

    public Component setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public Component setConfiguration(ComponentConfiguration configuration) {
        this.configuration = configuration;

        return this;
    }

    public ComponentConfiguration configuration() {
        return configuration;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof Component component)
            return Objects.equals(component.id, id);

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

            public static final String CONFIGURATION = "configuration";
            public static final String IDENTIFIER    = "id";
            public static final String NAME          = "name";

        }

    }

    //endregion Meta

}
