package rs.raf.student.uniart.model.document.animation.ease;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs.raf.student.uniart.adapter.document.animation.AnimationEaseAdapter;
import rs.raf.student.uniart.adapter.document.animation.EaseAdapter;
import rs.raf.student.uniart.adapter.document.animation.KeyframeAdapter;
import rs.raf.student.uniart.adapter.document.part.PartAdapter;
import rs.raf.student.uniart.model.document.animation.keyframe.Keyframe;
import rs.raf.student.uniart.model.document.part.Part;

import java.util.Objects;

@JsonDeserialize(using = AnimationEaseAdapter.Deserializer.class)
public class AnimationEase {

    @JsonSerialize(using = EaseAdapter.Serializer.class)
    @JsonProperty(Meta.Json.EASE)
    private Ease ease;

    @JsonSerialize(using = KeyframeAdapter.Serializer.class)
    @JsonProperty(Meta.Json.START_KEYFRAME)
    private Keyframe startKeyframe;

    @JsonSerialize(using = KeyframeAdapter.Serializer.class)
    @JsonProperty(Meta.Json.END_KEYFRAME)
    private Keyframe endKeyframe;

    @JsonSerialize(using = PartAdapter.Serializer.class)
    @JsonProperty(Meta.Json.PART)
    private Part part;

    //region Constructors

    public AnimationEase() { }

    public AnimationEase(Ease ease, Keyframe startKeyframe, Keyframe endKeyframe, Part part) {
        setEase(ease);
        setStartKeyframe(startKeyframe);
        setEndKeyframe(endKeyframe);
        setPart(part);
    }

    //endregion Constructors

    //region Data

    public AnimationEase setEase(Ease ease) {
        this.ease = ease;

        return this;
    }

    public Ease ease() {
        return ease;
    }

    public AnimationEase setStartKeyframe(Keyframe startKeyframe) {
        this.startKeyframe = startKeyframe;

        return this;
    }

    public Keyframe startKeyframe() {
        return startKeyframe;
    }

    public AnimationEase setEndKeyframe(Keyframe endKeyframe) {
        this.endKeyframe = endKeyframe;

        return this;
    }

    public Keyframe endKeyframe() {
        return endKeyframe;
    }

    public AnimationEase setPart(Part part) {
        this.part = part;

        return this;
    }

    public Part part() {
        return part;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof AnimationEase animationEase)
            return Objects.equals(animationEase.ease, ease)               &&
                   Objects.equals(animationEase.part, part)               &&
                   Objects.equals(animationEase.endKeyframe, endKeyframe) &&
                   Objects.equals(animationEase.startKeyframe, startKeyframe);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ease, part, endKeyframe, startKeyframe);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String EASE           = "ease";
            public static final String END_KEYFRAME   = "end_keyframe";
            public static final String PART           = "part";
            public static final String START_KEYFRAME = "start_keyframe";

        }

    }

    //endregion Meta

}
