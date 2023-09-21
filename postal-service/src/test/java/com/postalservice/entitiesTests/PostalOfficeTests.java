package com.postalservice.entitiesTests;

import com.postalservice.entities.PostalOffice;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class PostalOfficeTests {

    @Test
    public void testCreation() {
        PostalOffice postalOffice = new PostalOffice(
                "125009",
                "Почтовое отделение № 125009",
                "г. Москва, ул. Тверская, дом 9, стр. 5"
        );
        assertNotNull(postalOffice);
        assertEquals("125009", postalOffice.getOfficeIndex());
        assertEquals("Почтовое отделение № 125009", postalOffice.getOfficeTitle());
        assertEquals("г. Москва, ул. Тверская, дом 9, стр. 5", postalOffice.getOfficeAddress());
    }

    @Test
    public void testSetters() {
        PostalOffice postalOffice = new PostalOffice();
        postalOffice.setOfficeIndex("125009");
        postalOffice.setOfficeTitle("Почтовое отделение № 125009");
        postalOffice.setOfficeAddress("г. Москва, ул. Тверская, дом 9, стр. 5");
        assertEquals("125009", postalOffice.getOfficeIndex());
        assertEquals("Почтовое отделение № 125009", postalOffice.getOfficeTitle());
        assertEquals("г. Москва, ул. Тверская, дом 9, стр. 5", postalOffice.getOfficeAddress());
    }

}