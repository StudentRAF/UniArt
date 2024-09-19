package rs.raf.student.uniart.model.document.animation.keyframe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs.raf.student.uniart.adapter.document.animation.AnimationKeyframePartAdapter;
import rs.raf.student.uniart.adapter.document.part.PartAdapter;
import rs.raf.student.uniart.model.commons.Position;
import rs.raf.student.uniart.model.document.part.Part;

import java.util.Objects;

@JsonDeserialize(using = AnimationKeyframePartAdapter.Deserializer.class)
public class AnimationKeyframePart {

    @JsonSerialize(using = PartAdapter.Serializer.class)
    @JsonProperty(Meta.Json.PART)
    private Part part;

    @JsonProperty(Meta.Json.POSITION)
    private Position<Double> position;

    @JsonProperty(Meta.Json.ROTATION)
    private float rotation;

    //region Constructors

    public AnimationKeyframePart() { }

    public AnimationKeyframePart(Part part, Position<Double> position, float rotation) {
        setPart(part);
        setPosition(position);
        setRotation(rotation);
    }

    //endregion Constructors

    //region Data

    public AnimationKeyframePart setPart(Part part) {
        this.part = part;

        return this;
    }

    public Part part() {
        return part;
    }

    public AnimationKeyframePart setPosition(Position<Double> position) {
        this.position = position;

        return this;
    }

    public Position<Double> position() {
        return position;
    }

    public AnimationKeyframePart setRotation(float rotation) {
        this.rotation = rotation;

        return this;
    }

    public float rotation() {
        return rotation;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof AnimationKeyframePart animationKeyframePart)
            return Objects.equals(animationKeyframePart.part, part)         &&
                   Objects.equals(animationKeyframePart.position, position) &&
                   Objects.equals(animationKeyframePart.rotation, rotation);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, position, rotation);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String PART     = "part";
            public static final String POSITION = "position";
            public static final String ROTATION = "rotation";

        }

    }

    //endregion Meta

}
