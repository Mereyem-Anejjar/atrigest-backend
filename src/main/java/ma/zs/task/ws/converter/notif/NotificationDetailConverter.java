package  ma.zs.task.ws.converter.notif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.task.ws.converter.utilisateur.UtilisateurConverter;
import ma.zs.task.ws.converter.notif.NotificationConverter;

import ma.zs.task.bean.core.notif.Notification;


import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.notif.NotificationDetail;
import ma.zs.task.ws.dto.notif.NotificationDetailDto;

@Component
public class NotificationDetailConverter {

    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    @Autowired
    private NotificationConverter notificationConverter ;
    private boolean utilisateur;
    private boolean notification;

    public  NotificationDetailConverter() {
        initObject(true);
    }


    public NotificationDetail toItem(NotificationDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        NotificationDetail item = new NotificationDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.utilisateur && dto.getUtilisateur()!=null)
                item.setUtilisateur(utilisateurConverter.toItem(dto.getUtilisateur())) ;

            if(dto.getNotification() != null && dto.getNotification().getId() != null){
                item.setNotification(new Notification());
                item.getNotification().setId(dto.getNotification().getId());
                item.getNotification().setId(dto.getNotification().getId());
            }




        return item;
        }
    }


    public NotificationDetailDto toDto(NotificationDetail item) {
        if (item == null) {
            return null;
        } else {
            NotificationDetailDto dto = new NotificationDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.utilisateur && item.getUtilisateur()!=null) {
                dto.setUtilisateur(utilisateurConverter.toDto(item.getUtilisateur())) ;

            }
            if(this.notification && item.getNotification()!=null) {
                dto.setNotification(notificationConverter.toDto(item.getNotification())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.utilisateur = value;
        this.notification = value;
    }
	
    public List<NotificationDetail> toItem(List<NotificationDetailDto> dtos) {
        List<NotificationDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (NotificationDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<NotificationDetailDto> toDto(List<NotificationDetail> items) {
        List<NotificationDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (NotificationDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(NotificationDetailDto dto, NotificationDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getUtilisateur() != null)
        utilisateurConverter.copy(dto.getUtilisateur(), t.getUtilisateur());
        if (dto.getNotification() != null)
        notificationConverter.copy(dto.getNotification(), t.getNotification());
    }

    public List<NotificationDetail> copy(List<NotificationDetailDto> dtos) {
        List<NotificationDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (NotificationDetailDto dto : dtos) {
                NotificationDetail instance = new NotificationDetail();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public NotificationConverter getNotificationConverter(){
        return this.notificationConverter;
    }
    public void setNotificationConverter(NotificationConverter notificationConverter ){
        this.notificationConverter = notificationConverter;
    }
    public boolean  isUtilisateur(){
        return this.utilisateur;
    }
    public void  setUtilisateur(boolean utilisateur){
        this.utilisateur = utilisateur;
    }
    public boolean  isNotification(){
        return this.notification;
    }
    public void  setNotification(boolean notification){
        this.notification = notification;
    }
}
