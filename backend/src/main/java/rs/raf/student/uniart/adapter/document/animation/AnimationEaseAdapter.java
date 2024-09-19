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
import rs.raf.student.uniart.model.document.Document;
import rs.raf.student.uniart.model.document.animation.Animation;
import rs.raf.student.uniart.model.document.animation.ease.AnimationEase;
import rs.raf.student.uniart.model.document.animation.ease.Ease;
import rs.raf.student.uniart.model.document.animation.keyframe.Keyframe;
import rs.raf.student.uniart.model.document.part.Part;

import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimationEaseAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(AnimationEase.class);

    public static class Deserializer extends StdDeserializer<AnimationEase> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public AnimationEase deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            InjectableValuesMapper injectableValues = InjectableValuesMapper.from(context);
            ObjectMapper           objectMapper     = injectableValues.objectMapper();
            Document               document         = injectableValues.find(Document.class);
            Animation              animation        = injectableValues.find(Animation.class);
            AnimationEase          animationEase    = new AnimationEase();

            JsonNode rootNode          = objectMapper.readTree(parser);
            JsonNode easeNode          = rootNode.get(AnimationEase.Meta.Json.EASE);
            JsonNode startKeyframeNode = rootNode.get(AnimationEase.Meta.Json.START_KEYFRAME);
            JsonNode endKeyframeNode   = rootNode.get(AnimationEase.Meta.Json.END_KEYFRAME);
            JsonNode partNode          = rootNode.get(AnimationEase.Meta.Json.PART);

            UUID easeId = objectMapper.treeToValue(easeNode, UUID.class);
            Ease ease   = document.ease(easeId); //TODO: if null throw
            animationEase.setEase(ease);

            UUID     startKeyframeId = objectMapper.treeToValue(startKeyframeNode, UUID.class);
            Keyframe startKeyframe   = animation.keyframe(startKeyframeId); //TODO: if null throw
            animationEase.setStartKeyframe(startKeyframe);

            UUID     endKeyframeId = objectMapper.treeToValue(endKeyframeNode, UUID.class);
            Keyframe endKeyframe   = animation.keyframe(endKeyframeId); //TODO: if null throw
            animationEase.setEndKeyframe(endKeyframe);

            UUID partId = objectMapper.treeToValue(partNode, UUID.class);
            Part part   = document.part(partId); //TODO: if null throw
            animationEase.setPart(part);

            return animationEase;
        }

    }

}
