package  ma.zs.task.ws.facade.admin.entite;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import ma.zs.task.dao.criteria.core.entite.TypeEntiteExterneCriteria;
import ma.zs.task.service.facade.admin.entite.TypeEntiteExterneAdminService;
import ma.zs.task.ws.converter.entite.TypeEntiteExterneConverter;
import ma.zs.task.ws.dto.entite.TypeEntiteExterneDto;
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
@RequestMapping("/api/admin/typeEntiteExterne/")
public class TypeEntiteExterneRestAdmin {




    @Operation(summary = "Finds a list of all typeEntiteExternes")
    @GetMapping("")
    public ResponseEntity<List<TypeEntiteExterneDto>> findAll() throws Exception {
        ResponseEntity<List<TypeEntiteExterneDto>> res = null;
        List<TypeEntiteExterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeEntiteExternes")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeEntiteExterneDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeEntiteExterneDto>> res = null;
        List<TypeEntiteExterne> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeEntiteExterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeEntiteExterneDto> findById(@PathVariable Long id) {
        TypeEntiteExterne t = service.findById(id);
        if (t != null) {
            TypeEntiteExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeEntiteExterne by code")
    @GetMapping("code/{code}")
    public ResponseEntity<TypeEntiteExterneDto> findByCode(@PathVariable String code) {
	    TypeEntiteExterne t = service.findByReferenceEntity(new TypeEntiteExterne(code));
        if (t != null) {
            TypeEntiteExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeEntiteExterne")
    @PostMapping("")
    public ResponseEntity<TypeEntiteExterneDto> save(@RequestBody TypeEntiteExterneDto dto) throws Exception {
        if(dto!=null){
            TypeEntiteExterne myT = converter.toItem(dto);
            TypeEntiteExterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeEntiteExterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeEntiteExterne")
    @PutMapping("")
    public ResponseEntity<TypeEntiteExterneDto> update(@RequestBody TypeEntiteExterneDto dto) throws Exception {
        ResponseEntity<TypeEntiteExterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeEntiteExterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeEntiteExterne updated = service.update(t);
            TypeEntiteExterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeEntiteExterne")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeEntiteExterneDto>> delete(@RequestBody List<TypeEntiteExterneDto> dtos) throws Exception {
        ResponseEntity<List<TypeEntiteExterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeEntiteExterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeEntiteExterne")
    @DeleteMapping("")
    public ResponseEntity<TypeEntiteExterneDto> delete(@RequestBody TypeEntiteExterneDto dto) throws Exception {
		ResponseEntity<TypeEntiteExterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeEntiteExterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeEntiteExterne")
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
    @Operation(summary = "Delete multiple typeEntiteExternes by ids")
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



    @Operation(summary = "Finds a typeEntiteExterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeEntiteExterneDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeEntiteExterne loaded =  service.findWithAssociatedLists(id);
        TypeEntiteExterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeEntiteExternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeEntiteExterneDto>> findByCriteria(@RequestBody TypeEntiteExterneCriteria criteria) throws Exception {
        ResponseEntity<List<TypeEntiteExterneDto>> res = null;
        List<TypeEntiteExterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeEntiteExternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeEntiteExterneCriteria criteria) throws Exception {
        List<TypeEntiteExterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeEntiteExterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeEntiteExterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeEntiteExterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeEntiteExterneDto> findDtos(List<TypeEntiteExterne> list){
        List<TypeEntiteExterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeEntiteExterneDto> getDtoResponseEntity(TypeEntiteExterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeEntiteExterneAdminService service;
    @Autowired private TypeEntiteExterneConverter converter;





}
