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

import ma.zs.task.bean.core.soc.TypeSociete;
import ma.zs.task.dao.criteria.core.soc.TypeSocieteCriteria;
import ma.zs.task.service.facade.admin.soc.TypeSocieteAdminService;
import ma.zs.task.ws.converter.soc.TypeSocieteConverter;
import ma.zs.task.ws.dto.soc.TypeSocieteDto;
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
@RequestMapping("/api/admin/typeSociete/")
public class TypeSocieteRestAdmin {




    @Operation(summary = "Finds a list of all typeSocietes")
    @GetMapping("")
    public ResponseEntity<List<TypeSocieteDto>> findAll() throws Exception {
        ResponseEntity<List<TypeSocieteDto>> res = null;
        List<TypeSociete> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeSocieteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeSocietes")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeSocieteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeSocieteDto>> res = null;
        List<TypeSociete> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeSocieteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeSociete by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeSocieteDto> findById(@PathVariable Long id) {
        TypeSociete t = service.findById(id);
        if (t != null) {
            TypeSocieteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeSociete by code")
    @GetMapping("code/{code}")
    public ResponseEntity<TypeSocieteDto> findByCode(@PathVariable String code) {
	    TypeSociete t = service.findByReferenceEntity(new TypeSociete(code));
        if (t != null) {
            TypeSocieteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeSociete")
    @PostMapping("")
    public ResponseEntity<TypeSocieteDto> save(@RequestBody TypeSocieteDto dto) throws Exception {
        if(dto!=null){
            TypeSociete myT = converter.toItem(dto);
            TypeSociete t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeSocieteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeSociete")
    @PutMapping("")
    public ResponseEntity<TypeSocieteDto> update(@RequestBody TypeSocieteDto dto) throws Exception {
        ResponseEntity<TypeSocieteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeSociete t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeSociete updated = service.update(t);
            TypeSocieteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeSociete")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeSocieteDto>> delete(@RequestBody List<TypeSocieteDto> dtos) throws Exception {
        ResponseEntity<List<TypeSocieteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeSociete> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeSociete")
    @DeleteMapping("")
    public ResponseEntity<TypeSocieteDto> delete(@RequestBody TypeSocieteDto dto) throws Exception {
		ResponseEntity<TypeSocieteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeSociete t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeSociete")
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
    @Operation(summary = "Delete multiple typeSocietes by ids")
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



    @Operation(summary = "Finds a typeSociete and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeSocieteDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeSociete loaded =  service.findWithAssociatedLists(id);
        TypeSocieteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeSocietes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeSocieteDto>> findByCriteria(@RequestBody TypeSocieteCriteria criteria) throws Exception {
        ResponseEntity<List<TypeSocieteDto>> res = null;
        List<TypeSociete> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeSocieteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeSocietes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeSocieteCriteria criteria) throws Exception {
        List<TypeSociete> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeSocieteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeSociete data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeSocieteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeSocieteDto> findDtos(List<TypeSociete> list){
        List<TypeSocieteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeSocieteDto> getDtoResponseEntity(TypeSocieteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeSocieteAdminService service;
    @Autowired private TypeSocieteConverter converter;





}
