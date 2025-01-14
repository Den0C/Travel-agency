package com.denys.travel_agency.mapper;

import com.denys.travel_agency.dto.TourDTO;
import com.denys.travel_agency.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(target = "location", source = "location.locationId")
    @Mapping(target = "transferType", source = "transferType.transferTypeId")
    @Mapping(target = "hotelType", expression = "java(any.getHotelType().name())")
    @Mapping(target = "isHot", source = "hot")
    TourDTO toTourDTO(Tour any);

    @Mapping(target = "location", expression = "java(com.denys.travel_agency.model.Location.builder().locationId(UUID.fromString(any.getLocation())).build())")
    @Mapping(target = "transferType", expression = "java(com.denys.travel_agency.model.TransferType.builder().transferTypeId(UUID.fromString(any.getTransferType())).build())")
    @Mapping(target = "hotelType", expression = "java(com.denys.travel_agency.enums.HotelType.valueOf(any.getHotelType()))")
    @Mapping(target = "isHot", expression = "java(any.isHot())")
    @Mapping(target = "vouchers", ignore = true)
    Tour toTour(TourDTO any);
}
