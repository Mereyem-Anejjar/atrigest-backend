package  ma.zs.task.ws.facade.admin.dc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.dao.criteria.core.dc.DossierClientCriteria;
import ma.zs.task.service.facade.admin.dc.DossierClientAdminService;
import ma.zs.task.ws.converter.dc.DossierClientConverter;
import ma.zs.task.ws.dto.dc.DossierClientDto;
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
@RequestMapping("/api/admin/dossierClient/")
public class DossierClientRestAdmin {




    @Operation(summary = "Finds a list of all dossierClients")
    @GetMapping("")
    public ResponseEntity<List<DossierClientDto>> findAll() throws Exception {
        ResponseEntity<List<DossierClientDto>> res = null;
        List<DossierClient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DossierClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all dossierClients")
    @GetMapping("optimized")
    public ResponseEntity<List<DossierClientDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DossierClientDto>> res = null;
        List<DossierClient> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DossierClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a dossierClient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DossierClientDto> findById(@PathVariable Long id) {
        DossierClient t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DossierClientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a dossierClient by nom")
    @GetMapping("nom/{nom}")
    public ResponseEntity<DossierClientDto> findByNom(@PathVariable String nom) {
	    DossierClient t = service.findByReferenceEntity(new DossierClient(nom));
        if (t != null) {
            converter.init(true);
            DossierClientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  dossierClient")
    @PostMapping("")
    public ResponseEntity<DossierClientDto> save(@RequestBody DossierClientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DossierClient myT = converter.toItem(dto);
            DossierClient t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DossierClientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  dossierClient")
    @PutMapping("")
    public ResponseEntity<DossierClientDto> update(@RequestBody DossierClientDto dto) throws Exception {
        ResponseEntity<DossierClientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DossierClient t = service.findById(dto.getId());
            converter.copy(dto,t);
            DossierClient updated = service.update(t);
            DossierClientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of dossierClient")
    @PostMapping("multiple")
    public ResponseEntity<List<DossierClientDto>> delete(@RequestBody List<DossierClientDto> dtos) throws Exception {
        ResponseEntity<List<DossierClientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DossierClient> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified dossierClient")
    @DeleteMapping("")
    public ResponseEntity<DossierClientDto> delete(@RequestBody DossierClientDto dto) throws Exception {
		ResponseEntity<DossierClientDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DossierClient t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified dossierClient")
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
    @Operation(summary = "Delete multiple dossierClients by ids")
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


    @Operation(summary = "find by typeIdentite id")
    @GetMapping("typeIdentite/id/{id}")
    public List<DossierClientDto> findByTypeIdentiteId(@PathVariable Long id){
        return findDtos(service.findByTypeIdentiteId(id));
    }
    @Operation(summary = "delete by typeIdentite id")
    @DeleteMapping("typeIdentite/id/{id}")
    public int deleteByTypeIdentiteId(@PathVariable Long id){
        return service.deleteByTypeIdentiteId(id);
    }

    @Operation(summary = "Finds a dossierClient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DossierClientDto> findWithAssociatedLists(@PathVariable Long id) {
        DossierClient loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DossierClientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds dossierClients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DossierClientDto>> findByCriteria(@RequestBody DossierClientCriteria criteria) throws Exception {
        ResponseEntity<List<DossierClientDto>> res = null;
        List<DossierClient> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DossierClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated dossierClients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DossierClientCriteria criteria) throws Exception {
        List<DossierClient> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DossierClientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets dossierClient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DossierClientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DossierClientDto> findDtos(List<DossierClient> list){
        converter.initObject(true);
        List<DossierClientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DossierClientDto> getDtoResponseEntity(DossierClientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DossierClientAdminService service;
    @Autowired private DossierClientConverter converter;





}
