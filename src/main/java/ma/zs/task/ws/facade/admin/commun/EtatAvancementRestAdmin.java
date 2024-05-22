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

import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.dao.criteria.core.commun.EtatAvancementCriteria;
import ma.zs.task.service.facade.admin.commun.EtatAvancementAdminService;
import ma.zs.task.ws.converter.commun.EtatAvancementConverter;
import ma.zs.task.ws.dto.commun.EtatAvancementDto;
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
@RequestMapping("/api/admin/etatAvancement/")
public class EtatAvancementRestAdmin {




    @Operation(summary = "Finds a list of all etatAvancements")
    @GetMapping("")
    public ResponseEntity<List<EtatAvancementDto>> findAll() throws Exception {
        ResponseEntity<List<EtatAvancementDto>> res = null;
        List<EtatAvancement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatAvancements")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatAvancementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EtatAvancementDto>> res = null;
        List<EtatAvancement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatAvancement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatAvancementDto> findById(@PathVariable Long id) {
        EtatAvancement t = service.findById(id);
        if (t != null) {
            EtatAvancementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatAvancement by code")
    @GetMapping("code/{code}")
    public ResponseEntity<EtatAvancementDto> findByCode(@PathVariable String code) {
	    EtatAvancement t = service.findByReferenceEntity(new EtatAvancement(code));
        if (t != null) {
            EtatAvancementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatAvancement")
    @PostMapping("")
    public ResponseEntity<EtatAvancementDto> save(@RequestBody EtatAvancementDto dto) throws Exception {
        if(dto!=null){
            EtatAvancement myT = converter.toItem(dto);
            EtatAvancement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatAvancementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatAvancement")
    @PutMapping("")
    public ResponseEntity<EtatAvancementDto> update(@RequestBody EtatAvancementDto dto) throws Exception {
        ResponseEntity<EtatAvancementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatAvancement t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatAvancement updated = service.update(t);
            EtatAvancementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatAvancement")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatAvancementDto>> delete(@RequestBody List<EtatAvancementDto> dtos) throws Exception {
        ResponseEntity<List<EtatAvancementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatAvancement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified etatAvancement")
    @DeleteMapping("")
    public ResponseEntity<EtatAvancementDto> delete(@RequestBody EtatAvancementDto dto) throws Exception {
		ResponseEntity<EtatAvancementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            EtatAvancement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatAvancement")
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
    @Operation(summary = "Delete multiple etatAvancements by ids")
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



    @Operation(summary = "Finds a etatAvancement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatAvancementDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatAvancement loaded =  service.findWithAssociatedLists(id);
        EtatAvancementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatAvancements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatAvancementDto>> findByCriteria(@RequestBody EtatAvancementCriteria criteria) throws Exception {
        ResponseEntity<List<EtatAvancementDto>> res = null;
        List<EtatAvancement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAvancementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatAvancements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatAvancementCriteria criteria) throws Exception {
        List<EtatAvancement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EtatAvancementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatAvancement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatAvancementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatAvancementDto> findDtos(List<EtatAvancement> list){
        List<EtatAvancementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatAvancementDto> getDtoResponseEntity(EtatAvancementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EtatAvancementAdminService service;
    @Autowired private EtatAvancementConverter converter;





}
