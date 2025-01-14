package com.denys.travel_agency.mapper;

import com.denys.travel_agency.dto.LocationDTO;
import com.denys.travel_agency.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toLocationDTO(Location any);

    Location toLocation(LocationDTO any);
}
