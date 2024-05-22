package  ma.zs.task.ws.converter.entite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import ma.zs.task.ws.dto.entite.TypeEntiteExterneDto;

@Component
public class TypeEntiteExterneConverter {


    public  TypeEntiteExterneConverter() {
    }


    public TypeEntiteExterne toItem(TypeEntiteExterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeEntiteExterne item = new TypeEntiteExterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeEntiteExterneDto toDto(TypeEntiteExterne item) {
        if (item == null) {
            return null;
        } else {
            TypeEntiteExterneDto dto = new TypeEntiteExterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeEntiteExterne> toItem(List<TypeEntiteExterneDto> dtos) {
        List<TypeEntiteExterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeEntiteExterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeEntiteExterneDto> toDto(List<TypeEntiteExterne> items) {
        List<TypeEntiteExterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeEntiteExterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeEntiteExterneDto dto, TypeEntiteExterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeEntiteExterne> copy(List<TypeEntiteExterneDto> dtos) {
        List<TypeEntiteExterne> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeEntiteExterneDto dto : dtos) {
                TypeEntiteExterne instance = new TypeEntiteExterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
