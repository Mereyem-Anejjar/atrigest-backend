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

import ma.zs.task.bean.core.soc.RoleAssocie;
import ma.zs.task.dao.criteria.core.soc.RoleAssocieCriteria;
import ma.zs.task.service.facade.admin.soc.RoleAssocieAdminService;
import ma.zs.task.ws.converter.soc.RoleAssocieConverter;
import ma.zs.task.ws.dto.soc.RoleAssocieDto;
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
@RequestMapping("/api/admin/roleAssocie/")
public class RoleAssocieRestAdmin {




    @Operation(summary = "Finds a list of all roleAssocies")
    @GetMapping("")
    public ResponseEntity<List<RoleAssocieDto>> findAll() throws Exception {
        ResponseEntity<List<RoleAssocieDto>> res = null;
        List<RoleAssocie> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleAssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all roleAssocies")
    @GetMapping("optimized")
    public ResponseEntity<List<RoleAssocieDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RoleAssocieDto>> res = null;
        List<RoleAssocie> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleAssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a roleAssocie by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RoleAssocieDto> findById(@PathVariable Long id) {
        RoleAssocie t = service.findById(id);
        if (t != null) {
            RoleAssocieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a roleAssocie by code")
    @GetMapping("code/{code}")
    public ResponseEntity<RoleAssocieDto> findByCode(@PathVariable String code) {
	    RoleAssocie t = service.findByReferenceEntity(new RoleAssocie(code));
        if (t != null) {
            RoleAssocieDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  roleAssocie")
    @PostMapping("")
    public ResponseEntity<RoleAssocieDto> save(@RequestBody RoleAssocieDto dto) throws Exception {
        if(dto!=null){
            RoleAssocie myT = converter.toItem(dto);
            RoleAssocie t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RoleAssocieDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  roleAssocie")
    @PutMapping("")
    public ResponseEntity<RoleAssocieDto> update(@RequestBody RoleAssocieDto dto) throws Exception {
        ResponseEntity<RoleAssocieDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RoleAssocie t = service.findById(dto.getId());
            converter.copy(dto,t);
            RoleAssocie updated = service.update(t);
            RoleAssocieDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of roleAssocie")
    @PostMapping("multiple")
    public ResponseEntity<List<RoleAssocieDto>> delete(@RequestBody List<RoleAssocieDto> dtos) throws Exception {
        ResponseEntity<List<RoleAssocieDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<RoleAssocie> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified roleAssocie")
    @DeleteMapping("")
    public ResponseEntity<RoleAssocieDto> delete(@RequestBody RoleAssocieDto dto) throws Exception {
		ResponseEntity<RoleAssocieDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            RoleAssocie t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified roleAssocie")
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
    @Operation(summary = "Delete multiple roleAssocies by ids")
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



    @Operation(summary = "Finds a roleAssocie and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RoleAssocieDto> findWithAssociatedLists(@PathVariable Long id) {
        RoleAssocie loaded =  service.findWithAssociatedLists(id);
        RoleAssocieDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds roleAssocies by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RoleAssocieDto>> findByCriteria(@RequestBody RoleAssocieCriteria criteria) throws Exception {
        ResponseEntity<List<RoleAssocieDto>> res = null;
        List<RoleAssocie> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<RoleAssocieDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated roleAssocies by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RoleAssocieCriteria criteria) throws Exception {
        List<RoleAssocie> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<RoleAssocieDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets roleAssocie data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RoleAssocieCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RoleAssocieDto> findDtos(List<RoleAssocie> list){
        List<RoleAssocieDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RoleAssocieDto> getDtoResponseEntity(RoleAssocieDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private RoleAssocieAdminService service;
    @Autowired private RoleAssocieConverter converter;





}
