package br.org.neki.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.neki.backend.exception.EnumValidationExcepetion;


public enum Level 
{
	SENIOR, JUNIOR, TRAINEE, PLENO;

	@JsonCreator
	public static Level verifica(String valor) throws EnumValidationExcepetion 
	{
		for (Level level : values()) 
			if (valor.equals(level.name())) 
				return level;
		throw new EnumValidationExcepetion("Categoria inválida!");
	}
}
