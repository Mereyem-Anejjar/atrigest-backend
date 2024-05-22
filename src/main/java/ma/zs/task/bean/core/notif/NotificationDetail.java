package ma.zs.task.bean.core.notif;

import java.util.Objects;





import ma.zs.task.bean.core.utilisateur.Utilisateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notification_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="notification_detail_seq",sequenceName="notification_detail_seq",allocationSize=1, initialValue = 1)
public class NotificationDetail  extends BaseEntity     {

    private Long id;


    private Utilisateur utilisateur ;
    private Notification notification ;


    public NotificationDetail(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="notification_detail_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur")
    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur = utilisateur;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification")
    public Notification getNotification(){
        return this.notification;
    }
    public void setNotification(Notification notification){
        this.notification = notification;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDetail notificationDetail = (NotificationDetail) o;
        return id != null && id.equals(notificationDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

