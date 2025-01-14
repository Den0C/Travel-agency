package com.denys.travel_agency.mapper;

import com.denys.travel_agency.dto.VoucherDTO;
import com.denys.travel_agency.model.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    @Mapping(target = "user", expression = "java(com.denys.travel_agency.model.User.builder().userId(UUID.fromString(any.getUser())).build())")
    @Mapping(target = "tour", expression = "java(com.denys.travel_agency.model.Tour.builder().tourId(UUID.fromString(any.getTour())).build())")
    Voucher toVoucher(VoucherDTO any);
    @Mapping(target = "user",expression = "java(any.getUser().getUserId().toString())")
    @Mapping(target = "tour",expression = "java(any.getTour().getTourId().toString())")
    VoucherDTO toVoucherDTO(Voucher any);
}
