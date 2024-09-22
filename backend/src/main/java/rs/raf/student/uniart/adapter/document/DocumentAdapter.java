package rs.raf.student.uniart.adapter.document;

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
import rs.raf.student.uniart.model.document.DocumentInfo;
import rs.raf.student.uniart.model.document.animation.Animation;
import rs.raf.student.uniart.model.document.animation.ease.Ease;
import rs.raf.student.uniart.model.document.component.Component;
import rs.raf.student.uniart.model.document.configuration.Configuration;
import rs.raf.student.uniart.model.document.part.Part;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DocumentAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(Document.class);

    public static class Deserializer extends StdDeserializer<Document> {

        protected Deserializer() {
            super(type);
        }

        /**
         * Field dependencies:
         * <ul>
         *     <li>
         *         {@link Document#info} - no dependencies
         *     </li>
         *     <li>
         *         {@link Document#configuration} - no dependencies
         *     </li>
         *     <li>
         *         {@link Document#components} - no dependencies
         *     </li>
         *     <li>
         *         {@link Document#eases} - no dependencies
         *     </li>
         *     <li>
         *         {@link Document#parts} - {@linkplain Component} and {@linkplain Part}
         *     </li>
         *     <li>
         *         {@link Document#animations} - {@linkplain Part} and {@linkplain Ease}
         *     </li>
         * </ul>
         * @param parser Parser used for reading JSON content
         * @param context Context that can be used to access information about this deserialization activity.
         * @return deserialized document
         */
        @Override
        public Document deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            ObjectMapper           objectMapper     = (ObjectMapper) parser.getCodec();
            InjectableValuesMapper injectableValues = InjectableValuesMapper.setup(objectMapper);
            Document               document         = new Document();

            injectableValues.add(Document.class, document);

            JsonNode rootNode          = objectMapper.readTree(parser);
            JsonNode animationsNode    = rootNode.get(Document.Meta.Json.ANIMATIONS);
            JsonNode componentsNode    = rootNode.get(Document.Meta.Json.COMPONENTS);
            JsonNode configurationNode = rootNode.get(Document.Meta.Json.CONFIGURATION);
            JsonNode infoNode          = rootNode.get(Document.Meta.Json.DOCUMENT_INFO);
            JsonNode easesNode         = rootNode.get(Document.Meta.Json.EASES);
            JsonNode partsNode         = rootNode.get(Document.Meta.Json.PARTS);

            document.setInfo(objectMapper.treeToValue(infoNode, DocumentInfo.class));

            document.setConfiguration(objectMapper.treeToValue(configurationNode, Configuration.class));

            for (JsonNode componentNode : componentsNode)
                document.addComponent(objectMapper.treeToValue(componentNode, Component.class));

            for (JsonNode easeNode : easesNode)
                document.addEase(objectMapper.treeToValue(easeNode, Ease.class));

            for (JsonNode partNode : partsNode)
                document.addPart(objectMapper.treeToValue(partNode, Part.class));

            for (JsonNode animationNode : animationsNode)
                document.addAnimation(objectMapper.treeToValue(animationNode, Animation.class));

            injectableValues.remove(Document.class);

            document.parts()
                    .stream()
                    .filter(part -> part.parent() != null && part.parent().name() == null)
                    .forEach(part -> part.setParent(document.part(part.parent().id()))); //TODO: if parent is null throw

            return document;
        }

    }

}
