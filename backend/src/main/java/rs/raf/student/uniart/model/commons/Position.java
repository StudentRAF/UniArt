package rs.raf.student.uniart.model.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Position<Type> {

    @JsonProperty(Meta.Json.X)
    private Type x;

    @JsonProperty(Meta.Json.Y)
    private Type y;

    //region Constructors

    public Position() { }

    public Position(Type x, Type y) {
        this.x = x;
        this.y = y;
    }

    //endregion Constructors

    //region Data

    public Position<Type> x(Type x) {
        this.x = x;

        return this;
    }

    public Position<Type> y(Type y) {
        this.y = y;

        return this;
    }

    public Type x() {
        return x;
    }

    public Type y() {
        return y;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Position<?> position)
            return Objects.equals(position.x, x) &&
                   Objects.equals(position.y, y);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String X = "x";
            public static final String Y = "y";

        }

    }

    //endregion Meta

}
