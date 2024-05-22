package  ma.zs.task.ws.converter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.commun.EtatAvancementConverter;
import ma.zs.task.ws.converter.utilisateur.UtilisateurConverter;
import ma.zs.task.ws.converter.task.TacheConverter;

import ma.zs.task.bean.core.task.Tache;


import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.task.TacheDetail;
import ma.zs.task.ws.dto.task.TacheDetailDto;

@Component
public class TacheDetailConverter {

    @Autowired
    private EtatAvancementConverter etatAvancementConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    @Autowired
    private TacheConverter tacheConverter ;
    private boolean utilisateur;
    private boolean etatAvancement;
    private boolean tache;

    public  TacheDetailConverter() {
        initObject(true);
    }


    public TacheDetail toItem(TacheDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        TacheDetail item = new TacheDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.utilisateur && dto.getUtilisateur()!=null)
                item.setUtilisateur(utilisateurConverter.toItem(dto.getUtilisateur())) ;

            if(this.etatAvancement && dto.getEtatAvancement()!=null)
                item.setEtatAvancement(etatAvancementConverter.toItem(dto.getEtatAvancement())) ;

            if(dto.getTache() != null && dto.getTache().getId() != null){
                item.setTache(new Tache());
                item.getTache().setId(dto.getTache().getId());
                item.getTache().setId(dto.getTache().getId());
            }




        return item;
        }
    }


    public TacheDetailDto toDto(TacheDetail item) {
        if (item == null) {
            return null;
        } else {
            TacheDetailDto dto = new TacheDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.utilisateur && item.getUtilisateur()!=null) {
                dto.setUtilisateur(utilisateurConverter.toDto(item.getUtilisateur())) ;

            }
            if(this.etatAvancement && item.getEtatAvancement()!=null) {
                dto.setEtatAvancement(etatAvancementConverter.toDto(item.getEtatAvancement())) ;

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
        this.utilisateur = value;
        this.etatAvancement = value;
        this.tache = value;
    }
	
    public List<TacheDetail> toItem(List<TacheDetailDto> dtos) {
        List<TacheDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TacheDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TacheDetailDto> toDto(List<TacheDetail> items) {
        List<TacheDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TacheDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TacheDetailDto dto, TacheDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getUtilisateur() != null)
        utilisateurConverter.copy(dto.getUtilisateur(), t.getUtilisateur());
        if (dto.getEtatAvancement() != null)
        etatAvancementConverter.copy(dto.getEtatAvancement(), t.getEtatAvancement());
        if (dto.getTache() != null)
        tacheConverter.copy(dto.getTache(), t.getTache());
    }

    public List<TacheDetail> copy(List<TacheDetailDto> dtos) {
        List<TacheDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (TacheDetailDto dto : dtos) {
                TacheDetail instance = new TacheDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EtatAvancementConverter getEtatAvancementConverter(){
        return this.etatAvancementConverter;
    }
    public void setEtatAvancementConverter(EtatAvancementConverter etatAvancementConverter ){
        this.etatAvancementConverter = etatAvancementConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public TacheConverter getTacheConverter(){
        return this.tacheConverter;
    }
    public void setTacheConverter(TacheConverter tacheConverter ){
        this.tacheConverter = tacheConverter;
    }
    public boolean  isUtilisateur(){
        return this.utilisateur;
    }
    public void  setUtilisateur(boolean utilisateur){
        this.utilisateur = utilisateur;
    }
    public boolean  isEtatAvancement(){
        return this.etatAvancement;
    }
    public void  setEtatAvancement(boolean etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public boolean  isTache(){
        return this.tache;
    }
    public void  setTache(boolean tache){
        this.tache = tache;
    }
}
