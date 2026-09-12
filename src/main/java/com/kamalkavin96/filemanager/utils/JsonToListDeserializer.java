package com.kamalkavin96.filemanager.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToListDeserializer extends JsonDeserializer<List<String>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
       return objectMapper.readValue( parser.getText(), objectMapper.getTypeFactory() .constructCollectionType(List.class, String.class) );
    }

}
