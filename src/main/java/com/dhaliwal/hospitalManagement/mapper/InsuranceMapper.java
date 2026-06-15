package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.patient.request.InsuranceRequestDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.InsuranceResponseDto;
import com.dhaliwal.hospitalManagement.entity.Insurance;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {
    public InsuranceResponseDto toDto(Insurance insurance) {
        InsuranceResponseDto dto = new InsuranceResponseDto();
        dto.setId(insurance.getId());
        dto.setCreatedAt(insurance.getCreatedAt());
        dto.setProvider(insurance.getProvider());
        dto.setPolicyNumber(insurance.getPolicyNumber());
        dto.setValidTill(insurance.getValidTill());
        return dto;
    }
    public Insurance toEntity(InsuranceRequestDto dto) {
        Insurance insurance = new Insurance();
        insurance.setProvider(dto.getProvider());
        insurance.setPolicyNumber(dto.getPolicyNumber());
        insurance.setValidTill(dto.getValidTill());
        return insurance;
    }
}
