package  ma.zs.task.ws.converter.entite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.entite.TypeEntiteExterneConverter;



import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.ws.dto.entite.EntiteExterneDto;

@Component
public class EntiteExterneConverter {

    @Autowired
    private TypeEntiteExterneConverter typeEntiteExterneConverter ;
    private boolean typeEntiteExterne;

    public  EntiteExterneConverter() {
        initObject(true);
    }


    public EntiteExterne toItem(EntiteExterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        EntiteExterne item = new EntiteExterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getTel()))
                item.setTel(dto.getTel());
            if(this.typeEntiteExterne && dto.getTypeEntiteExterne()!=null)
                item.setTypeEntiteExterne(typeEntiteExterneConverter.toItem(dto.getTypeEntiteExterne())) ;




        return item;
        }
    }


    public EntiteExterneDto toDto(EntiteExterne item) {
        if (item == null) {
            return null;
        } else {
            EntiteExterneDto dto = new EntiteExterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getTel()))
                dto.setTel(item.getTel());
            if(this.typeEntiteExterne && item.getTypeEntiteExterne()!=null) {
                dto.setTypeEntiteExterne(typeEntiteExterneConverter.toDto(item.getTypeEntiteExterne())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.typeEntiteExterne = value;
    }
	
    public List<EntiteExterne> toItem(List<EntiteExterneDto> dtos) {
        List<EntiteExterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EntiteExterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EntiteExterneDto> toDto(List<EntiteExterne> items) {
        List<EntiteExterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EntiteExterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EntiteExterneDto dto, EntiteExterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getTypeEntiteExterne() != null)
        typeEntiteExterneConverter.copy(dto.getTypeEntiteExterne(), t.getTypeEntiteExterne());
    }

    public List<EntiteExterne> copy(List<EntiteExterneDto> dtos) {
        List<EntiteExterne> result = new ArrayList<>();
        if (dtos != null) {
            for (EntiteExterneDto dto : dtos) {
                EntiteExterne instance = new EntiteExterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeEntiteExterneConverter getTypeEntiteExterneConverter(){
        return this.typeEntiteExterneConverter;
    }
    public void setTypeEntiteExterneConverter(TypeEntiteExterneConverter typeEntiteExterneConverter ){
        this.typeEntiteExterneConverter = typeEntiteExterneConverter;
    }
    public boolean  isTypeEntiteExterne(){
        return this.typeEntiteExterne;
    }
    public void  setTypeEntiteExterne(boolean typeEntiteExterne){
        this.typeEntiteExterne = typeEntiteExterne;
    }
}
