package com.pm.ms_suscripciones.Assemblers;
import com.pm.ms_suscripciones.Controller.SuscripcionControllerV2;
import com.pm.ms_suscripciones.DTO.SuscripcionResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SuscripcionModelAssembler

    implements RepresentationModelAssembler<SuscripcionResponseDTO, EntityModel<SuscripcionResponseDTO>> {

        @Override
        public EntityModel<SuscripcionResponseDTO> toModel(SuscripcionResponseDTO dto){
            return EntityModel.of(dto,
                    linkTo(methodOn(SuscripcionControllerV2.class).buscar(dto.getId())).withSelfRel(),
                    linkTo(methodOn(SuscripcionControllerV2.class).listarTodos()).withRel("suscripciones"),
                    linkTo(methodOn(SuscripcionControllerV2.class).listarPorUsuario(dto.getUsuarioId())).withRel("por-usuario"),
                    linkTo(methodOn(SuscripcionControllerV2.class).cancelar(dto.getId())).withRel("cancelar")
            );
        }
}
