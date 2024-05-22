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
import ma.zs.task.bean.core.soc.RoleAssocie;
import ma.zs.task.ws.dto.soc.RoleAssocieDto;

@Component
public class RoleAssocieConverter {


    public  RoleAssocieConverter() {
    }


    public RoleAssocie toItem(RoleAssocieDto dto) {
        if (dto == null) {
            return null;
        } else {
        RoleAssocie item = new RoleAssocie();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public RoleAssocieDto toDto(RoleAssocie item) {
        if (item == null) {
            return null;
        } else {
            RoleAssocieDto dto = new RoleAssocieDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<RoleAssocie> toItem(List<RoleAssocieDto> dtos) {
        List<RoleAssocie> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RoleAssocieDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RoleAssocieDto> toDto(List<RoleAssocie> items) {
        List<RoleAssocieDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RoleAssocie item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RoleAssocieDto dto, RoleAssocie t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<RoleAssocie> copy(List<RoleAssocieDto> dtos) {
        List<RoleAssocie> result = new ArrayList<>();
        if (dtos != null) {
            for (RoleAssocieDto dto : dtos) {
                RoleAssocie instance = new RoleAssocie();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
