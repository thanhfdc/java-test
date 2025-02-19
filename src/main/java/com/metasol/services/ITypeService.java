package com.metasol.services;

import com.metasol.dto.request.TypeRequestDto;
import com.metasol.dto.response.TypeResponseDto;

import java.util.List;

public interface ITypeService {

    TypeResponseDto createType(TypeRequestDto requestDto);
    List<TypeResponseDto> getAll();
    TypeResponseDto updateType(Long id, TypeRequestDto requestDto);
    void deleteType(Long id);

}
