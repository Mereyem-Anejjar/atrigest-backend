package ma.zs.task.bean.core.utilisateur;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.task.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.task.zynerator.security.bean.User;

@Entity
@Table(name = "utilisateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="utilisateur_seq",sequenceName="utilisateur_seq",allocationSize=1, initialValue = 1)
public class Utilisateur  extends User    {


    public Utilisateur(String username) {
        super(username);
    }
    @Column(length = 500)
    private String description;
    @Column(columnDefinition = "boolean default false")
    private boolean credentialsNonExpired = false;
    @Column(columnDefinition = "boolean default false")
    private boolean enabled = false;
    @Column(columnDefinition = "boolean default false")
    private boolean accountNonExpired = false;
    @Column(columnDefinition = "boolean default false")
    private boolean accountNonLocked = false;
    @Column(columnDefinition = "boolean default false")
    private boolean passwordChanged = false;
    @Column(length = 500)
    private String username;
    @Column(length = 500)
    private String password;



    public Utilisateur(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="utilisateur_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public boolean  getCredentialsNonExpired(){
        return this.credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean  getEnabled(){
        return this.enabled;
    }
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    public boolean  getAccountNonExpired(){
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
    }
    public boolean  getAccountNonLocked(){
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
    }
    public boolean  getPasswordChanged(){
        return this.passwordChanged;
    }
    public void setPasswordChanged(boolean passwordChanged){
        this.passwordChanged = passwordChanged;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur utilisateur = (Utilisateur) o;
        return id != null && id.equals(utilisateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

