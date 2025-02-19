package com.metasol.services.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.metasol.dto.request.TypeRequestDto;
import com.metasol.dto.response.TypeResponseDto;
import com.metasol.entity.TypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeResponseMapper {
    TypeResponseDto entityToResponse(TypeEntity entity);
}
