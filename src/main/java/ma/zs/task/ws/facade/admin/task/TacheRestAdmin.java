package  ma.zs.task.ws.facade.admin.task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.task.Tache;
import ma.zs.task.dao.criteria.core.task.TacheCriteria;
import ma.zs.task.service.facade.admin.task.TacheAdminService;
import ma.zs.task.ws.converter.task.TacheConverter;
import ma.zs.task.ws.dto.task.TacheDto;
import ma.zs.task.zynerator.controller.AbstractController;
import ma.zs.task.zynerator.dto.AuditEntityDto;
import ma.zs.task.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.task.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.task.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/tache/")
public class TacheRestAdmin {




    @Operation(summary = "Finds a list of all taches")
    @GetMapping("")
    public ResponseEntity<List<TacheDto>> findAll() throws Exception {
        ResponseEntity<List<TacheDto>> res = null;
        List<Tache> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<TacheDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a tache by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TacheDto> findById(@PathVariable Long id) {
        Tache t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TacheDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  tache")
    @PostMapping("")
    public ResponseEntity<TacheDto> save(@RequestBody TacheDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Tache myT = converter.toItem(dto);
            Tache t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TacheDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  tache")
    @PutMapping("")
    public ResponseEntity<TacheDto> update(@RequestBody TacheDto dto) throws Exception {
        ResponseEntity<TacheDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Tache t = service.findById(dto.getId());
            converter.copy(dto,t);
            Tache updated = service.update(t);
            TacheDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tache")
    @PostMapping("multiple")
    public ResponseEntity<List<TacheDto>> delete(@RequestBody List<TacheDto> dtos) throws Exception {
        ResponseEntity<List<TacheDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Tache> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified tache")
    @DeleteMapping("")
    public ResponseEntity<TacheDto> delete(@RequestBody TacheDto dto) throws Exception {
		ResponseEntity<TacheDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Tache t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified tache")
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
    @Operation(summary = "Delete multiple taches by ids")
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


    @Operation(summary = "find by priorite id")
    @GetMapping("priorite/id/{id}")
    public List<TacheDto> findByPrioriteId(@PathVariable Long id){
        return findDtos(service.findByPrioriteId(id));
    }
    @Operation(summary = "delete by priorite id")
    @DeleteMapping("priorite/id/{id}")
    public int deleteByPrioriteId(@PathVariable Long id){
        return service.deleteByPrioriteId(id);
    }
    @Operation(summary = "find by etatAvancement id")
    @GetMapping("etatAvancement/id/{id}")
    public List<TacheDto> findByEtatAvancementId(@PathVariable Long id){
        return findDtos(service.findByEtatAvancementId(id));
    }
    @Operation(summary = "delete by etatAvancement id")
    @DeleteMapping("etatAvancement/id/{id}")
    public int deleteByEtatAvancementId(@PathVariable Long id){
        return service.deleteByEtatAvancementId(id);
    }
    @Operation(summary = "find by dossierClient id")
    @GetMapping("dossierClient/id/{id}")
    public List<TacheDto> findByDossierClientId(@PathVariable Long id){
        return findDtos(service.findByDossierClientId(id));
    }
    @Operation(summary = "delete by dossierClient id")
    @DeleteMapping("dossierClient/id/{id}")
    public int deleteByDossierClientId(@PathVariable Long id){
        return service.deleteByDossierClientId(id);
    }

    @Operation(summary = "Finds a tache and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TacheDto> findWithAssociatedLists(@PathVariable Long id) {
        Tache loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TacheDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds taches by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TacheDto>> findByCriteria(@RequestBody TacheCriteria criteria) throws Exception {
        ResponseEntity<List<TacheDto>> res = null;
        List<Tache> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<TacheDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated taches by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TacheCriteria criteria) throws Exception {
        List<Tache> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<TacheDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets tache data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TacheCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TacheDto> findDtos(List<Tache> list){
        converter.initList(false);
        converter.initObject(true);
        List<TacheDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TacheDto> getDtoResponseEntity(TacheDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TacheAdminService service;
    @Autowired private TacheConverter converter;





}
