package rs.raf.student.uniart.model.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Version {

    @JsonProperty(Meta.Json.MAJOR)
    private int major;

    @JsonProperty(Meta.Json.MINOR)
    private int minor;

    @JsonProperty(Meta.Json.PATCH)
    private int patch;

    //region Constructors

    public Version() { }

    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    //endregion Constructors

    //region Data

    public Version major(int major) {
        this.major = major;

        return this;
    }

    public Version minor(int minor) {
        this.minor = minor;

        return this;
    }

    public Version patch(int patch) {
        this.patch = patch;

        return this;
    }

    public int major() {
        return major;
    }

    public int minor() {
        return minor;
    }

    public int patch() {
        return patch;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Version version)
            return Objects.equals(version.major, major) &&
                   Objects.equals(version.minor, minor) &&
                   Objects.equals(version.patch, patch);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String MAJOR = "major";
            public static final String MINOR = "minor";
            public static final String PATCH = "patch";

        }

    }

    //endregion Meta

}
