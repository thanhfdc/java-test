package com.metasol.validation;

import com.metasol.constant.Constant;
import com.metasol.constant.ErrorCode;
import com.metasol.constant.MessageCode;
import com.metasol.entity.CustomerEntity;
import com.metasol.exception.EOException;
import com.metasol.repositories.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationCustomer {

    @Autowired
    private ICustomerRepository customerRepos;


    public void checkId(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new EOException(ErrorCode.ENTITY_NOT_NULL, MessageCode.NOT_NULL);
        }
        if (!customerRepos.existsById(id)) {
            throw new EOException(ErrorCode.ENTITY_NOT_FOUND, MessageCode.ENTITY_NOT_FOUND);
        }
    }

    public void checkPhoneNumber(Long id, String phoneNumber) {
        if (!StringUtils.hasText(phoneNumber)) {
            throw new EOException(ErrorCode.CUSTOMER_PHONE_NOTNULL, MessageCode.NOT_NULL);
        }

        Pattern pattern = Pattern.compile(Constant.PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new EOException(ErrorCode.CUSTOMER_PHONE_WRONG_FORMAT, MessageCode.INVALID_VALUE);
        }
        if (ObjectUtils.isEmpty(id) && customerRepos.existsByPhoneNumber(phoneNumber)) {
            throw new EOException(ErrorCode.EMPLOYEE_PHONE_DUPLICATE, MessageCode.CUSTOMER_PHONE_NUMBER_DUPLICATE);
        }

        if (!ObjectUtils.isEmpty(id)) {
            CustomerEntity employee = customerRepos.getOne(id);
            if (!employee.getPhoneNumber().equals(phoneNumber) && customerRepos.existsByPhoneNumber(phoneNumber)) {
                throw new EOException(ErrorCode.EMPLOYEE_PHONE_DUPLICATE, MessageCode.CUSTOMER_PHONE_NUMBER_DUPLICATE);

            }
        }


    }

}
