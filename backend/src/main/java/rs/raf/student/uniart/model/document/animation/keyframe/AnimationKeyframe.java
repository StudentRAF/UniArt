package rs.raf.student.uniart.model.document.animation.keyframe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import rs.raf.student.uniart.adapter.document.animation.AnimationKeyframeAdapter;
import rs.raf.student.uniart.adapter.document.animation.KeyframeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(using = AnimationKeyframeAdapter.Deserializer.class)
public class AnimationKeyframe {

    @JsonSerialize(using = KeyframeAdapter.Serializer.class)
    @JsonProperty(Meta.Json.KEYFRAME)
    private Keyframe keyframe;

    @JsonProperty(Meta.Json.ANIMATION_KEYFRAME_PARTS)
    private List<AnimationKeyframePart> animationKeyframeParts;

    //region Constructors

    public AnimationKeyframe() {
        animationKeyframeParts = new ArrayList<>();
    }

    public AnimationKeyframe(Keyframe keyframe, List<AnimationKeyframePart> animationKeyframeParts) {
        setKeyframe(keyframe);
        setAnimationKeyframeParts(animationKeyframeParts);
    }

    //endregion Constructors

    //region Data

    public AnimationKeyframe setKeyframe(Keyframe keyframe) {
        this.keyframe = keyframe;

        return this;
    }

    public Keyframe keyframe() {
        return keyframe;
    }

    public AnimationKeyframe addAnimationKeyframePart(AnimationKeyframePart animationKeyframePart) {
        animationKeyframeParts.add(animationKeyframePart);

        return this;
    }

    public AnimationKeyframe setAnimationKeyframeParts(List<AnimationKeyframePart> animationKeyframeParts) {
        this.animationKeyframeParts = animationKeyframeParts;

        return this;
    }

    public List<AnimationKeyframePart> animationKeyframeParts() {
        return animationKeyframeParts;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof AnimationKeyframe animationKeyframe)
            return Objects.equals(animationKeyframe.animationKeyframeParts, animationKeyframeParts) &&
                   Objects.equals(animationKeyframe.keyframe, keyframe);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(animationKeyframeParts, keyframe);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String KEYFRAME                 = "keyframe";
            public static final String ANIMATION_KEYFRAME_PARTS = "data";

        }

    }

    //endregion Meta

}
