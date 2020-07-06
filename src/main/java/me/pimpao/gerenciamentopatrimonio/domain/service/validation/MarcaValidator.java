package me.pimpao.gerenciamentopatrimonio.domain.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import me.pimpao.gerenciamentopatrimonio.domain.dto.MarcaDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Marca;
import me.pimpao.gerenciamentopatrimonio.domain.repository.MarcaRepository;
import me.pimpao.gerenciamentopatrimonio.domain.resource.exception.FieldMessage;

public class MarcaValidator implements ConstraintValidator<MarcaValidation, MarcaDto>{
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public void initialize(MarcaValidation constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(MarcaDto marcaDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> fields = new ArrayList<>();
		
		Marca marca = marcaRepository.findByNome(marcaDto.getNome());
		
		if (marca != null) {
			fields.add(new FieldMessage("nome", "Já existe marca com esse nome"));
		}
		
		fields.forEach(field -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName()).addConstraintViolation();
		});
		
		
		return fields.isEmpty();
	}

}
