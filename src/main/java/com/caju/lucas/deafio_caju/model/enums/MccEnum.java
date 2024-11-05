package com.caju.lucas.deafio_caju.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MccEnum {

	FOOD,
	MEAL,
	CASH;

	public static MccEnum obterTipoSaldo(String mcc) {
		return switch (mcc) {
			case "5412", "5411" -> FOOD;
			case "5811", "5812" -> MEAL;
			default -> CASH;
		};
	}
}
