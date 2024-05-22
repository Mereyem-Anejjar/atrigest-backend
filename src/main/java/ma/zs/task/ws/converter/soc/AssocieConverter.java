package  ma.zs.task.ws.converter.soc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.soc.SocieteConverter;
import ma.zs.task.ws.converter.soc.RoleAssocieConverter;

import ma.zs.task.bean.core.soc.Societe;


import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.soc.Associe;
import ma.zs.task.ws.dto.soc.AssocieDto;

@Component
public class AssocieConverter {

    @Autowired
    private SocieteConverter societeConverter ;
    @Autowired
    private RoleAssocieConverter roleAssocieConverter ;
    private boolean societe;
    private boolean roleAssocie;

    public  AssocieConverter() {
        initObject(true);
    }


    public Associe toItem(AssocieDto dto) {
        if (dto == null) {
            return null;
        } else {
        Associe item = new Associe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(dto.getSociete() != null && dto.getSociete().getId() != null){
                item.setSociete(new Societe());
                item.getSociete().setId(dto.getSociete().getId());
                item.getSociete().setNom(dto.getSociete().getNom());
            }

            if(this.roleAssocie && dto.getRoleAssocie()!=null)
                item.setRoleAssocie(roleAssocieConverter.toItem(dto.getRoleAssocie())) ;




        return item;
        }
    }


    public AssocieDto toDto(Associe item) {
        if (item == null) {
            return null;
        } else {
            AssocieDto dto = new AssocieDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(this.societe && item.getSociete()!=null) {
                dto.setSociete(societeConverter.toDto(item.getSociete())) ;

            }
            if(this.roleAssocie && item.getRoleAssocie()!=null) {
                dto.setRoleAssocie(roleAssocieConverter.toDto(item.getRoleAssocie())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.societe = value;
        this.roleAssocie = value;
    }
	
    public List<Associe> toItem(List<AssocieDto> dtos) {
        List<Associe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AssocieDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AssocieDto> toDto(List<Associe> items) {
        List<AssocieDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Associe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AssocieDto dto, Associe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSociete() != null)
        societeConverter.copy(dto.getSociete(), t.getSociete());
        if (dto.getRoleAssocie() != null)
        roleAssocieConverter.copy(dto.getRoleAssocie(), t.getRoleAssocie());
    }

    public List<Associe> copy(List<AssocieDto> dtos) {
        List<Associe> result = new ArrayList<>();
        if (dtos != null) {
            for (AssocieDto dto : dtos) {
                Associe instance = new Associe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SocieteConverter getSocieteConverter(){
        return this.societeConverter;
    }
    public void setSocieteConverter(SocieteConverter societeConverter ){
        this.societeConverter = societeConverter;
    }
    public RoleAssocieConverter getRoleAssocieConverter(){
        return this.roleAssocieConverter;
    }
    public void setRoleAssocieConverter(RoleAssocieConverter roleAssocieConverter ){
        this.roleAssocieConverter = roleAssocieConverter;
    }
    public boolean  isSociete(){
        return this.societe;
    }
    public void  setSociete(boolean societe){
        this.societe = societe;
    }
    public boolean  isRoleAssocie(){
        return this.roleAssocie;
    }
    public void  setRoleAssocie(boolean roleAssocie){
        this.roleAssocie = roleAssocie;
    }
}
