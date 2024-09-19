package rs.raf.student.uniart.model.document.part.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PartConfiguration {

    @JsonProperty(Meta.Json.ART)
    private PartConfigurationArt art;

    //region Constructors

    public PartConfiguration() { }

    public PartConfiguration(PartConfigurationArt art) {
        setArt(art);
    }

    //endregion Constructors

    //region Data

    public PartConfiguration setArt(PartConfigurationArt art) {
        this.art = art;

        return this;
    }

    public PartConfigurationArt art() {
        return art;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof PartConfiguration configuration)
            return Objects.equals(configuration.art, art);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(art);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String ART = "art";

        }

    }

    //endregion Meta

}
