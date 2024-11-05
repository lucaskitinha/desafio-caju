package com.caju.lucas.deafio_caju.service;

import com.caju.lucas.deafio_caju.model.ResponseTransaction;
import com.caju.lucas.deafio_caju.model.Saldo;
import com.caju.lucas.deafio_caju.model.Transaction;
import com.caju.lucas.deafio_caju.model.enums.MccEnum;
import com.caju.lucas.deafio_caju.repository.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

	@Autowired
	SaldoRepository saldoRepository;

	private static final String CODE00 = "00";
	private static final String CODE51 = "51";
	private static final String CODE07 = "07";

	public ResponseTransaction validarTranasaction(Transaction transaction) {
		try {
			MccEnum tipo = MccEnum.obterTipoSaldo(transaction.mcc());
			
			tipo = validarNomeComerciante(transaction.merchant().toLowerCase());

			var saldo = saldoRepository.findByTipo(tipo);

			var retorno = processarTransacao(saldo,transaction.amount());

			if(!retorno.getCode().equals(CODE00) && !tipo.name().equals("CASH")) {
				Saldo saldoCash = saldoRepository.findByTipo(MccEnum.CASH);
				retorno = processarTransacao(saldoCash, transaction.amount());
			}
			return retorno;
		} catch (Exception e) {
			return construirRetorno(CODE07);
		}

	}

	private MccEnum validarNomeComerciante( String merchant) {
		if(merchant.contains("food") || merchant.contains("eat") || merchant.contains("restaurant")) {
			return MccEnum.FOOD;
		} else if (merchant.contains("market") || merchant.contains("rappy") || merchant.contains("alimento")) {
			return MccEnum.MEAL;
		} else {
			return MccEnum.CASH;
		}
	}

	private ResponseTransaction processarTransacao(Saldo saldo, Double amount) {
		var retorno = validarSaldoTranasaction(saldo.getTotalAmount(), amount);

		if(retorno.getCode().equals(CODE00)) {
			atualizarSaldo(saldo, amount);
		}

		return retorno;
	}

	private void atualizarSaldo(Saldo saldo, Double amount) {
		saldo.setTotalAmount(saldo.getTotalAmount()-amount);
		saldoRepository.save(saldo);
	}

	private ResponseTransaction validarSaldoTranasaction(Double totalAmount, Double amount) {
		try {
			return (amount <= totalAmount) ? construirRetorno(CODE00) : construirRetorno(CODE51);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao validar saldo ou transação.");
		}
	}

	private ResponseTransaction construirRetorno(String code) {
		return ResponseTransaction.builder()
				.code(code)
				.build();
	}
}
