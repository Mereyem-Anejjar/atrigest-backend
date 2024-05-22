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
import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.ws.dto.commun.EtatAvancementDto;

@Component
public class EtatAvancementConverter {


    public  EtatAvancementConverter() {
    }


    public EtatAvancement toItem(EtatAvancementDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatAvancement item = new EtatAvancement();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EtatAvancementDto toDto(EtatAvancement item) {
        if (item == null) {
            return null;
        } else {
            EtatAvancementDto dto = new EtatAvancementDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<EtatAvancement> toItem(List<EtatAvancementDto> dtos) {
        List<EtatAvancement> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatAvancementDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatAvancementDto> toDto(List<EtatAvancement> items) {
        List<EtatAvancementDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatAvancement item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatAvancementDto dto, EtatAvancement t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatAvancement> copy(List<EtatAvancementDto> dtos) {
        List<EtatAvancement> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatAvancementDto dto : dtos) {
                EtatAvancement instance = new EtatAvancement();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
