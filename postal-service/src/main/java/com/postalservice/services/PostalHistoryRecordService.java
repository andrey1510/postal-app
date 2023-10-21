package com.postalservice.services;

import com.postalservice.dto.PostalHistoryOfItem;
import com.postalservice.entities.PostalHistoryRecord;

import java.util.List;
import java.util.UUID;

public interface PostalHistoryRecordService {

    PostalHistoryRecord createPostalHistory(PostalHistoryRecord postalHistoryRecord);

    List<PostalHistoryOfItem> getPostalHistory(UUID postalItemId);

    PostalHistoryRecord getLastPostalHistoryRecord(UUID postalItemId);
}
