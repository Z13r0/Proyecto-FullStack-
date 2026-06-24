package com.pm.ms_perfiles.Assemblers;

import com.pm.ms_perfiles.Controller.PerfilControllerV2;
import com.pm.ms_perfiles.DTO.PerfilResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PerfilModelAssembler
        implements RepresentationModelAssembler<PerfilResponseDTO, EntityModel<PerfilResponseDTO>> {

    @Override
    public EntityModel<PerfilResponseDTO> toModel(PerfilResponseDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PerfilControllerV2.class).buscar(dto.getId())).withSelfRel(),
                linkTo(methodOn(PerfilControllerV2.class).listar()).withRel("perfiles"),
                linkTo(methodOn(PerfilControllerV2.class).eliminar(dto.getId())).withRel("eliminar")
        );
    }
}
