package com.metasol.services.Imp;

import com.metasol.constant.ErrorCode;
import com.metasol.constant.MessageCode;
import com.metasol.dto.request.TypeRequestDto;
import com.metasol.dto.response.TypeResponseDto;
import com.metasol.entity.TypeEntity;
import com.metasol.exception.EOException;
import com.metasol.repositories.ITypeRepository;
import com.metasol.services.ITypeService;
import com.metasol.services.mapper.TypeResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class TypeServiceImp implements ITypeService {
    private final ITypeRepository typeRepo;
    private final TypeResponseMapper typeResponseMapper;


    @Override
    public TypeResponseDto createType(TypeRequestDto requestDto) {
        TypeEntity entity = new TypeEntity();
        this.value(entity, requestDto);
        typeRepo.save(entity);
        return typeResponseMapper.entityToResponse(entity);
    }

    @Override
    public List<TypeResponseDto> getAll() {

        List<TypeEntity> entityList = typeRepo.findAll();
        List<TypeResponseDto> responseDtoList = new LinkedList<>();
        for (TypeEntity entity : entityList) {
            TypeResponseDto typeResponseDto = typeResponseMapper.entityToResponse(entity);
            responseDtoList.add(typeResponseDto);
        }
        return responseDtoList;
    }

    @Override
    public TypeResponseDto updateType(Long id, TypeRequestDto requestDto) {

        TypeEntity entity = typeRepo.findById(id)
                .orElseThrow(() -> new EOException(ErrorCode.ENTITY_NOT_FOUND, MessageCode.ENTITY_NOT_FOUND));
        this.value(entity, requestDto);
        typeRepo.save(entity);

        return typeResponseMapper.entityToResponse(entity);
    }

    @Override
    public void deleteType(Long id) {
        typeRepo.deleteById(id);
    }

    private void value(TypeEntity entity, TypeRequestDto requestDto) {
        entity.setTypeName(requestDto.getTypeName());
    }
}
