package rs.raf.student.uniart.adapter.document.component;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import rs.raf.student.uniart.adapter.document.common.UniqueIdentifierAdapter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComponentAdapter {

    public static class Serializer extends UniqueIdentifierAdapter.Serializer { }

}
