package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Statistic;
import com.example.Product.delivery.domain.Transaction;
import com.example.Product.delivery.dto.TransactionDTO;
import com.example.Product.delivery.mapper.StatisticMapper;
import com.example.Product.delivery.mapper.TransactionMapper;
import com.example.Product.delivery.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionDTO> getTransactionById(Long id) {
        return transactionRepository.findById(id).map(TransactionMapper::toTransactionDTO);
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.toTransaction(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toTransactionDTO(savedTransaction);
    }

    public TransactionDTO updateTransaction(Long id, TransactionDTO updatedTransactionDTO) {
        if (transactionRepository.existsById(id)) {
            Transaction updatedTransaction = TransactionMapper.toTransaction(updatedTransactionDTO);
            updatedTransaction.setId(id);
            return TransactionMapper.toTransactionDTO(transactionRepository.save(updatedTransaction));
        } else {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}

