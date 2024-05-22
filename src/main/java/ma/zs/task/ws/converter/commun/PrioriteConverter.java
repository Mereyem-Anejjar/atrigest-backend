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
import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.ws.dto.commun.PrioriteDto;

@Component
public class PrioriteConverter {


    public  PrioriteConverter() {
    }


    public Priorite toItem(PrioriteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Priorite item = new Priorite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public PrioriteDto toDto(Priorite item) {
        if (item == null) {
            return null;
        } else {
            PrioriteDto dto = new PrioriteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Priorite> toItem(List<PrioriteDto> dtos) {
        List<Priorite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PrioriteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PrioriteDto> toDto(List<Priorite> items) {
        List<PrioriteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Priorite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PrioriteDto dto, Priorite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Priorite> copy(List<PrioriteDto> dtos) {
        List<Priorite> result = new ArrayList<>();
        if (dtos != null) {
            for (PrioriteDto dto : dtos) {
                Priorite instance = new Priorite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
