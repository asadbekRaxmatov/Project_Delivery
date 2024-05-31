package com.example.Product.delivery.mapper;


import com.example.Product.delivery.domain.Request;
import com.example.Product.delivery.dto.RequestDTO;

public class RequestMapper {

    public static RequestDTO toRequestDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestCode(request.getRequestCode());
        requestDTO.setPlaceName(request.getPlaceName());
        requestDTO.setProductCode(request.getProductCode());

        return requestDTO;
    }

    public static Request toRequest(RequestDTO requestDTO) {
        Request request = new Request();
        request.setRequestCode(requestDTO.getRequestCode());
        request.setPlaceName(requestDTO.getPlaceName());
        request.setProductCode(requestDTO.getProductCode());

        return request;
    }
}
