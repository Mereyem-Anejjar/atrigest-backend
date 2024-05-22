package  ma.zs.task.ws.converter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.entite.EntiteExterneConverter;
import ma.zs.task.ws.converter.task.TacheConverter;

import ma.zs.task.bean.core.task.Tache;


import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.task.TacheEntiteExterne;
import ma.zs.task.ws.dto.task.TacheEntiteExterneDto;

@Component
public class TacheEntiteExterneConverter {

    @Autowired
    private EntiteExterneConverter entiteExterneConverter ;
    @Autowired
    private TacheConverter tacheConverter ;
    private boolean entiteExterne;
    private boolean tache;

    public  TacheEntiteExterneConverter() {
        initObject(true);
    }


    public TacheEntiteExterne toItem(TacheEntiteExterneDto dto) {
        if (dto == null) {
            return null;
        } else {
        TacheEntiteExterne item = new TacheEntiteExterne();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.entiteExterne && dto.getEntiteExterne()!=null)
                item.setEntiteExterne(entiteExterneConverter.toItem(dto.getEntiteExterne())) ;

            if(dto.getTache() != null && dto.getTache().getId() != null){
                item.setTache(new Tache());
                item.getTache().setId(dto.getTache().getId());
                item.getTache().setId(dto.getTache().getId());
            }




        return item;
        }
    }


    public TacheEntiteExterneDto toDto(TacheEntiteExterne item) {
        if (item == null) {
            return null;
        } else {
            TacheEntiteExterneDto dto = new TacheEntiteExterneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.entiteExterne && item.getEntiteExterne()!=null) {
                dto.setEntiteExterne(entiteExterneConverter.toDto(item.getEntiteExterne())) ;

            }
            if(this.tache && item.getTache()!=null) {
                dto.setTache(tacheConverter.toDto(item.getTache())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.entiteExterne = value;
        this.tache = value;
    }
	
    public List<TacheEntiteExterne> toItem(List<TacheEntiteExterneDto> dtos) {
        List<TacheEntiteExterne> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TacheEntiteExterneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TacheEntiteExterneDto> toDto(List<TacheEntiteExterne> items) {
        List<TacheEntiteExterneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TacheEntiteExterne item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TacheEntiteExterneDto dto, TacheEntiteExterne t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEntiteExterne() != null)
        entiteExterneConverter.copy(dto.getEntiteExterne(), t.getEntiteExterne());
        if (dto.getTache() != null)
        tacheConverter.copy(dto.getTache(), t.getTache());
    }

    public List<TacheEntiteExterne> copy(List<TacheEntiteExterneDto> dtos) {
        List<TacheEntiteExterne> result = new ArrayList<>();
        if (dtos != null) {
            for (TacheEntiteExterneDto dto : dtos) {
                TacheEntiteExterne instance = new TacheEntiteExterne();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EntiteExterneConverter getEntiteExterneConverter(){
        return this.entiteExterneConverter;
    }
    public void setEntiteExterneConverter(EntiteExterneConverter entiteExterneConverter ){
        this.entiteExterneConverter = entiteExterneConverter;
    }
    public TacheConverter getTacheConverter(){
        return this.tacheConverter;
    }
    public void setTacheConverter(TacheConverter tacheConverter ){
        this.tacheConverter = tacheConverter;
    }
    public boolean  isEntiteExterne(){
        return this.entiteExterne;
    }
    public void  setEntiteExterne(boolean entiteExterne){
        this.entiteExterne = entiteExterne;
    }
    public boolean  isTache(){
        return this.tache;
    }
    public void  setTache(boolean tache){
        this.tache = tache;
    }
}
