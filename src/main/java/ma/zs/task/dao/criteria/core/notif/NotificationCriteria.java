package  ma.zs.task.dao.criteria.core.notif;



import ma.zs.task.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class NotificationCriteria extends  BaseCriteria  {

    private String contenue;
    private String contenueLike;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateEnvoiFrom;
    private LocalDateTime dateEnvoiTo;



    public NotificationCriteria(){}

    public String getContenue(){
        return this.contenue;
    }
    public void setContenue(String contenue){
        this.contenue = contenue;
    }
    public String getContenueLike(){
        return this.contenueLike;
    }
    public void setContenueLike(String contenueLike){
        this.contenueLike = contenueLike;
    }

    public LocalDateTime getDateEnvoi(){
        return this.dateEnvoi;
    }
    public void setDateEnvoi(LocalDateTime dateEnvoi){
        this.dateEnvoi = dateEnvoi;
    }
    public LocalDateTime getDateEnvoiFrom(){
        return this.dateEnvoiFrom;
    }
    public void setDateEnvoiFrom(LocalDateTime dateEnvoiFrom){
        this.dateEnvoiFrom = dateEnvoiFrom;
    }
    public LocalDateTime getDateEnvoiTo(){
        return this.dateEnvoiTo;
    }
    public void setDateEnvoiTo(LocalDateTime dateEnvoiTo){
        this.dateEnvoiTo = dateEnvoiTo;
    }

}
