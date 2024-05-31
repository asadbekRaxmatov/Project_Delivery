package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Offer;
import com.example.Product.delivery.dto.OfferDTO;
import com.example.Product.delivery.mapper.OfferMapper;
import com.example.Product.delivery.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<OfferDTO> getOfferById(Long id) {
        return offerRepository.findById(id)
                .map(OfferMapper::toOfferDTO);
    }


    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = OfferMapper.toOffer(offerDTO);
        offer = offerRepository.save(offer);
        return OfferMapper.toOfferDTO(offer);
    }


    public OfferDTO updateOffer(Long id, OfferDTO updatedOfferDTO) {
        if (offerRepository.existsById(id)) {
            Offer updatedOffer = OfferMapper.toOffer(updatedOfferDTO);
            updatedOffer.setId(id);
            return OfferMapper.toOfferDTO(offerRepository.save(updatedOffer));
        } else {
            throw new RuntimeException("Offer not found with id: " + id);
        }
    }


    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
}

