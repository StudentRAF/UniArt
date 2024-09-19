package rs.raf.student.uniart.adapter.document.part;

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
import rs.raf.student.uniart.model.document.Document;
import rs.raf.student.uniart.model.document.component.Component;
import rs.raf.student.uniart.model.document.part.Part;
import rs.raf.student.uniart.model.document.part.configuration.PartConfiguration;

import java.io.IOException;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PartAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(Part.class);

    public static class Serializer extends UniqueIdentifierAdapter.Serializer { }

    public static class Deserializer extends StdDeserializer<Part> {

        protected Deserializer() {
            super(type);
        }

        @Override
        public Part deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
            InjectableValuesMapper injectableValues = InjectableValuesMapper.from(context);
            ObjectMapper           objectMapper     = injectableValues.objectMapper();
            Document               document         = injectableValues.find(Document.class);
            Part                   part             = new Part();

            JsonNode rootNode          = objectMapper.readTree(parser);
            JsonNode componentNode     = rootNode.get(Part.Meta.Json.COMPONENT);
            JsonNode configurationNode = rootNode.get(Part.Meta.Json.CONFIGURATION);
            JsonNode identifierNode    = rootNode.get(Part.Meta.Json.IDENTIFIER);
            JsonNode layerNode         = rootNode.get(Part.Meta.Json.LAYER);
            JsonNode nameNode          = rootNode.get(Part.Meta.Json.NAME);
            JsonNode parentNode        = rootNode.get(Part.Meta.Json.PARENT);

            part.setId(objectMapper.treeToValue(identifierNode, UUID.class));

            part.setName(nameNode.textValue());

            part.setLayer(layerNode.intValue());

            part.setConfiguration(objectMapper.treeToValue(configurationNode, PartConfiguration.class));

            UUID      componentId = objectMapper.treeToValue(componentNode, UUID.class);
            Component component   = document.component(componentId); //TODO: if null throw
            part.setComponent(component);

            UUID parentId = objectMapper.treeToValue(parentNode, UUID.class);
            Part parent   = document.part(parentId);
            part.setParent(parentId != null ? (parent != null ? parent : new Part().setId(parentId)) : null);

            return part;
        }

    }

}
