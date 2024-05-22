package  ma.zs.task.ws.converter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.task.zynerator.util.ListUtil;

import ma.zs.task.ws.converter.dc.DossierClientConverter;
import ma.zs.task.ws.converter.entite.EntiteExterneConverter;
import ma.zs.task.ws.converter.task.TacheDetailConverter;
import ma.zs.task.ws.converter.commun.EtatAvancementConverter;
import ma.zs.task.ws.converter.commun.PrioriteConverter;
import ma.zs.task.ws.converter.utilisateur.UtilisateurConverter;
import ma.zs.task.ws.converter.task.TacheEntiteExterneConverter;



import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.task.Tache;
import ma.zs.task.ws.dto.task.TacheDto;

@Component
public class TacheConverter {

    @Autowired
    private DossierClientConverter dossierClientConverter ;
    @Autowired
    private EntiteExterneConverter entiteExterneConverter ;
    @Autowired
    private TacheDetailConverter tacheDetailConverter ;
    @Autowired
    private EtatAvancementConverter etatAvancementConverter ;
    @Autowired
    private PrioriteConverter prioriteConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    @Autowired
    private TacheEntiteExterneConverter tacheEntiteExterneConverter ;
    private boolean priorite;
    private boolean etatAvancement;
    private boolean dossierClient;
    private boolean tacheDetails;
    private boolean tacheEntiteExternes;

    public  TacheConverter() {
        init(true);
    }


    public Tache toItem(TacheDto dto) {
        if (dto == null) {
            return null;
        } else {
        Tache item = new Tache();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateLimite()))
                item.setDateLimite(DateUtil.stringEnToDate(dto.getDateLimite()));
            if(this.priorite && dto.getPriorite()!=null)
                item.setPriorite(prioriteConverter.toItem(dto.getPriorite())) ;

            if(this.etatAvancement && dto.getEtatAvancement()!=null)
                item.setEtatAvancement(etatAvancementConverter.toItem(dto.getEtatAvancement())) ;

            if(this.dossierClient && dto.getDossierClient()!=null)
                item.setDossierClient(dossierClientConverter.toItem(dto.getDossierClient())) ;


            if(this.tacheDetails && ListUtil.isNotEmpty(dto.getTacheDetails()))
                item.setTacheDetails(tacheDetailConverter.toItem(dto.getTacheDetails()));
            if(this.tacheEntiteExternes && ListUtil.isNotEmpty(dto.getTacheEntiteExternes()))
                item.setTacheEntiteExternes(tacheEntiteExterneConverter.toItem(dto.getTacheEntiteExternes()));


        return item;
        }
    }


    public TacheDto toDto(Tache item) {
        if (item == null) {
            return null;
        } else {
            TacheDto dto = new TacheDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateLimite()!=null)
                dto.setDateLimite(DateUtil.dateTimeToString(item.getDateLimite()));
            if(this.priorite && item.getPriorite()!=null) {
                dto.setPriorite(prioriteConverter.toDto(item.getPriorite())) ;

            }
            if(this.etatAvancement && item.getEtatAvancement()!=null) {
                dto.setEtatAvancement(etatAvancementConverter.toDto(item.getEtatAvancement())) ;

            }
            if(this.dossierClient && item.getDossierClient()!=null) {
                dto.setDossierClient(dossierClientConverter.toDto(item.getDossierClient())) ;

            }
        if(this.tacheDetails && ListUtil.isNotEmpty(item.getTacheDetails())){
            tacheDetailConverter.init(true);
            tacheDetailConverter.setTache(false);
            dto.setTacheDetails(tacheDetailConverter.toDto(item.getTacheDetails()));
            tacheDetailConverter.setTache(true);

        }
        if(this.tacheEntiteExternes && ListUtil.isNotEmpty(item.getTacheEntiteExternes())){
            tacheEntiteExterneConverter.init(true);
            tacheEntiteExterneConverter.setTache(false);
            dto.setTacheEntiteExternes(tacheEntiteExterneConverter.toDto(item.getTacheEntiteExternes()));
            tacheEntiteExterneConverter.setTache(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.tacheDetails = value;
        this.tacheEntiteExternes = value;
    }
    public void initObject(boolean value) {
        this.priorite = value;
        this.etatAvancement = value;
        this.dossierClient = value;
    }
	
    public List<Tache> toItem(List<TacheDto> dtos) {
        List<Tache> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TacheDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TacheDto> toDto(List<Tache> items) {
        List<TacheDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Tache item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TacheDto dto, Tache t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getPriorite() != null)
        prioriteConverter.copy(dto.getPriorite(), t.getPriorite());
        if (dto.getEtatAvancement() != null)
        etatAvancementConverter.copy(dto.getEtatAvancement(), t.getEtatAvancement());
        if (dto.getDossierClient() != null)
        dossierClientConverter.copy(dto.getDossierClient(), t.getDossierClient());
        if (dto.getTacheDetails() != null)
            t.setTacheDetails(tacheDetailConverter.copy(dto.getTacheDetails()));
        if (dto.getTacheEntiteExternes() != null)
            t.setTacheEntiteExternes(tacheEntiteExterneConverter.copy(dto.getTacheEntiteExternes()));
    }

    public List<Tache> copy(List<TacheDto> dtos) {
        List<Tache> result = new ArrayList<>();
        if (dtos != null) {
            for (TacheDto dto : dtos) {
                Tache instance = new Tache();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DossierClientConverter getDossierClientConverter(){
        return this.dossierClientConverter;
    }
    public void setDossierClientConverter(DossierClientConverter dossierClientConverter ){
        this.dossierClientConverter = dossierClientConverter;
    }
    public EntiteExterneConverter getEntiteExterneConverter(){
        return this.entiteExterneConverter;
    }
    public void setEntiteExterneConverter(EntiteExterneConverter entiteExterneConverter ){
        this.entiteExterneConverter = entiteExterneConverter;
    }
    public TacheDetailConverter getTacheDetailConverter(){
        return this.tacheDetailConverter;
    }
    public void setTacheDetailConverter(TacheDetailConverter tacheDetailConverter ){
        this.tacheDetailConverter = tacheDetailConverter;
    }
    public EtatAvancementConverter getEtatAvancementConverter(){
        return this.etatAvancementConverter;
    }
    public void setEtatAvancementConverter(EtatAvancementConverter etatAvancementConverter ){
        this.etatAvancementConverter = etatAvancementConverter;
    }
    public PrioriteConverter getPrioriteConverter(){
        return this.prioriteConverter;
    }
    public void setPrioriteConverter(PrioriteConverter prioriteConverter ){
        this.prioriteConverter = prioriteConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public TacheEntiteExterneConverter getTacheEntiteExterneConverter(){
        return this.tacheEntiteExterneConverter;
    }
    public void setTacheEntiteExterneConverter(TacheEntiteExterneConverter tacheEntiteExterneConverter ){
        this.tacheEntiteExterneConverter = tacheEntiteExterneConverter;
    }
    public boolean  isPriorite(){
        return this.priorite;
    }
    public void  setPriorite(boolean priorite){
        this.priorite = priorite;
    }
    public boolean  isEtatAvancement(){
        return this.etatAvancement;
    }
    public void  setEtatAvancement(boolean etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public boolean  isDossierClient(){
        return this.dossierClient;
    }
    public void  setDossierClient(boolean dossierClient){
        this.dossierClient = dossierClient;
    }
    public boolean  isTacheDetails(){
        return this.tacheDetails ;
    }
    public void  setTacheDetails(boolean tacheDetails ){
        this.tacheDetails  = tacheDetails ;
    }
    public boolean  isTacheEntiteExternes(){
        return this.tacheEntiteExternes ;
    }
    public void  setTacheEntiteExternes(boolean tacheEntiteExternes ){
        this.tacheEntiteExternes  = tacheEntiteExternes ;
    }
}
