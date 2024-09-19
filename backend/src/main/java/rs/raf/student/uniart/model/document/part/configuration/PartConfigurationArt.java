package rs.raf.student.uniart.model.document.part.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.Position;

import java.util.Objects;

public class PartConfigurationArt {

    @JsonProperty(Meta.Json.POSITION)
    private Position<Double> position;

    //region Constructors

    public PartConfigurationArt() { }

    public PartConfigurationArt(Position<Double> position) {
        setPosition(position);
    }

    //endregion Constructors

    //region Data

    public PartConfigurationArt setPosition(Position<Double> position) {
        this.position = position;

        return this;
    }

    public Position<Double> position() {
        return position;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof PartConfigurationArt configuration)
            return Objects.equals(configuration.position, position);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String POSITION = "position";

        }

    }

    //endregion Meta

}
