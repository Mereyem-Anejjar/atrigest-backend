package  ma.zs.task.ws.dto.notif;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDto  extends AuditBaseDto {

    private String contenue  ;
    private String dateEnvoi ;


    private List<NotificationDetailDto> notificationDetails ;


    public NotificationDto(){
        super();
    }



    @Log
    public String getContenue(){
        return this.contenue;
    }
    public void setContenue(String contenue){
        this.contenue = contenue;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEnvoi(){
        return this.dateEnvoi;
    }
    public void setDateEnvoi(String dateEnvoi){
        this.dateEnvoi = dateEnvoi;
    }





    public List<NotificationDetailDto> getNotificationDetails(){
        return this.notificationDetails;
    }

    public void setNotificationDetails(List<NotificationDetailDto> notificationDetails){
        this.notificationDetails = notificationDetails;
    }



}
