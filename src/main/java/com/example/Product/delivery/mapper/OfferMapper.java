package com.example.Product.delivery.mapper;


import com.example.Product.delivery.domain.Offer;
import com.example.Product.delivery.dto.OfferDTO;

public class OfferMapper {

    public static OfferDTO toOfferDTO(Offer offer) {
        OfferDTO offerDTO = new OfferDTO();
        offerDTO.setOfferCode(offer.getOfferCode());
        offerDTO.setPlaceName(offer.getPlaceName());
        offerDTO.setProductCode(offer.getProductCode());

        return offerDTO;
    }

    public static Offer toOffer(OfferDTO offerDTO) {
        Offer offer = new Offer();
        offer.setOfferCode(offerDTO.getOfferCode());
        offer.setPlaceName(offerDTO.getPlaceName());
        offer.setProductCode(offerDTO.getProductCode());

        return offer;
    }
}

