package  ma.zs.task.ws.converter.soc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.soc.TypeSociete;
import ma.zs.task.ws.dto.soc.TypeSocieteDto;

@Component
public class TypeSocieteConverter {


    public  TypeSocieteConverter() {
    }


    public TypeSociete toItem(TypeSocieteDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeSociete item = new TypeSociete();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeSocieteDto toDto(TypeSociete item) {
        if (item == null) {
            return null;
        } else {
            TypeSocieteDto dto = new TypeSocieteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeSociete> toItem(List<TypeSocieteDto> dtos) {
        List<TypeSociete> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeSocieteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeSocieteDto> toDto(List<TypeSociete> items) {
        List<TypeSocieteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeSociete item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeSocieteDto dto, TypeSociete t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeSociete> copy(List<TypeSocieteDto> dtos) {
        List<TypeSociete> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeSocieteDto dto : dtos) {
                TypeSociete instance = new TypeSociete();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
