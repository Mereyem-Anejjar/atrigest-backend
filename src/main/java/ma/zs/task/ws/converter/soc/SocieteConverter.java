package  ma.zs.task.ws.converter.soc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.task.zynerator.util.ListUtil;

import ma.zs.task.ws.converter.soc.TypeSocieteConverter;
import ma.zs.task.ws.converter.soc.RoleAssocieConverter;
import ma.zs.task.ws.converter.soc.AssocieConverter;



import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.soc.Societe;
import ma.zs.task.ws.dto.soc.SocieteDto;

@Component
public class SocieteConverter {

    @Autowired
    private TypeSocieteConverter typeSocieteConverter ;
    @Autowired
    private RoleAssocieConverter roleAssocieConverter ;
    @Autowired
    private AssocieConverter associeConverter ;
    private boolean type;
    private boolean associes;

    public  SocieteConverter() {
        init(true);
    }


    public Societe toItem(SocieteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Societe item = new Societe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getDateCreation()))
                item.setDateCreation(DateUtil.stringEnToDate(dto.getDateCreation()));
            if(StringUtil.isNotEmpty(dto.getRc()))
                item.setRc(dto.getRc());
            if(StringUtil.isNotEmpty(dto.getTaxeProfessionnelle()))
                item.setTaxeProfessionnelle(dto.getTaxeProfessionnelle());
            if(StringUtil.isNotEmpty(dto.getIce()))
                item.setIce(dto.getIce());
            if(StringUtil.isNotEmpty(dto.getGerant()))
                item.setGerant(dto.getGerant());
            if(this.type && dto.getType()!=null)
                item.setType(typeSocieteConverter.toItem(dto.getType())) ;


            if(this.associes && ListUtil.isNotEmpty(dto.getAssocies()))
                item.setAssocies(associeConverter.toItem(dto.getAssocies()));


        return item;
        }
    }


    public SocieteDto toDto(Societe item) {
        if (item == null) {
            return null;
        } else {
            SocieteDto dto = new SocieteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(item.getDateCreation()!=null)
                dto.setDateCreation(DateUtil.dateTimeToString(item.getDateCreation()));
            if(StringUtil.isNotEmpty(item.getRc()))
                dto.setRc(item.getRc());
            if(StringUtil.isNotEmpty(item.getTaxeProfessionnelle()))
                dto.setTaxeProfessionnelle(item.getTaxeProfessionnelle());
            if(StringUtil.isNotEmpty(item.getIce()))
                dto.setIce(item.getIce());
            if(StringUtil.isNotEmpty(item.getGerant()))
                dto.setGerant(item.getGerant());
            if(this.type && item.getType()!=null) {
                dto.setType(typeSocieteConverter.toDto(item.getType())) ;

            }
        if(this.associes && ListUtil.isNotEmpty(item.getAssocies())){
            associeConverter.init(true);
            associeConverter.setSociete(false);
            dto.setAssocies(associeConverter.toDto(item.getAssocies()));
            associeConverter.setSociete(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.associes = value;
    }
    public void initObject(boolean value) {
        this.type = value;
    }
	
    public List<Societe> toItem(List<SocieteDto> dtos) {
        List<Societe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SocieteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SocieteDto> toDto(List<Societe> items) {
        List<SocieteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Societe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SocieteDto dto, Societe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getType() != null)
        typeSocieteConverter.copy(dto.getType(), t.getType());
        if (dto.getAssocies() != null)
            t.setAssocies(associeConverter.copy(dto.getAssocies()));
    }

    public List<Societe> copy(List<SocieteDto> dtos) {
        List<Societe> result = new ArrayList<>();
        if (dtos != null) {
            for (SocieteDto dto : dtos) {
                Societe instance = new Societe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeSocieteConverter getTypeSocieteConverter(){
        return this.typeSocieteConverter;
    }
    public void setTypeSocieteConverter(TypeSocieteConverter typeSocieteConverter ){
        this.typeSocieteConverter = typeSocieteConverter;
    }
    public RoleAssocieConverter getRoleAssocieConverter(){
        return this.roleAssocieConverter;
    }
    public void setRoleAssocieConverter(RoleAssocieConverter roleAssocieConverter ){
        this.roleAssocieConverter = roleAssocieConverter;
    }
    public AssocieConverter getAssocieConverter(){
        return this.associeConverter;
    }
    public void setAssocieConverter(AssocieConverter associeConverter ){
        this.associeConverter = associeConverter;
    }
    public boolean  isType(){
        return this.type;
    }
    public void  setType(boolean type){
        this.type = type;
    }
    public boolean  isAssocies(){
        return this.associes ;
    }
    public void  setAssocies(boolean associes ){
        this.associes  = associes ;
    }
}
