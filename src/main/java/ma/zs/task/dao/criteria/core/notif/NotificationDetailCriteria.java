package  ma.zs.task.dao.criteria.core.notif;


import ma.zs.task.dao.criteria.core.utilisateur.UtilisateurCriteria;

import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;

public class NotificationDetailCriteria extends  BaseCriteria  {


    private UtilisateurCriteria utilisateur ;
    private List<UtilisateurCriteria> utilisateurs ;
    private NotificationCriteria notification ;
    private List<NotificationCriteria> notifications ;


    public NotificationDetailCriteria(){}


    public UtilisateurCriteria getUtilisateur(){
        return this.utilisateur;
    }

    public void setUtilisateur(UtilisateurCriteria utilisateur){
        this.utilisateur = utilisateur;
    }
    public List<UtilisateurCriteria> getUtilisateurs(){
        return this.utilisateurs;
    }

    public void setUtilisateurs(List<UtilisateurCriteria> utilisateurs){
        this.utilisateurs = utilisateurs;
    }
    public NotificationCriteria getNotification(){
        return this.notification;
    }

    public void setNotification(NotificationCriteria notification){
        this.notification = notification;
    }
    public List<NotificationCriteria> getNotifications(){
        return this.notifications;
    }

    public void setNotifications(List<NotificationCriteria> notifications){
        this.notifications = notifications;
    }
}
