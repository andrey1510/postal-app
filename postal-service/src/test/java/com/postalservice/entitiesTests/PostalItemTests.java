package com.postalservice.entitiesTests;

import com.postalservice.entities.PostalItem;
import com.postalservice.enums.PostalType;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostalItemTests {

    @Test
    public void testCreation() {
        PostalItem postalItem = new PostalItem(
                UUID.randomUUID(),
                PostalType.LETTER,
                "123001",
                "Москва, ул. Лесная, д. 4, кв. 10",
                "Кузнецов Петр Михайлович"
        );
        assertNotNull(postalItem);
        assertEquals(PostalType.LETTER, postalItem.getPostalType());
        assertEquals("123001", postalItem.getRecipientIndex());
        assertEquals("Москва, ул. Лесная, д. 4, кв. 10", postalItem.getRecipientAddress());
        assertEquals("Кузнецов Петр Михайлович", postalItem.getRecipientName());
    }

    @Test
    public void testSetters() {
        PostalItem postalItem = new PostalItem();
        postalItem.setPostalItemId(UUID.randomUUID());
        postalItem.setPostalType(PostalType.LETTER);
        postalItem.setRecipientAddress("Москва, ул. Лесная, д. 4, кв. 10");
        postalItem.setRecipientName("Кузнецов Петр Михайлович");
        postalItem.setRecipientIndex("123001");

        assertNotNull(postalItem.getPostalItemId());
        assertEquals(PostalType.LETTER, postalItem.getPostalType());
        assertEquals("123001", postalItem.getRecipientIndex());
        assertEquals("Москва, ул. Лесная, д. 4, кв. 10", postalItem.getRecipientAddress());
        assertEquals("Кузнецов Петр Михайлович", postalItem.getRecipientName());

    }

}
