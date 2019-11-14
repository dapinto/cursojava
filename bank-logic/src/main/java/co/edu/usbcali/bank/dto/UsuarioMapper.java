package co.edu.usbcali.bank.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Usuario;

@Mapper
public interface UsuarioMapper {

	@Mapping(source = "tipoUsuario.tiusId", target = "tiusId")
	UsuarioDTO entityToDTO(Usuario usuario);

	@Mapping(target = "tipoUsuario.tiusId", source = "tiusId")
	Usuario dtoToEntity(UsuarioDTO usuarioDTO);

	List<Usuario> toUsuarios(List<UsuarioDTO> usuariosDTO);

	List<UsuarioDTO> toUsuariosDTOs(List<Usuario> usuarios);
}
