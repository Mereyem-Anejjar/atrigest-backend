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

import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.dao.criteria.core.commun.PrioriteCriteria;
import ma.zs.task.service.facade.admin.commun.PrioriteAdminService;
import ma.zs.task.ws.converter.commun.PrioriteConverter;
import ma.zs.task.ws.dto.commun.PrioriteDto;
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
@RequestMapping("/api/admin/priorite/")
public class PrioriteRestAdmin {




    @Operation(summary = "Finds a list of all priorites")
    @GetMapping("")
    public ResponseEntity<List<PrioriteDto>> findAll() throws Exception {
        ResponseEntity<List<PrioriteDto>> res = null;
        List<Priorite> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrioriteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all priorites")
    @GetMapping("optimized")
    public ResponseEntity<List<PrioriteDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PrioriteDto>> res = null;
        List<Priorite> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrioriteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a priorite by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PrioriteDto> findById(@PathVariable Long id) {
        Priorite t = service.findById(id);
        if (t != null) {
            PrioriteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a priorite by code")
    @GetMapping("code/{code}")
    public ResponseEntity<PrioriteDto> findByCode(@PathVariable String code) {
	    Priorite t = service.findByReferenceEntity(new Priorite(code));
        if (t != null) {
            PrioriteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  priorite")
    @PostMapping("")
    public ResponseEntity<PrioriteDto> save(@RequestBody PrioriteDto dto) throws Exception {
        if(dto!=null){
            Priorite myT = converter.toItem(dto);
            Priorite t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PrioriteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  priorite")
    @PutMapping("")
    public ResponseEntity<PrioriteDto> update(@RequestBody PrioriteDto dto) throws Exception {
        ResponseEntity<PrioriteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Priorite t = service.findById(dto.getId());
            converter.copy(dto,t);
            Priorite updated = service.update(t);
            PrioriteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of priorite")
    @PostMapping("multiple")
    public ResponseEntity<List<PrioriteDto>> delete(@RequestBody List<PrioriteDto> dtos) throws Exception {
        ResponseEntity<List<PrioriteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Priorite> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified priorite")
    @DeleteMapping("")
    public ResponseEntity<PrioriteDto> delete(@RequestBody PrioriteDto dto) throws Exception {
		ResponseEntity<PrioriteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Priorite t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified priorite")
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
    @Operation(summary = "Delete multiple priorites by ids")
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



    @Operation(summary = "Finds a priorite and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PrioriteDto> findWithAssociatedLists(@PathVariable Long id) {
        Priorite loaded =  service.findWithAssociatedLists(id);
        PrioriteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds priorites by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PrioriteDto>> findByCriteria(@RequestBody PrioriteCriteria criteria) throws Exception {
        ResponseEntity<List<PrioriteDto>> res = null;
        List<Priorite> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrioriteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated priorites by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PrioriteCriteria criteria) throws Exception {
        List<Priorite> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PrioriteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets priorite data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PrioriteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PrioriteDto> findDtos(List<Priorite> list){
        List<PrioriteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PrioriteDto> getDtoResponseEntity(PrioriteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PrioriteAdminService service;
    @Autowired private PrioriteConverter converter;





}
