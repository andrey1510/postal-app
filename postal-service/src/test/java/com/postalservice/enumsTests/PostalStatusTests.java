package com.postalservice.enumsTests;

import com.postalservice.enums.PostalStatus;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PostalStatusTests {

    @Test
    public void testToString() {
        assertEquals("Прибыло в почтовое отделение", PostalStatus.IN_OFFICE.toString());
        assertEquals("Покинуло почтовое отделение", PostalStatus.OUT_OF_OFFICE.toString());
        assertEquals("Зарегистрировано", PostalStatus.REGISTERED.toString());
        assertEquals("Получено адресатом", PostalStatus.RECEIVED.toString());
    }

    @Test
    public void testFromValue_returnsCorrectPostalStatus() {
        assertEquals(PostalStatus.IN_OFFICE, PostalStatus.fromValue("Прибыло в почтовое отделение"));
        assertEquals(PostalStatus.OUT_OF_OFFICE, PostalStatus.fromValue("Покинуло почтовое отделение"));
        assertEquals(PostalStatus.REGISTERED, PostalStatus.fromValue("Зарегистрировано"));
        assertEquals(PostalStatus.RECEIVED, PostalStatus.fromValue("Получено адресатом"));
    }

    @Test
    public void test_getStatusTitle_returnsCorrectStatusTitle() {
        assertEquals("Прибыло в почтовое отделение", PostalStatus.IN_OFFICE.getStatusTitle());
        assertEquals("Покинуло почтовое отделение", PostalStatus.OUT_OF_OFFICE.getStatusTitle());
        assertEquals("Зарегистрировано", PostalStatus.REGISTERED.getStatusTitle());
        assertEquals("Получено адресатом", PostalStatus.RECEIVED.getStatusTitle());
    }

}
