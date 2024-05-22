package  ma.zs.task.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.dao.criteria.core.commun.TypeIdentiteCriteria;
import ma.zs.task.service.facade.admin.commun.TypeIdentiteAdminService;
import ma.zs.task.ws.converter.commun.TypeIdentiteConverter;
import ma.zs.task.ws.dto.commun.TypeIdentiteDto;
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
@RequestMapping("/api/admin/typeIdentite/")
public class TypeIdentiteRestAdmin {




    @Operation(summary = "Finds a list of all typeIdentites")
    @GetMapping("")
    public ResponseEntity<List<TypeIdentiteDto>> findAll() throws Exception {
        ResponseEntity<List<TypeIdentiteDto>> res = null;
        List<TypeIdentite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeIdentiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeIdentites")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeIdentiteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeIdentiteDto>> res = null;
        List<TypeIdentite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeIdentiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeIdentite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeIdentiteDto> findById(@PathVariable Long id) {
        TypeIdentite t = service.findById(id);
        if (t != null) {
            TypeIdentiteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeIdentite by code")
    @GetMapping("code/{code}")
    public ResponseEntity<TypeIdentiteDto> findByCode(@PathVariable String code) {
	    TypeIdentite t = service.findByReferenceEntity(new TypeIdentite(code));
        if (t != null) {
            TypeIdentiteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeIdentite")
    @PostMapping("")
    public ResponseEntity<TypeIdentiteDto> save(@RequestBody TypeIdentiteDto dto) throws Exception {
        if(dto!=null){
            TypeIdentite myT = converter.toItem(dto);
            TypeIdentite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeIdentiteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeIdentite")
    @PutMapping("")
    public ResponseEntity<TypeIdentiteDto> update(@RequestBody TypeIdentiteDto dto) throws Exception {
        ResponseEntity<TypeIdentiteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeIdentite t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeIdentite updated = service.update(t);
            TypeIdentiteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeIdentite")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeIdentiteDto>> delete(@RequestBody List<TypeIdentiteDto> dtos) throws Exception {
        ResponseEntity<List<TypeIdentiteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeIdentite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeIdentite")
    @DeleteMapping("")
    public ResponseEntity<TypeIdentiteDto> delete(@RequestBody TypeIdentiteDto dto) throws Exception {
		ResponseEntity<TypeIdentiteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeIdentite t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeIdentite")
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
    @Operation(summary = "Delete multiple typeIdentites by ids")
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



    @Operation(summary = "Finds a typeIdentite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeIdentiteDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeIdentite loaded =  service.findWithAssociatedLists(id);
        TypeIdentiteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeIdentites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeIdentiteDto>> findByCriteria(@RequestBody TypeIdentiteCriteria criteria) throws Exception {
        ResponseEntity<List<TypeIdentiteDto>> res = null;
        List<TypeIdentite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeIdentiteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeIdentites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeIdentiteCriteria criteria) throws Exception {
        List<TypeIdentite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeIdentiteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeIdentite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeIdentiteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeIdentiteDto> findDtos(List<TypeIdentite> list){
        List<TypeIdentiteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeIdentiteDto> getDtoResponseEntity(TypeIdentiteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeIdentiteAdminService service;
    @Autowired private TypeIdentiteConverter converter;





}
