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
import ma.zs.task.bean.core.commun.Banque;
import ma.zs.task.ws.dto.commun.BanqueDto;

@Component
public class BanqueConverter {


    public  BanqueConverter() {
    }


    public Banque toItem(BanqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        Banque item = new Banque();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public BanqueDto toDto(Banque item) {
        if (item == null) {
            return null;
        } else {
            BanqueDto dto = new BanqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Banque> toItem(List<BanqueDto> dtos) {
        List<Banque> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (BanqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<BanqueDto> toDto(List<Banque> items) {
        List<BanqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Banque item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(BanqueDto dto, Banque t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Banque> copy(List<BanqueDto> dtos) {
        List<Banque> result = new ArrayList<>();
        if (dtos != null) {
            for (BanqueDto dto : dtos) {
                Banque instance = new Banque();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
