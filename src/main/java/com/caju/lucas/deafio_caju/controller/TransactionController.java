package com.caju.lucas.deafio_caju.controller;

import com.caju.lucas.deafio_caju.model.Transaction;
import com.caju.lucas.deafio_caju.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;


	@PostMapping
	@Transactional
	public ResponseEntity validarTransaction(@RequestBody Transaction transaction) {
	    return ResponseEntity.ok(service.validarTranasaction(transaction));
	}
}
