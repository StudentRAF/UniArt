package rs.raf.student.uniart.adapter.document.animation;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.uniart.adapter.commons.InjectableValuesMapper;
import rs.raf.student.uniart.model.document.animation.Animation;
import rs.raf.student.uniart.model.document.animation.keyframe.AnimationKeyframe;
import rs.raf.student.uniart.model.document.animation.keyframe.AnimationKeyframePart;
import rs.raf.student.uniart.model.document.animation.keyframe.Keyframe;

import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimationKeyframeAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(AnimationKeyframe.class);

    public static class Deserializer extends StdDeserializer<AnimationKeyframe> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public AnimationKeyframe deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            InjectableValuesMapper injectableValues  = InjectableValuesMapper.from(context);
            ObjectMapper           objectMapper      = injectableValues.objectMapper();
            Animation              animation         = injectableValues.find(Animation.class);
            AnimationKeyframe      animationKeyframe = new AnimationKeyframe();

            JsonNode rootNode                   = objectMapper.readTree(parser);
            JsonNode animationKeyframePartsNode = rootNode.get(AnimationKeyframe.Meta.Json.ANIMATION_KEYFRAME_PARTS);
            JsonNode keyframeNode               = rootNode.get(AnimationKeyframe.Meta.Json.KEYFRAME);

            UUID     keyframeId = objectMapper.treeToValue(keyframeNode, UUID.class);
            Keyframe keyframe   = animation.keyframe(keyframeId); //TODO: if null throw
            animationKeyframe.setKeyframe(keyframe);

            for (JsonNode animationKeyframePartNode : animationKeyframePartsNode)
                animationKeyframe.addAnimationKeyframePart(objectMapper.treeToValue(animationKeyframePartNode, AnimationKeyframePart.class));

            return animationKeyframe;
        }

    }

}
