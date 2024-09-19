package rs.raf.student.uniart.model.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.model.commons.Version;

import java.util.Objects;

/**
 * Document information used to validate document type, and if necessary, require update version to the latest.<br><br>
 *
 * Structure:
 * <ul>
 *     <li>
 *         <code>type</code>: declares document type, and it should always be <code>uniart</code>
 *     </li>
 *     <li>
 *         <code>version</code>: declares document version, important when updating to the latest format
 *     </li>
 * </ul>
 */
public class DocumentInfo {

    @JsonProperty(Meta.Json.TYPE)
    private String type;

    @JsonProperty(Meta.Json.TITLE)
    private String title;

    @JsonProperty(Meta.Json.VERSION)
    private Version version;

    //region Constructors

    public DocumentInfo() { }

    public DocumentInfo(String type, String title, Version version) {
        setType(type);
        setTitle(title);
        setVersion(version);
    }

    //endregion Constructors

    //region Data

    public DocumentInfo setType(String type) {
        this.type = type;

        return this;
    }

    public String type() {
        return type;
    }

    public DocumentInfo setTitle(String title) {
        this.title = title;

        return this;
    }

    public String title() {
        return title;
    }

    public DocumentInfo setVersion(Version version) {
        this.version = version;

        return this;
    }

    public Version version() {
        return version;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof DocumentInfo documentInfo)
            return Objects.equals(documentInfo.type, type) &&
                   Objects.equals(documentInfo.title, title) &&
                   Objects.equals(documentInfo.version, version);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, version);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String TITLE   = "type";
            public static final String TYPE    = "title";
            public static final String VERSION = "version";

        }

    }

    //endregion Meta

}
