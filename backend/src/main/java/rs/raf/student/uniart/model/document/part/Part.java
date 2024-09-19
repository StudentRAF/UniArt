package rs.raf.student.uniart.model.document.part;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs.raf.student.uniart.adapter.document.component.ComponentAdapter;
import rs.raf.student.uniart.adapter.document.part.PartAdapter;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;
import rs.raf.student.uniart.model.document.component.Component;
import rs.raf.student.uniart.model.document.part.configuration.PartConfiguration;

import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = PartAdapter.Deserializer.class)
public class Part implements IUniqueIdentifier {

    @JsonProperty(Meta.Json.IDENTIFIER)
    private UUID id;

    @JsonProperty(Meta.Json.NAME)
    private String name;

    @JsonSerialize(using = ComponentAdapter.Serializer.class)
    @JsonProperty(Meta.Json.COMPONENT)
    private Component component;

    @JsonSerialize(using = PartAdapter.Serializer.class)
    @JsonProperty(Meta.Json.PARENT)
    private Part parent;

    @JsonProperty(Meta.Json.LAYER)
    private int layer;

    @JsonProperty(Meta.Json.CONFIGURATION)
    private PartConfiguration configuration;

    //region Constructors

    public Part() { }

    public Part(UUID id, String name, Component component, Part parent, int layer, PartConfiguration configuration) {
        setId(id);
        setName(name);
        setComponent(component);
        setParent(parent);
        setLayer(layer);
        setConfiguration(configuration);
    }

    //endregion Constructors

    //region Data

    public Part setId(UUID id) {
        this.id = id;

        return this;
    }

    @Override
    public UUID id() {
        return id;
    }

    public Part setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public Part setComponent(Component component) {
        this.component = component;

        return this;
    }

    public Component component() {
        return component;
    }

    public Part setParent(Part parent) {
        this.parent = parent;

        return this;
    }

    public Part parent() {
        return parent;
    }

    public Part setLayer(int layer) {
        this.layer = layer;

        return this;
    }

    public int layer() {
        return layer;
    }

    public Part setConfiguration(PartConfiguration configuration) {
        this.configuration = configuration;

        return this;
    }

    public PartConfiguration configuration() {
        return configuration;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof Part part)
            return Objects.equals(part.id, id);

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

            public static final String COMPONENT     = "component";
            public static final String CONFIGURATION = "configuration";
            public static final String IDENTIFIER    = "id";
            public static final String LAYER         = "layer";
            public static final String NAME          = "name";
            public static final String PARENT        = "parent";

        }

    }

    //endregion Meta

}
