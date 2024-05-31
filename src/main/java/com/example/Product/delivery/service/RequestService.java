package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Request;
import com.example.Product.delivery.dto.RequestDTO;
import com.example.Product.delivery.mapper.RequestMapper;
import com.example.Product.delivery.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<RequestDTO> getRequestById(Long id) {
        return requestRepository.findById(id)
                .map(RequestMapper::toRequestDTO);
    }

    public RequestDTO createRequest(RequestDTO requestDTO) {
        Request request = RequestMapper.toRequest(requestDTO);
        return RequestMapper.toRequestDTO(requestRepository.save(request));
    }

    public RequestDTO updateRequest(Long id, RequestDTO updatedRequestDTO) {
        if (requestRepository.existsById(id)) {
            Request updatedRequest = RequestMapper.toRequest(updatedRequestDTO);
            updatedRequest.setId(id);
            return RequestMapper.toRequestDTO(requestRepository.save(updatedRequest));
        } else {
            throw new RuntimeException("Request not found with id: " + id);
        }
    }


    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}

