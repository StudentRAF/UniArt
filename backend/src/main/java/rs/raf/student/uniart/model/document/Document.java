package rs.raf.student.uniart.model.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import rs.raf.student.uniart.adapter.document.DocumentAdapter;
import rs.raf.student.uniart.model.document.animation.Animation;
import rs.raf.student.uniart.model.document.animation.ease.Ease;
import rs.raf.student.uniart.model.document.component.Component;
import rs.raf.student.uniart.model.document.configuration.Configuration;
import rs.raf.student.uniart.model.document.part.Part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents the root of the UniArt document format.
 * When is serialized, it defines UniArt file format,
 * that in its base is json and should be saved as-is
 * or as json binary.<br><br>
 *
 * Structure:
 * <ul>
 *     <li>
 *         <code>info</code>: document basic information {@linkplain DocumentInfo}
 *     </li>
 *     <li>
 *         <code>configuration</code>: document {@linkplain Configuration}
 *     </li>
 *     <li>
 *         <code>components</code> {@linkplain Component}: list of {@linkplain Component} associated with {@linkplain Part}, each part belongs to one and only one component
 *     </li>
 *     <li>
 *         <code>parts</code>: list of {@linkplain Part} found in animation
 *     </li>
 *     <li>
 *         <code>animations</code>: list of {@linkplain Animation} in document
 *     </li>
 *     <li>
 *         <code>eases</code>: list of {@linkplain Ease} that can be used in animation
 *     </li>
 * </ul>
 */
@JsonDeserialize(using = DocumentAdapter.Deserializer.class)
public class Document {

    @JsonProperty(Meta.Json.DOCUMENT_INFO)
    private DocumentInfo info;

    @JsonProperty(Meta.Json.CONFIGURATION)
    private Configuration configuration;

    @JsonProperty(Meta.Json.COMPONENTS)
    private List<Component> components;

    @JsonProperty(Meta.Json.PARTS)
    private List<Part> parts;

    @JsonProperty(Meta.Json.EASES)
    private List<Ease> eases;

    @JsonProperty(Meta.Json.ANIMATIONS)
    private List<Animation> animations;

    @JsonIgnore
    private final Map<UUID, Component> componentMap = new HashMap<>();

    @JsonIgnore
    private final Map<UUID, Part> partMap = new HashMap<>();

    @JsonIgnore
    private final Map<UUID, Animation> animationMap = new HashMap<>();

    @JsonIgnore
    private final Map<UUID, Ease> easeMap = new HashMap<>();

    //region Constructors

    public Document() {
        animations = new ArrayList<>();
        components = new ArrayList<>();
        eases      = new ArrayList<>();
        parts      = new ArrayList<>();
    }

    public Document(DocumentInfo info, Configuration configuration, List<Component> components, List<Part> parts, List<Animation> animations, List<Ease> eases) {
        setInfo(info);
        setConfiguration(configuration);
        setComponents(components);
        setParts(parts);
        setAnimations(animations);
        setEases(eases);
    }

    //endregion Constructors

    //region Data

    public Document setInfo(DocumentInfo info) {
        this.info = info;

        return this;
    }

    public DocumentInfo info() {
        return info;
    }

    public Document setConfiguration(Configuration configuration) {
        this.configuration = configuration;

        return this;
    }

    public Configuration configuration() {
        return configuration;
    }

    public Document addComponent(Component component) {
        components.add(component);

        componentMap.put(component.id(), component);

        return this;
    }

    public Document setComponents(List<Component> components) {
        this.components = components;
        this.componentMap.clear();

        for (var component : components)
            componentMap.put(component.id(), component);

        return this;
    }

    public Component component(UUID componentId) {
        return componentMap.get(componentId);
    }

    public List<Component> components() {
        return components;
    }

    public Document addPart(Part part) {
        parts.add(part);

        partMap.put(part.id(), part);

        return this;
    }

    public Document setParts(List<Part> parts) {
        this.parts = parts;
        this.partMap.clear();

        for (var part : parts)
            partMap.put(part.id(), part);

        return this;
    }

    public Part part(UUID partId) {
        return partMap.get(partId);
    }

    public List<Part> parts() {
        return parts;
    }

    public Document addAnimation(Animation animation) {
        animations.add(animation);

        animationMap.put(animation.id(), animation);

        return this;
    }

    public Document setAnimations(List<Animation> animations) {
        this.animations = animations;
        this.animationMap.clear();

        for (var animation : animations)
            animationMap.put(animation.id(), animation);

        return this;
    }

    public Animation animation(UUID animationId) {
        return animationMap.get(animationId);
    }

    public List<Animation> animations() {
        return animations;
    }

    public Document addEase(Ease ease) {
        eases.add(ease);

        easeMap.put(ease.id(), ease);

        return this;
    }

    public Document setEases(List<Ease> eases) {
        this.eases = eases;
        this.easeMap.clear();

        for (var ease : eases)
            easeMap.put(ease.id(), ease);

        return this;
    }

    public Ease ease(UUID easeId) {
        return easeMap.get(easeId);
    }

    public List<Ease> eases() {
        return eases;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;

        if (object instanceof Document document)
            return Objects.equals(document.info, info)              &&
                   Objects.equals(document.eases, eases)            &&
                   Objects.equals(document.parts, parts)            &&
                   Objects.equals(document.animations, animations)  &&
                   Objects.equals(document.components, components)  &&
                   Objects.equals(document.configuration, configuration);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(info, eases, parts, animations, components, configuration);
    }

    //endregion Object

    //region Meta

    public static class Meta {

        public static class Json {

            public static final String ANIMATIONS    = "animations";
            public static final String COMPONENTS    = "components";
            public static final String CONFIGURATION = "configuration";
            public static final String DOCUMENT_INFO = "info";
            public static final String EASES         = "eases";
            public static final String PARTS         = "parts";

        }

    }

    //endregion Meta

}
