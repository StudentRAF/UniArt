package rs.raf.student.uniart.model.document.animation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rs.raf.student.uniart.adapter.document.animation.AnimationAdapter;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;
import rs.raf.student.uniart.model.document.animation.ease.AnimationEase;
import rs.raf.student.uniart.model.document.animation.keyframe.AnimationKeyframe;
import rs.raf.student.uniart.model.document.animation.keyframe.Keyframe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(using = AnimationAdapter.Deserializer.class)
public class Animation implements IUniqueIdentifier {

    @JsonProperty(Meta.Json.IDENTIFIER)
    private UUID id;

    @JsonProperty(Meta.Json.NAME)
    private String name;

    @JsonProperty(Meta.Json.KEYFRAMES)
    private List<Keyframe> keyframes;

    @JsonProperty(Meta.Json.ANIMATION_KEYFRAMES)
    private List<AnimationKeyframe> animationKeyframes;

    @JsonProperty(Meta.Json.ANIMATION_EASES)
    private List<AnimationEase> animationEases;

    @JsonIgnore
    private final Map<UUID, Keyframe> keyframeMap = new HashMap<>();

    //region Constructors

    public Animation() {
        keyframes          = new ArrayList<>();
        animationKeyframes = new ArrayList<>();
        animationEases     = new ArrayList<>();
    }

    public Animation(UUID id, String name, List<Keyframe> keyframes, List<AnimationKeyframe> animationKeyframes, List<AnimationEase> animationEases) {
        setId(id);
        setName(name);
        setKeyframes(keyframes);
        setAnimationKeyframes(animationKeyframes);
        setAnimationEases(animationEases);
    }

    //endregion Constructors

    //region Data

    public Animation setId(UUID id) {
        this.id = id;

        return this;
    }

    @Override
    public UUID id() {
        return id;
    }

    public Animation setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public Animation addKeyframe(Keyframe keyframe) {
        keyframes.add(keyframe);

        keyframeMap.put(keyframe.id(), keyframe);

        return this;
    }

    public Animation setKeyframes(List<Keyframe> keyframes) {
        this.keyframes = keyframes;
        this.keyframeMap.clear();

        for (var keyframe : keyframes)
            keyframeMap.put(keyframe.id(), keyframe);

        return this;
    }

    public Keyframe keyframe(UUID keyframeId) {
        return keyframeMap.get(keyframeId);
    }

    public List<Keyframe> keyframes() {
        return keyframes;
    }

    public Animation addAnimationKeyframe(AnimationKeyframe animationKeyframe) {
        animationKeyframes.add(animationKeyframe);

        return this;
    }

    public Animation setAnimationKeyframes(List<AnimationKeyframe> animationKeyframes) {
        this.animationKeyframes = animationKeyframes;

        return this;
    }

    public List<AnimationKeyframe> animationKeyframes() {
        return animationKeyframes;
    }

    public Animation addAnimationEase(AnimationEase animationEase) {
        animationEases.add(animationEase);

        return this;
    }

    public Animation setAnimationEases(List<AnimationEase> animationEases) {
        this.animationEases = animationEases;

        return this;
    }

    public List<AnimationEase> eases() {
        return animationEases;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof Animation animation)
            return Objects.equals(animation.id, id);

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

            public static final String ANIMATION_EASES     = "eases";
            public static final String ANIMATION_KEYFRAMES = "data";
            public static final String IDENTIFIER          = "id";
            public static final String KEYFRAMES           = "keyframes";
            public static final String NAME                = "name";

        }

    }

    //endregion Meta

}
