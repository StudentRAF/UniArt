package rs.raf.student.uniart.model.document.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Configuration {

    @JsonProperty(Meta.Json.ART)
    private ConfigurationArt art;

    @JsonProperty(Meta.Json.UNITY)
    private ConfigurationUnity unity;

    //region Constructors

    public Configuration() { }

    public Configuration(ConfigurationArt art, ConfigurationUnity unity) {
        setArt(art);
        setUnity(unity);
    }

    //endregion Constructors

    //region Data

    public Configuration setArt(ConfigurationArt art) {
        this.art = art;

        return this;
    }

    public ConfigurationArt art() {
        return art;
    }

    public Configuration setUnity(ConfigurationUnity unity) {
        this.unity = unity;

        return this;
    }

    public ConfigurationUnity unity() {
        return unity;
    }


    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Configuration configuration)
            return Objects.equals(configuration.art, art) &&
                   Objects.equals(configuration.unity, unity);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(art, unity);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String ART   = "art";
            public static final String UNITY = "unity";

        }

    }

    //endregion Meta

}
