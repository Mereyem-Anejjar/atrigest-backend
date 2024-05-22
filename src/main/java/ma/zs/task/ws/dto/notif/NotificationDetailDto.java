package  ma.zs.task.ws.dto.notif;

import ma.zs.task.zynerator.audit.Log;
import ma.zs.task.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDetailDto  extends AuditBaseDto {


    private UtilisateurDto utilisateur ;
    private NotificationDto notification ;



    public NotificationDetailDto(){
        super();
    }




    public UtilisateurDto getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur){
        this.utilisateur = utilisateur;
    }
    public NotificationDto getNotification(){
        return this.notification;
    }

    public void setNotification(NotificationDto notification){
        this.notification = notification;
    }






}
