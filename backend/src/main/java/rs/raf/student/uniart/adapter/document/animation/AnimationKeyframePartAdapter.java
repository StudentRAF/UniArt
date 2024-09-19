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
import rs.raf.student.uniart.model.commons.Position;
import rs.raf.student.uniart.model.document.Document;
import rs.raf.student.uniart.model.document.animation.keyframe.AnimationKeyframePart;
import rs.raf.student.uniart.model.document.part.Part;

import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimationKeyframePartAdapter {

    private static final JavaType type         = TypeFactory.defaultInstance().constructType(AnimationKeyframePart.class);
    private static final JavaType positionType = TypeFactory.defaultInstance().constructParametricType(Position.class, Double.class);

    public static class Deserializer extends StdDeserializer<AnimationKeyframePart> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public AnimationKeyframePart deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            InjectableValuesMapper injectableValues      = InjectableValuesMapper.from(context);
            ObjectMapper           objectMapper          = injectableValues.objectMapper();
            Document               document              = injectableValues.find(Document.class);
            AnimationKeyframePart  animationKeyframePart = new AnimationKeyframePart();

            JsonNode rootNode     = objectMapper.readTree(parser);
            JsonNode partNode     = rootNode.get(AnimationKeyframePart.Meta.Json.PART);
            JsonNode positionNode = rootNode.get(AnimationKeyframePart.Meta.Json.POSITION);
            JsonNode rotationNode = rootNode.get(AnimationKeyframePart.Meta.Json.ROTATION);

            animationKeyframePart.setRotation(rotationNode.floatValue());

            animationKeyframePart.setPosition(objectMapper.treeToValue(positionNode, positionType));

            UUID partId = objectMapper.treeToValue(partNode, UUID.class);
            Part part   = document.part(partId); //TODO: if null throw
            animationKeyframePart.setPart(part);

            return animationKeyframePart;
        }

    }

}
