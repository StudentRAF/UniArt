package rs.raf.student.uniart.adapter.document.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.uniart.model.commons.IUniqueIdentifier;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UniqueIdentifierAdapter {

    private static final JavaType type = TypeFactory.defaultInstance().constructType(IUniqueIdentifier.class);

    public static class Serializer extends StdSerializer<IUniqueIdentifier> {

        protected Serializer() {
            super(type);
        }

        @Override
        public void serialize(IUniqueIdentifier value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            generator.writeObject(value.id());
        }

    }

}
