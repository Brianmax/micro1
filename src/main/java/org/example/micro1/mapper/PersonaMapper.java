package org.example.micro1.mapper;

import org.example.micro1.entity.PersonaEntity;
import org.example.micro1.response.ResponseReniec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);
    @Mapping(source = "nombres", target = "name")
    @Mapping(source = "apellidoPaterno", target = "firstSurname")
    @Mapping(source = "apellidoMaterno", target = "secondSurname")
    @Mapping(source = "numeroDocumento", target = "numDocument")
    PersonaEntity toPersonaEntity(ResponseReniec responseReniec);


}
