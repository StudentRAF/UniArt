package rs.raf.student.uniart.model.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Size<Type> {

    @JsonProperty(Meta.Json.WIDTH)
    private Type width;

    @JsonProperty(Meta.Json.HEIGHT)
    private Type height;

    //region Constructors

    public Size() { }

    public Size(Type width, Type height) {
        this.width  = width;
        this.height = height;
    }

    //endregion Constructors

    //region Data

    public Size<Type> width(Type width) {
        this.width = width;

        return this;
    }

    public Size<Type> height(Type height) {
        this.height = height;

        return this;
    }

    public Type width() {
        return width;
    }

    public Type height() {
        return height;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Size<?> size)
            return Objects.equals(size.width, width) &&
                   Objects.equals(size.height, height);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String HEIGHT = "height";
            public static final String WIDTH  = "width";

        }

    }

    //endregion Meta

}
