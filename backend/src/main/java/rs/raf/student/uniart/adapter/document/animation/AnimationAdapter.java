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
import rs.raf.student.uniart.adapter.document.common.UniqueIdentifierAdapter;
import rs.raf.student.uniart.model.document.animation.Animation;
import rs.raf.student.uniart.model.document.animation.ease.AnimationEase;
import rs.raf.student.uniart.model.document.animation.keyframe.AnimationKeyframe;
import rs.raf.student.uniart.model.document.animation.keyframe.Keyframe;

import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimationAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(Animation.class);

    public static class Serializer extends UniqueIdentifierAdapter.Serializer { }

    public static class Deserializer extends StdDeserializer<Animation> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public Animation deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            InjectableValuesMapper injectableValues = InjectableValuesMapper.from(context);
            ObjectMapper           objectMapper     = injectableValues.objectMapper();
            Animation              animation        = new Animation();

            injectableValues.add(Animation.class, animation);

            JsonNode rootNode               = objectMapper.readTree(parser);
            JsonNode animationEasesNode     = rootNode.get(Animation.Meta.Json.ANIMATION_EASES);
            JsonNode animationKeyframesNode = rootNode.get(Animation.Meta.Json.ANIMATION_KEYFRAMES);
            JsonNode identifierNode         = rootNode.get(Animation.Meta.Json.IDENTIFIER);
            JsonNode keyframesNode          = rootNode.get(Animation.Meta.Json.KEYFRAMES);
            JsonNode nameNode               = rootNode.get(Animation.Meta.Json.NAME);

            animation.setId(objectMapper.treeToValue(identifierNode, UUID.class));

            animation.setName(nameNode.textValue());

            for (JsonNode keyframeNode : keyframesNode)
                animation.addKeyframe(objectMapper.treeToValue(keyframeNode, Keyframe.class));

            for (JsonNode animationEaseNode : animationEasesNode)
                animation.addAnimationEase(objectMapper.treeToValue(animationEaseNode, AnimationEase.class));

            for (JsonNode animationKeyframe : animationKeyframesNode)
                animation.addAnimationKeyframe(objectMapper.treeToValue(animationKeyframe, AnimationKeyframe.class));

            injectableValues.remove(Animation.class);

            return animation;
        }

    }

}
