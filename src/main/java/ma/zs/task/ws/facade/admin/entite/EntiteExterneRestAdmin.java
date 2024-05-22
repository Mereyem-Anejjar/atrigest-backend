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

import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.dao.criteria.core.entite.EntiteExterneCriteria;
import ma.zs.task.service.facade.admin.entite.EntiteExterneAdminService;
import ma.zs.task.ws.converter.entite.EntiteExterneConverter;
import ma.zs.task.ws.dto.entite.EntiteExterneDto;
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
@RequestMapping("/api/admin/entiteExterne/")
public class EntiteExterneRestAdmin {




    @Operation(summary = "Finds a list of all entiteExternes")
    @GetMapping("")
    public ResponseEntity<List<EntiteExterneDto>> findAll() throws Exception {
        ResponseEntity<List<EntiteExterneDto>> res = null;
        List<EntiteExterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all entiteExternes")
    @GetMapping("optimized")
    public ResponseEntity<List<EntiteExterneDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EntiteExterneDto>> res = null;
        List<EntiteExterne> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a entiteExterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EntiteExterneDto> findById(@PathVariable Long id) {
        EntiteExterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EntiteExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a entiteExterne by nom")
    @GetMapping("nom/{nom}")
    public ResponseEntity<EntiteExterneDto> findByNom(@PathVariable String nom) {
	    EntiteExterne t = service.findByReferenceEntity(new EntiteExterne(nom));
        if (t != null) {
            converter.init(true);
            EntiteExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  entiteExterne")
    @PostMapping("")
    public ResponseEntity<EntiteExterneDto> save(@RequestBody EntiteExterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EntiteExterne myT = converter.toItem(dto);
            EntiteExterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EntiteExterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  entiteExterne")
    @PutMapping("")
    public ResponseEntity<EntiteExterneDto> update(@RequestBody EntiteExterneDto dto) throws Exception {
        ResponseEntity<EntiteExterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EntiteExterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            EntiteExterne updated = service.update(t);
            EntiteExterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of entiteExterne")
    @PostMapping("multiple")
    public ResponseEntity<List<EntiteExterneDto>> delete(@RequestBody List<EntiteExterneDto> dtos) throws Exception {
        ResponseEntity<List<EntiteExterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EntiteExterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified entiteExterne")
    @DeleteMapping("")
    public ResponseEntity<EntiteExterneDto> delete(@RequestBody EntiteExterneDto dto) throws Exception {
		ResponseEntity<EntiteExterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            EntiteExterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified entiteExterne")
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
    @Operation(summary = "Delete multiple entiteExternes by ids")
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


    @Operation(summary = "find by typeEntiteExterne id")
    @GetMapping("typeEntiteExterne/id/{id}")
    public List<EntiteExterneDto> findByTypeEntiteExterneId(@PathVariable Long id){
        return findDtos(service.findByTypeEntiteExterneId(id));
    }
    @Operation(summary = "delete by typeEntiteExterne id")
    @DeleteMapping("typeEntiteExterne/id/{id}")
    public int deleteByTypeEntiteExterneId(@PathVariable Long id){
        return service.deleteByTypeEntiteExterneId(id);
    }

    @Operation(summary = "Finds a entiteExterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EntiteExterneDto> findWithAssociatedLists(@PathVariable Long id) {
        EntiteExterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EntiteExterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds entiteExternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EntiteExterneDto>> findByCriteria(@RequestBody EntiteExterneCriteria criteria) throws Exception {
        ResponseEntity<List<EntiteExterneDto>> res = null;
        List<EntiteExterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated entiteExternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EntiteExterneCriteria criteria) throws Exception {
        List<EntiteExterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EntiteExterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets entiteExterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EntiteExterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EntiteExterneDto> findDtos(List<EntiteExterne> list){
        converter.initObject(true);
        List<EntiteExterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EntiteExterneDto> getDtoResponseEntity(EntiteExterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EntiteExterneAdminService service;
    @Autowired private EntiteExterneConverter converter;





}
