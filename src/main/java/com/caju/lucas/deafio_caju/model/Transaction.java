package com.caju.lucas.deafio_caju.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Transaction(
		@NotBlank
		String transactionId,
		@NotBlank
		String accountId,
		@NotBlank
		@Positive(message = "O valor deve ser maior que zero")
		Double amount,
		@NotBlank
		String mcc,
		@NotBlank
		String merchant
){}
