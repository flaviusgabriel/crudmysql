package com.example.crudmysql;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TransferObjectTestUtils {
    private static final String BASE_JSON_PATH = "src/test/resources/dto/";

    static void assertSerialization(String file, Class serializationClass) throws IOException{
        String eventJson = new String(Files.readAllBytes(Paths.get(BASE_JSON_PATH + file)));
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(eventJson, serializationClass);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        assertNotNull(jsonStr);

    }
}
