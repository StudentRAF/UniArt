package rs.raf.student.uniart.model.document.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ConfigurationUnity {

    @JsonProperty(Meta.Json.PIXELS_PER_UNIT)
    private int pixelsPerUnit;

    //region Constructors

    public ConfigurationUnity() { }

    public ConfigurationUnity(int pixelsPerUnit) {
        setPixelsPerUnit(pixelsPerUnit);
    }

    //endregion Constructors

    //region Data

    public ConfigurationUnity setPixelsPerUnit(int pixelsPerUnit) {
        this.pixelsPerUnit = pixelsPerUnit;

        return this;
    }

    public int pixelsPerUnit() {
        return pixelsPerUnit;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ConfigurationUnity configuration)
            return Objects.equals(configuration.pixelsPerUnit, pixelsPerUnit);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixelsPerUnit);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String PIXELS_PER_UNIT = "pixels_per_unit";

        }

    }

    //endregion Meta

}
