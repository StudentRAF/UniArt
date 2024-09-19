package rs.raf.student.uniart.model.document.component.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.Position;

import java.util.Objects;

public class ComponentConfigurationUnity {

    @JsonProperty(Meta.Json.PIVOT)
    private Position<Double> pivot;

    //region Constructors

    public ComponentConfigurationUnity() { }

    public ComponentConfigurationUnity(Position<Double> pivot) {
        setPivot(pivot);
    }

    //endregion Constructors

    //region Data

    public ComponentConfigurationUnity setPivot(Position<Double> pivot) {
        this.pivot = pivot;

        return this;
    }

    public Position<Double> pivot() {
        return pivot;
    }

    //endregion Data

    //region Object

    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof ComponentConfigurationUnity configuration)
            return Objects.equals(configuration.pivot, pivot);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pivot);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String PIVOT = "pivot";

        }

    }

    //endregion Meta

}
