package rs.raf.student.uniart.adapter.commons;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InjectableValuesMapper extends InjectableValues {

    private final Map<String, Object> valuesMap = new HashMap<>();

    public InjectableValuesMapper(ObjectMapper objectMapper) {
        add(ObjectMapper.class, objectMapper);
    }

    public static InjectableValuesMapper from(DeserializationContext context) throws JsonMappingException {
        InjectableValues injectableValues = ((ObjectMapper) context.findInjectableValue(ObjectMapper.class.getName(), null, null)).getInjectableValues();

        if (!(injectableValues instanceof InjectableValuesMapper injectableValuesMapper))
            throw new IllegalStateException("Context InjectableValues is not InjectableValuesMapper. Found: " + injectableValues.getClass().getName() );

        return injectableValuesMapper;
    }

    public static InjectableValuesMapper setup(ObjectMapper objectMapper) {
        InjectableValuesMapper injectableValuesMapper = new InjectableValuesMapper(objectMapper);

        objectMapper.setInjectableValues(injectableValuesMapper);

        return injectableValuesMapper;
    }

    public InjectableValuesMapper addNamed(String name, Object value) {
        valuesMap.put(name, value);

        return this;
    }

    public InjectableValuesMapper add(Class<?> key, Object value) {
        return addNamed(key.getName(), value);
    }

    public InjectableValuesMapper addNamed(Map<String, Object> values) {
        valuesMap.putAll(values);

        return this;
    }

    public InjectableValuesMapper addAll(Map<Class<?>, Object> values) {
        return addNamed(values.entrySet()
                              .stream()
                              .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue)));
    }

    public InjectableValuesMapper removeNamed(String name) {
        if (!name.equals(ObjectMapper.class.getName()))
            valuesMap.remove(name);

        return this;
    }

    public InjectableValuesMapper remove(Class<?> key) {
        return removeNamed(key.getName());
    }

    public InjectableValuesMapper removeNamed(List<String> names) {
        names.forEach(this::removeNamed);

        return this;
    }

    public InjectableValuesMapper removeAll(List<Class<?>> keys) {
        return removeNamed(keys.stream()
                               .map(Class::getName)
                               .toList());
    }

    public InjectableValuesMapper clear() {
        ObjectMapper objectMapper = find(ObjectMapper.class);

        valuesMap.clear();
        add(ObjectMapper.class, objectMapper);

        return this;
    }

    @Override
    public Object findInjectableValue(Object valueId, DeserializationContext context, BeanProperty forProperty, Object beanInstance) throws JsonMappingException {
        if (!(valueId instanceof String key))
            throw new IllegalArgumentException("Injectable values implementation requires valueId to be of type String.");

        if (!valuesMap.containsKey(key))
            throw new IllegalArgumentException(MessageFormat.format("""
                                                                    There is no injectable object with valueId: "{0}".\
                                                                    """, key));

        return valuesMap.get(key);
    }

    @SneakyThrows
    public Object find(String name) {
        return findInjectableValue(name, null, null, null);
    }

    @SneakyThrows
    public <Type> Type find(String name, Class<Type> returnType) {
        Object value = findInjectableValue(name, null, null, null);

        if (!returnType.isAssignableFrom(value.getClass()))
            throw new IllegalArgumentException(MessageFormat.format("""
                                                                    ClassId({0}) is not assignable form of value class ({1}).\
                                                                    """, returnType.getName(), value.getClass().getName()));

        return (Type) value;
    }

    @SneakyThrows
    public <Type> Type find(Class<Type> classId) {
        return find(classId.getName(), classId);
    }

    public ObjectMapper objectMapper() {
        return find(ObjectMapper.class);
    }

}
