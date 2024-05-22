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
import ma.zs.task.bean.core.commun.Nationalite;
import ma.zs.task.ws.dto.commun.NationaliteDto;

@Component
public class NationaliteConverter {


    public  NationaliteConverter() {
    }


    public Nationalite toItem(NationaliteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Nationalite item = new Nationalite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public NationaliteDto toDto(Nationalite item) {
        if (item == null) {
            return null;
        } else {
            NationaliteDto dto = new NationaliteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Nationalite> toItem(List<NationaliteDto> dtos) {
        List<Nationalite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (NationaliteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<NationaliteDto> toDto(List<Nationalite> items) {
        List<NationaliteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Nationalite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(NationaliteDto dto, Nationalite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Nationalite> copy(List<NationaliteDto> dtos) {
        List<Nationalite> result = new ArrayList<>();
        if (dtos != null) {
            for (NationaliteDto dto : dtos) {
                Nationalite instance = new Nationalite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
