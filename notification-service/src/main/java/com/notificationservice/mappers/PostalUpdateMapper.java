package com.notificationservice.mappers;

import com.notificationservice.controllers.PostalUpdatesController;
import com.notificationservice.dto.PostalUpdateDTO;
import com.notificationservice.dto.PostalUpdateStatusAndTimestampDTO;
import com.notificationservice.entities.PostalUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", uses = PostalUpdatesController.class)
public interface PostalUpdateMapper {

    PostalUpdateMapper INSTANCE = Mappers.getMapper(PostalUpdateMapper.class);

    //ToDo

    @Mapping(source = "postalItemId", target = "postalItemId")
    @Mapping(source = "postalStatus", target = "postalStatus")
    @Mapping(source = "timestamp", target = "timestamp")
    PostalUpdate dtoToEntity(PostalUpdateDTO postalUpdateDTO);

    @Mapping(source = "postalStatus", target = "postalStatus")
    @Mapping(source = "timestamp", target = "timestamp")
    PostalUpdateStatusAndTimestampDTO entityToDTO(PostalUpdate postalUpdate);



}

