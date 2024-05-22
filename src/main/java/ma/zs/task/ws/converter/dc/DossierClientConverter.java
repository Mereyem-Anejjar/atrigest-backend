package  ma.zs.task.ws.converter.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.commun.NationaliteConverter;
import ma.zs.task.ws.converter.commun.TypeIdentiteConverter;
import ma.zs.task.ws.converter.commun.BanqueConverter;



import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.ws.dto.dc.DossierClientDto;

@Component
public class DossierClientConverter {

    @Autowired
    private NationaliteConverter nationaliteConverter ;
    @Autowired
    private TypeIdentiteConverter typeIdentiteConverter ;
    @Autowired
    private BanqueConverter banqueConverter ;
    private boolean nationalite;
    private boolean typeIdentite;
    private boolean banqueAdherente;

    public  DossierClientConverter() {
        initObject(true);
    }


    public DossierClient toItem(DossierClientDto dto) {
        if (dto == null) {
            return null;
        } else {
        DossierClient item = new DossierClient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getNumeroIdentite()))
                item.setNumeroIdentite(dto.getNumeroIdentite());
            if(StringUtil.isNotEmpty(dto.getRaisonSociale()))
                item.setRaisonSociale(dto.getRaisonSociale());
            if(StringUtil.isNotEmpty(dto.getIdentifiantCommun()))
                item.setIdentifiantCommun(dto.getIdentifiantCommun());
            if(StringUtil.isNotEmpty(dto.getRegistreCommerce()))
                item.setRegistreCommerce(dto.getRegistreCommerce());
            if(StringUtil.isNotEmpty(dto.getTaxeProfessionnelle()))
                item.setTaxeProfessionnelle(dto.getTaxeProfessionnelle());
            if(StringUtil.isNotEmpty(dto.getCnss()))
                item.setCnss(dto.getCnss());
            if(StringUtil.isNotEmpty(dto.getGroupeSociete()))
                item.setGroupeSociete(dto.getGroupeSociete());
            if(this.nationalite && dto.getNationalite()!=null)
                item.setNationalite(nationaliteConverter.toItem(dto.getNationalite())) ;

            if(this.typeIdentite && dto.getTypeIdentite()!=null)
                item.setTypeIdentite(typeIdentiteConverter.toItem(dto.getTypeIdentite())) ;

            if(this.banqueAdherente && dto.getBanqueAdherente()!=null)
                item.setBanqueAdherente(banqueConverter.toItem(dto.getBanqueAdherente())) ;




        return item;
        }
    }


    public DossierClientDto toDto(DossierClient item) {
        if (item == null) {
            return null;
        } else {
            DossierClientDto dto = new DossierClientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getNumeroIdentite()))
                dto.setNumeroIdentite(item.getNumeroIdentite());
            if(StringUtil.isNotEmpty(item.getRaisonSociale()))
                dto.setRaisonSociale(item.getRaisonSociale());
            if(StringUtil.isNotEmpty(item.getIdentifiantCommun()))
                dto.setIdentifiantCommun(item.getIdentifiantCommun());
            if(StringUtil.isNotEmpty(item.getRegistreCommerce()))
                dto.setRegistreCommerce(item.getRegistreCommerce());
            if(StringUtil.isNotEmpty(item.getTaxeProfessionnelle()))
                dto.setTaxeProfessionnelle(item.getTaxeProfessionnelle());
            if(StringUtil.isNotEmpty(item.getCnss()))
                dto.setCnss(item.getCnss());
            if(StringUtil.isNotEmpty(item.getGroupeSociete()))
                dto.setGroupeSociete(item.getGroupeSociete());
            if(this.nationalite && item.getNationalite()!=null) {
                dto.setNationalite(nationaliteConverter.toDto(item.getNationalite())) ;

            }
            if(this.typeIdentite && item.getTypeIdentite()!=null) {
                dto.setTypeIdentite(typeIdentiteConverter.toDto(item.getTypeIdentite())) ;

            }
            if(this.banqueAdherente && item.getBanqueAdherente()!=null) {
                dto.setBanqueAdherente(banqueConverter.toDto(item.getBanqueAdherente())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.nationalite = value;
        this.typeIdentite = value;
        this.banqueAdherente = value;
    }
	
    public List<DossierClient> toItem(List<DossierClientDto> dtos) {
        List<DossierClient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DossierClientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DossierClientDto> toDto(List<DossierClient> items) {
        List<DossierClientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DossierClient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DossierClientDto dto, DossierClient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getNationalite() != null)
        nationaliteConverter.copy(dto.getNationalite(), t.getNationalite());
        if (dto.getTypeIdentite() != null)
        typeIdentiteConverter.copy(dto.getTypeIdentite(), t.getTypeIdentite());
        if (dto.getBanqueAdherente() != null)
        banqueConverter.copy(dto.getBanqueAdherente(), t.getBanqueAdherente());
    }

    public List<DossierClient> copy(List<DossierClientDto> dtos) {
        List<DossierClient> result = new ArrayList<>();
        if (dtos != null) {
            for (DossierClientDto dto : dtos) {
                DossierClient instance = new DossierClient();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public NationaliteConverter getNationaliteConverter(){
        return this.nationaliteConverter;
    }
    public void setNationaliteConverter(NationaliteConverter nationaliteConverter ){
        this.nationaliteConverter = nationaliteConverter;
    }
    public TypeIdentiteConverter getTypeIdentiteConverter(){
        return this.typeIdentiteConverter;
    }
    public void setTypeIdentiteConverter(TypeIdentiteConverter typeIdentiteConverter ){
        this.typeIdentiteConverter = typeIdentiteConverter;
    }
    public BanqueConverter getBanqueConverter(){
        return this.banqueConverter;
    }
    public void setBanqueConverter(BanqueConverter banqueConverter ){
        this.banqueConverter = banqueConverter;
    }
    public boolean  isNationalite(){
        return this.nationalite;
    }
    public void  setNationalite(boolean nationalite){
        this.nationalite = nationalite;
    }
    public boolean  isTypeIdentite(){
        return this.typeIdentite;
    }
    public void  setTypeIdentite(boolean typeIdentite){
        this.typeIdentite = typeIdentite;
    }
    public boolean  isBanqueAdherente(){
        return this.banqueAdherente;
    }
    public void  setBanqueAdherente(boolean banqueAdherente){
        this.banqueAdherente = banqueAdherente;
    }
}
