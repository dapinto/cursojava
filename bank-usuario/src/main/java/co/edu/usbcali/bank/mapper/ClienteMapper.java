package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {

	@Mapping(source = "tipoDocumento.tdocId", target = "tdocId")
	ClienteDTO entityToDTO(Cliente cliente);

	@Mapping(target = "tipoDocumento.tdocId", source = "tdocId")
	Cliente dtoToEntity(ClienteDTO clienteDTO);

	List<Cliente> toClientes(List<ClienteDTO> clientesDTO);

	List<ClienteDTO> toClientesDTOs(List<Cliente> clientes);
}
