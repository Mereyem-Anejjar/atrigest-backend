package  ma.zs.task.ws.facade.admin.soc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.soc.Associe;
import ma.zs.task.dao.criteria.core.soc.AssocieCriteria;
import ma.zs.task.service.facade.admin.soc.AssocieAdminService;
import ma.zs.task.ws.converter.soc.AssocieConverter;
import ma.zs.task.ws.dto.soc.AssocieDto;
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
@RequestMapping("/api/admin/associe/")
public class AssocieRestAdmin {




    @Operation(summary = "Finds a list of all associes")
    @GetMapping("")
    public ResponseEntity<List<AssocieDto>> findAll() throws Exception {
        ResponseEntity<List<AssocieDto>> res = null;
        List<Associe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all associes")
    @GetMapping("optimized")
    public ResponseEntity<List<AssocieDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<AssocieDto>> res = null;
        List<Associe> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a associe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AssocieDto> findById(@PathVariable Long id) {
        Associe t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AssocieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a associe by nom")
    @GetMapping("nom/{nom}")
    public ResponseEntity<AssocieDto> findByNom(@PathVariable String nom) {
	    Associe t = service.findByReferenceEntity(new Associe(nom));
        if (t != null) {
            converter.init(true);
            AssocieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  associe")
    @PostMapping("")
    public ResponseEntity<AssocieDto> save(@RequestBody AssocieDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Associe myT = converter.toItem(dto);
            Associe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AssocieDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  associe")
    @PutMapping("")
    public ResponseEntity<AssocieDto> update(@RequestBody AssocieDto dto) throws Exception {
        ResponseEntity<AssocieDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Associe t = service.findById(dto.getId());
            converter.copy(dto,t);
            Associe updated = service.update(t);
            AssocieDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of associe")
    @PostMapping("multiple")
    public ResponseEntity<List<AssocieDto>> delete(@RequestBody List<AssocieDto> dtos) throws Exception {
        ResponseEntity<List<AssocieDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Associe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified associe")
    @DeleteMapping("")
    public ResponseEntity<AssocieDto> delete(@RequestBody AssocieDto dto) throws Exception {
		ResponseEntity<AssocieDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Associe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified associe")
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
    @Operation(summary = "Delete multiple associes by ids")
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


    @Operation(summary = "find by societe id")
    @GetMapping("societe/id/{id}")
    public List<AssocieDto> findBySocieteId(@PathVariable Long id){
        return findDtos(service.findBySocieteId(id));
    }
    @Operation(summary = "delete by societe id")
    @DeleteMapping("societe/id/{id}")
    public int deleteBySocieteId(@PathVariable Long id){
        return service.deleteBySocieteId(id);
    }
    @Operation(summary = "find by roleAssocie id")
    @GetMapping("roleAssocie/id/{id}")
    public List<AssocieDto> findByRoleAssocieId(@PathVariable Long id){
        return findDtos(service.findByRoleAssocieId(id));
    }
    @Operation(summary = "delete by roleAssocie id")
    @DeleteMapping("roleAssocie/id/{id}")
    public int deleteByRoleAssocieId(@PathVariable Long id){
        return service.deleteByRoleAssocieId(id);
    }

    @Operation(summary = "Finds a associe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AssocieDto> findWithAssociatedLists(@PathVariable Long id) {
        Associe loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AssocieDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds associes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AssocieDto>> findByCriteria(@RequestBody AssocieCriteria criteria) throws Exception {
        ResponseEntity<List<AssocieDto>> res = null;
        List<Associe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated associes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AssocieCriteria criteria) throws Exception {
        List<Associe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AssocieDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets associe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AssocieCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AssocieDto> findDtos(List<Associe> list){
        converter.initObject(true);
        List<AssocieDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AssocieDto> getDtoResponseEntity(AssocieDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AssocieAdminService service;
    @Autowired private AssocieConverter converter;





}
