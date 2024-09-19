package rs.raf.student.uniart.model.document.animation.keyframe;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;

import java.util.Objects;
import java.util.UUID;

public class Keyframe implements IUniqueIdentifier {

    @JsonProperty(Meta.Json.IDENTIFIER)
    private UUID id;

    @JsonProperty(Meta.Json.FRAME)
    private int frame;

    //region Constructors

    public Keyframe() { }

    public Keyframe(UUID id, int frame) {
        setId(id);
        setFrame(frame);
    }

    //endregion Constructors

    //region Data

    public Keyframe setId(UUID id) {
        this.id = id;

        return this;
    }

    @Override
    public UUID id() {
        return id;
    }

    public Keyframe setFrame(int frame) {
        this.frame = frame;

        return this;
    }

    public int frame() {
        return frame;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof Keyframe keyframe)
            return Objects.equals(keyframe.id, id);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String FRAME      = "frame";
            public static final String IDENTIFIER = "id";

        }

    }

    //endregion Meta

}
