package com.metasol.validation;

import com.metasol.constant.ErrorCode;
import com.metasol.constant.MessageCode;
import com.metasol.exception.EOException;
import com.metasol.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ValidationProduct {
    @Autowired
    private IProductRepository productRepos;

    public boolean checkId(Long id) {
        boolean check;
        if (ObjectUtils.isEmpty(id)) {
            throw new EOException(ErrorCode.ENTITY_NOT_FOUND, MessageCode.ENTITY_NOT_FOUND);
        }
        if (!productRepos.existsById(id)) {
            throw new EOException(ErrorCode.ENTITY_NOT_NULL, MessageCode.ENTITY_NOT_FOUND);
        }else check = true;
        return check;

    }
}
