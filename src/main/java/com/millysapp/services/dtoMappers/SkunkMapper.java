package com.millysapp.services.dtoMappers;

import com.millysapp.dtos.SkunkDto;
import com.millysapp.model.Skunk;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkunkMapper {

    public List<SkunkDto> mapListToDto(List<Skunk> skunkList) {
        List<SkunkDto> list = new ArrayList<>();
        for(Skunk skunk : skunkList) {
                list.add(mapToDto(skunk));
        }
        return list;
    }

    public SkunkDto mapToDto(Skunk skunk) {
        SkunkDto skunkDto = new SkunkDto();

        skunkDto.setSkunkId(skunk.getSkunkId());
        skunkDto.setName(skunk.getName());
        skunkDto.setSize(skunk.getSize());
        skunkDto.setWeight(skunk.getWeight());
        skunkDto.setColor(skunk.getColor());
        skunkDto.setFamilyName(skunk.getFamilyName());
        skunkDto.setOmnivorous(skunk.isOmnivorous());
        return skunkDto;
    }

    public List<Skunk> mapListToEntity(List<SkunkDto> skunkDtoList) {
        List<Skunk> list = new ArrayList<>();

        for(SkunkDto skunkDto : skunkDtoList) {
            list.add(mapToEntity(skunkDto));
        }
        return list;
    }

    public Skunk mapToEntity(SkunkDto skunkDto) {
        Skunk skunk = new Skunk();

        skunk.setSkunkId(skunkDto.getSkunkId());
        skunk.setName(skunkDto.getName());
        skunk.setSize(skunkDto.getSize());
        skunk.setWeight(skunkDto.getWeight());
        skunk.setColor(skunkDto.getColor());
        skunk.setOmnivorous(skunkDto.getOmnivorous());
        skunk.setFamilyName(skunkDto.getFamilyName());
        return skunk;
    }
}
