package  ma.zs.task.ws.converter.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.task.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.task.zynerator.util.StringUtil;
import ma.zs.task.zynerator.converter.AbstractConverter;
import ma.zs.task.zynerator.util.DateUtil;
import ma.zs.task.bean.core.utilisateur.Utilisateur;
import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;

@Component
public class UtilisateurConverter {


    public  UtilisateurConverter() {
    }


    public Utilisateur toItem(UtilisateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Utilisateur item = new Utilisateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setEnabled(dto.getEnabled());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            item.setPasswordChanged(dto.getPasswordChanged());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public UtilisateurDto toDto(Utilisateur item) {
        if (item == null) {
            return null;
        } else {
            UtilisateurDto dto = new UtilisateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(StringUtil.isNotEmpty(item.getPassword()))
                dto.setPassword(item.getPassword());


        return dto;
        }
    }


	
    public List<Utilisateur> toItem(List<UtilisateurDto> dtos) {
        List<Utilisateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (UtilisateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<UtilisateurDto> toDto(List<Utilisateur> items) {
        List<UtilisateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Utilisateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(UtilisateurDto dto, Utilisateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Utilisateur> copy(List<UtilisateurDto> dtos) {
        List<Utilisateur> result = new ArrayList<>();
        if (dtos != null) {
            for (UtilisateurDto dto : dtos) {
                Utilisateur instance = new Utilisateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
