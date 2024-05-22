package  ma.zs.task.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.ws.dto.commun.TypeIdentiteDto;

@Component
public class TypeIdentiteConverter {


    public  TypeIdentiteConverter() {
    }


    public TypeIdentite toItem(TypeIdentiteDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeIdentite item = new TypeIdentite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeIdentiteDto toDto(TypeIdentite item) {
        if (item == null) {
            return null;
        } else {
            TypeIdentiteDto dto = new TypeIdentiteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeIdentite> toItem(List<TypeIdentiteDto> dtos) {
        List<TypeIdentite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeIdentiteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeIdentiteDto> toDto(List<TypeIdentite> items) {
        List<TypeIdentiteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeIdentite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeIdentiteDto dto, TypeIdentite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeIdentite> copy(List<TypeIdentiteDto> dtos) {
        List<TypeIdentite> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeIdentiteDto dto : dtos) {
                TypeIdentite instance = new TypeIdentite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
