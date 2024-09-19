package rs.raf.student.uniart.model.document.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ConfigurationArt {

    @JsonProperty(Meta.Json.EXPORT_SCALE)
    private float exportScale;

    @JsonProperty(Meta.Json.STROKE)
    private float stroke;

    //region Constructors

    public ConfigurationArt() { }

    public ConfigurationArt(float exportScale, float stroke) {
        setExportScale(exportScale);
        setStroke(stroke);
    }

    //endregion Constructors

    //region Data

    public ConfigurationArt setExportScale(float exportScale) {
        this.exportScale = exportScale;

        return this;
    }

    public float exportScale() {
        return exportScale;
    }

    public ConfigurationArt setStroke(float stroke) {
        this.stroke = stroke;

        return this;
    }

    public float stroke() {
        return stroke;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof ConfigurationArt configuration)
            return Objects.equals(configuration.exportScale, exportScale) &&
                   Objects.equals(configuration.stroke, stroke);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(exportScale, stroke);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String EXPORT_SCALE = "export_scale";
            public static final String STROKE       = "stroke";

        }

    }

    //endregion Meta

}
