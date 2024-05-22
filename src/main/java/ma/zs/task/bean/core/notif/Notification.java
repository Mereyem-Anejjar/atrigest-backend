package ma.zs.task.bean.core.notif;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.task.bean.core.utilisateur.Utilisateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notification")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="notification_seq",sequenceName="notification_seq",allocationSize=1, initialValue = 1)
public class Notification  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String contenue;
    private LocalDateTime dateEnvoi ;


    private List<NotificationDetail> notificationDetails ;

    public Notification(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="notification_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @OneToMany(mappedBy = "notification")

    public List<NotificationDetail> getNotificationDetails(){
        return this.notificationDetails;
    }
    public void setNotificationDetails(List<NotificationDetail> notificationDetails){
        this.notificationDetails = notificationDetails;
    }
    public String getContenue(){
        return this.contenue;
    }
    public void setContenue(String contenue){
        this.contenue = contenue;
    }
    public LocalDateTime getDateEnvoi(){
        return this.dateEnvoi;
    }
    public void setDateEnvoi(LocalDateTime dateEnvoi){
        this.dateEnvoi = dateEnvoi;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return id != null && id.equals(notification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

