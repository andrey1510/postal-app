package com.postalservice.dtoTests;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.dto.PostalItemInfo;
import com.postalservice.entities.PostalItem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostalItemInfoTests {

    @Test
    public void testCreation() {
        PostalItem postalItem = new PostalItem();
        List<PostalHistoryOfItem> postalHistoryOfItem = new ArrayList<>();
        PostalItemInfo postalItemInfo = new PostalItemInfo(postalItem, postalHistoryOfItem);

        assertNotNull(postalItemInfo);
        assertEquals(postalItem, postalItemInfo.getPostalItem());
        assertEquals(postalHistoryOfItem, postalItemInfo.getPostalHistoryOfItem());
    }

}
