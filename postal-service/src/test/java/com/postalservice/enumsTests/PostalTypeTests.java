package com.postalservice.enumsTests;

import com.postalservice.enums.PostalType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostalTypeTests {

    @Test
    public void testToString() {
        assertEquals("Письмо", PostalType.LETTER.toString());
        assertEquals("Бандероль", PostalType.PARCEL.toString());
        assertEquals("Открытка", PostalType.POSTCARD.toString());
        assertEquals("Посылка", PostalType.PACKAGE.toString());
    }

    @Test
    public void testFromValue() {
        assertEquals(PostalType.LETTER, PostalType.fromValue("Письмо"));
        assertEquals(PostalType.PARCEL, PostalType.fromValue("Бандероль"));
        assertEquals(PostalType.POSTCARD, PostalType.fromValue("Открытка"));
        assertEquals(PostalType.PACKAGE, PostalType.fromValue("Посылка"));
    }

    @Test
    public void testGetStatusTitle() {
        assertEquals("Письмо", PostalType.LETTER.getTypeTitle());
        assertEquals("Бандероль", PostalType.PARCEL.getTypeTitle());
        assertEquals("Открытка", PostalType.POSTCARD.getTypeTitle());
        assertEquals("Посылка", PostalType.PACKAGE.getTypeTitle());
    }

}