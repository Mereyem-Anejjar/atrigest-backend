package  ma.zs.task.ws.converter.notif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.task.zynerator.util.ListUtil;

import ma.zs.task.ws.converter.notif.NotificationDetailConverter;
import ma.zs.task.ws.converter.utilisateur.UtilisateurConverter;



import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.notif.Notification;
import ma.zs.task.ws.dto.notif.NotificationDto;

@Component
public class NotificationConverter {

    @Autowired
    private NotificationDetailConverter notificationDetailConverter ;
    @Autowired
    private UtilisateurConverter utilisateurConverter ;
    private boolean notificationDetails;

    public  NotificationConverter() {
        initList(true);
    }


    public Notification toItem(NotificationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Notification item = new Notification();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getContenue()))
                item.setContenue(dto.getContenue());
            if(StringUtil.isNotEmpty(dto.getDateEnvoi()))
                item.setDateEnvoi(DateUtil.stringEnToDate(dto.getDateEnvoi()));

            if(this.notificationDetails && ListUtil.isNotEmpty(dto.getNotificationDetails()))
                item.setNotificationDetails(notificationDetailConverter.toItem(dto.getNotificationDetails()));


        return item;
        }
    }


    public NotificationDto toDto(Notification item) {
        if (item == null) {
            return null;
        } else {
            NotificationDto dto = new NotificationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getContenue()))
                dto.setContenue(item.getContenue());
            if(item.getDateEnvoi()!=null)
                dto.setDateEnvoi(DateUtil.dateTimeToString(item.getDateEnvoi()));
        if(this.notificationDetails && ListUtil.isNotEmpty(item.getNotificationDetails())){
            notificationDetailConverter.init(true);
            notificationDetailConverter.setNotification(false);
            dto.setNotificationDetails(notificationDetailConverter.toDto(item.getNotificationDetails()));
            notificationDetailConverter.setNotification(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.notificationDetails = value;
    }
	
    public List<Notification> toItem(List<NotificationDto> dtos) {
        List<Notification> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (NotificationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<NotificationDto> toDto(List<Notification> items) {
        List<NotificationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Notification item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(NotificationDto dto, Notification t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getNotificationDetails() != null)
            t.setNotificationDetails(notificationDetailConverter.copy(dto.getNotificationDetails()));
    }

    public List<Notification> copy(List<NotificationDto> dtos) {
        List<Notification> result = new ArrayList<>();
        if (dtos != null) {
            for (NotificationDto dto : dtos) {
                Notification instance = new Notification();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public NotificationDetailConverter getNotificationDetailConverter(){
        return this.notificationDetailConverter;
    }
    public void setNotificationDetailConverter(NotificationDetailConverter notificationDetailConverter ){
        this.notificationDetailConverter = notificationDetailConverter;
    }
    public UtilisateurConverter getUtilisateurConverter(){
        return this.utilisateurConverter;
    }
    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter ){
        this.utilisateurConverter = utilisateurConverter;
    }
    public boolean  isNotificationDetails(){
        return this.notificationDetails ;
    }
    public void  setNotificationDetails(boolean notificationDetails ){
        this.notificationDetails  = notificationDetails ;
    }
}
