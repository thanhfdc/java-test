package com.metasol.rest;

import com.metasol.dto.request.TypeRequestDto;
import com.metasol.dto.response.TypeResponseDto;
import com.metasol.services.Imp.TypeServiceImp;
import com.metasol.utils.EOResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/types")
public class RestTypeController {
    private final TypeServiceImp typeService;


    @GetMapping("/get-all")
    EOResponse<List<?>> getAll() {
        List<TypeResponseDto> listType = typeService.getAll();
        return EOResponse.build(listType);
    }

    @PostMapping("")
    EOResponse<?> createCategory(@Valid @NotNull @RequestBody TypeRequestDto dto,
                                 BindingResult result) {

        if (result.hasErrors()) {
            List<String> errorMessage = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return EOResponse.build(errorMessage);
        }
        TypeResponseDto typeResponseDto = typeService.createType(dto);
        return EOResponse.build(typeResponseDto);
    }

    @PutMapping("/{id}")
    EOResponse<?> updateType(@PathVariable(name = "id") Long id, @RequestBody TypeRequestDto requestDto) {

        TypeResponseDto typeResponseDto = typeService.updateType(id, requestDto);
        return EOResponse.build(typeResponseDto);

    }

    @DeleteMapping("{id}")
    EOResponse<?> deleteType(@PathVariable(name = "id") Long id) {
        try {
            typeService.deleteType(id);
            return EOResponse.build("Xoa thanh cong type id: " + id);
        } catch (Exception e) {
            return EOResponse.build(e.getMessage());
        }

    }


}
