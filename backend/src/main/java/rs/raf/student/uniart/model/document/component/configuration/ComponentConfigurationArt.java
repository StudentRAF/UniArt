package rs.raf.student.uniart.model.document.component.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.Size;

import java.util.Objects;

public class ComponentConfigurationArt {

    @JsonProperty(Meta.Json.SIZE)
    private Size<Double> size;

    //region Constructors

    public ComponentConfigurationArt() { }

    public ComponentConfigurationArt(Size<Double> size) {
        setSize(size);
    }

    //endregion Constructors

    //region Data

    public ComponentConfigurationArt setSize(Size<Double> size) {
        this.size = size;

        return this;
    }

    public Size<Double> size() {
        return size;
    }

    //endregion Data

    //region Object

    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof ComponentConfigurationArt configuration)
            return Objects.equals(configuration.size, size);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String SIZE = "size";

        }

    }

    //endregion Meta

}
