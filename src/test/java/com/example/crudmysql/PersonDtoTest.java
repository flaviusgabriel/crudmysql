package com.example.crudmysql;

import org.junit.jupiter.api.Test;

import java.io.IOException;


class PersonDtoTest {
    @Test
    public void serialization() throws IOException{
        TransferObjectTestUtils.assertSerialization("person.json",PersonDto.class);
    }

}