package rs.raf.student.uniart.model.document.component.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ComponentConfiguration {

    @JsonProperty(Meta.Json.ART)
    private ComponentConfigurationArt art;

    @JsonProperty(Meta.Json.UNITY)
    private ComponentConfigurationUnity unity;

    //region Constructors

    public ComponentConfiguration() { }

    public ComponentConfiguration(ComponentConfigurationArt art, ComponentConfigurationUnity unity) {
        setArt(art);
        setUnity(unity);
    }

    //endregion Constructors

    //region Data

    public ComponentConfiguration setArt(ComponentConfigurationArt art) {
        this.art = art;

        return this;
    }

    public ComponentConfigurationArt art() {
        return art;
    }

    public ComponentConfiguration setUnity(ComponentConfigurationUnity unity) {
        this.unity = unity;

        return this;
    }

    public ComponentConfigurationUnity unity() {
        return unity;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof ComponentConfiguration configuration)
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
