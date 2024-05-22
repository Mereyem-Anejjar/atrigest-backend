package  ma.zs.task.ws.facade.admin.utilisateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.utilisateur.Utilisateur;
import ma.zs.task.dao.criteria.core.utilisateur.UtilisateurCriteria;
import ma.zs.task.service.facade.admin.utilisateur.UtilisateurAdminService;
import ma.zs.task.ws.converter.utilisateur.UtilisateurConverter;
import ma.zs.task.ws.dto.utilisateur.UtilisateurDto;
import ma.zs.task.zynerator.controller.AbstractController;
import ma.zs.task.zynerator.dto.AuditEntityDto;
import ma.zs.task.zynerator.util.PaginatedList;


import ma.zs.task.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.task.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.task.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/utilisateur/")
public class UtilisateurRestAdmin {




    @Operation(summary = "Finds a list of all utilisateurs")
    @GetMapping("")
    public ResponseEntity<List<UtilisateurDto>> findAll() throws Exception {
        ResponseEntity<List<UtilisateurDto>> res = null;
        List<Utilisateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<UtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a utilisateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UtilisateurDto> findById(@PathVariable Long id) {
        Utilisateur t = service.findById(id);
        if (t != null) {
            UtilisateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  utilisateur")
    @PostMapping("")
    public ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto dto) throws Exception {
        if(dto!=null){
            Utilisateur myT = converter.toItem(dto);
            Utilisateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                UtilisateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  utilisateur")
    @PutMapping("")
    public ResponseEntity<UtilisateurDto> update(@RequestBody UtilisateurDto dto) throws Exception {
        ResponseEntity<UtilisateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Utilisateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Utilisateur updated = service.update(t);
            UtilisateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of utilisateur")
    @PostMapping("multiple")
    public ResponseEntity<List<UtilisateurDto>> delete(@RequestBody List<UtilisateurDto> dtos) throws Exception {
        ResponseEntity<List<UtilisateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Utilisateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified utilisateur")
    @DeleteMapping("")
    public ResponseEntity<UtilisateurDto> delete(@RequestBody UtilisateurDto dto) throws Exception {
		ResponseEntity<UtilisateurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Utilisateur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified utilisateur")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple utilisateurs by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a utilisateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<UtilisateurDto> findWithAssociatedLists(@PathVariable Long id) {
        Utilisateur loaded =  service.findWithAssociatedLists(id);
        UtilisateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds utilisateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<UtilisateurDto>> findByCriteria(@RequestBody UtilisateurCriteria criteria) throws Exception {
        ResponseEntity<List<UtilisateurDto>> res = null;
        List<Utilisateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<UtilisateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated utilisateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody UtilisateurCriteria criteria) throws Exception {
        List<Utilisateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<UtilisateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets utilisateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody UtilisateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<UtilisateurDto> findDtos(List<Utilisateur> list){
        List<UtilisateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<UtilisateurDto> getDtoResponseEntity(UtilisateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

    @Autowired private UtilisateurAdminService service;
    @Autowired private UtilisateurConverter converter;





}
