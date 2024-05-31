package com.example.Product.delivery.mapper;

import com.example.Product.delivery.domain.Transaction;
import com.example.Product.delivery.dto.TransactionDTO;

public class TransactionMapper {

    public static TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionCode(transaction.getTransactionCode());
        transactionDTO.setPlaceName(transaction.getPlaceName());
        transactionDTO.setProductCode(transaction.getProductCode());

        return transactionDTO;
    }

    public static Transaction toTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionCode(transactionDTO.getTransactionCode());
        transaction.setPlaceName(transactionDTO.getPlaceName());
        transaction.setProductCode(transactionDTO.getProductCode());

        return transaction;
    }
}

