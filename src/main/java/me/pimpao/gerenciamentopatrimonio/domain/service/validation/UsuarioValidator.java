package me.pimpao.gerenciamentopatrimonio.domain.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import me.pimpao.gerenciamentopatrimonio.domain.dto.UsuarioDto;
import me.pimpao.gerenciamentopatrimonio.domain.entity.Usuario;
import me.pimpao.gerenciamentopatrimonio.domain.repository.UsuarioRepository;
import me.pimpao.gerenciamentopatrimonio.domain.resource.exception.FieldMessage;

public class UsuarioValidator implements ConstraintValidator<UsuarioValidation, UsuarioDto> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioValidation constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(UsuarioDto usuarioDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> fields = new ArrayList<>();
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDto.getEmail());
		
		if (usuario.isPresent()) {
			fields.add(new FieldMessage("email", "Já existe usuário com esse email"));
		}
		
		fields.forEach(field -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName()).addConstraintViolation();
		});
		
		return fields.isEmpty();
	}

}
