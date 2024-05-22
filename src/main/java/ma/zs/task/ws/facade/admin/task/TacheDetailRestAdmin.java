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

import ma.zs.task.bean.core.task.TacheDetail;
import ma.zs.task.dao.criteria.core.task.TacheDetailCriteria;
import ma.zs.task.service.facade.admin.task.TacheDetailAdminService;
import ma.zs.task.ws.converter.task.TacheDetailConverter;
import ma.zs.task.ws.dto.task.TacheDetailDto;
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
@RequestMapping("/api/admin/tacheDetail/")
public class TacheDetailRestAdmin {




    @Operation(summary = "Finds a list of all tacheDetails")
    @GetMapping("")
    public ResponseEntity<List<TacheDetailDto>> findAll() throws Exception {
        ResponseEntity<List<TacheDetailDto>> res = null;
        List<TacheDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<TacheDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a tacheDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TacheDetailDto> findById(@PathVariable Long id) {
        TacheDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TacheDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  tacheDetail")
    @PostMapping("")
    public ResponseEntity<TacheDetailDto> save(@RequestBody TacheDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            TacheDetail myT = converter.toItem(dto);
            TacheDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TacheDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  tacheDetail")
    @PutMapping("")
    public ResponseEntity<TacheDetailDto> update(@RequestBody TacheDetailDto dto) throws Exception {
        ResponseEntity<TacheDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TacheDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            TacheDetail updated = service.update(t);
            TacheDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tacheDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<TacheDetailDto>> delete(@RequestBody List<TacheDetailDto> dtos) throws Exception {
        ResponseEntity<List<TacheDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<TacheDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified tacheDetail")
    @DeleteMapping("")
    public ResponseEntity<TacheDetailDto> delete(@RequestBody TacheDetailDto dto) throws Exception {
		ResponseEntity<TacheDetailDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            TacheDetail t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified tacheDetail")
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
    @Operation(summary = "Delete multiple tacheDetails by ids")
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


    @Operation(summary = "find by utilisateur id")
    @GetMapping("utilisateur/id/{id}")
    public List<TacheDetailDto> findByUtilisateurId(@PathVariable Long id){
        return findDtos(service.findByUtilisateurId(id));
    }
    @Operation(summary = "delete by utilisateur id")
    @DeleteMapping("utilisateur/id/{id}")
    public int deleteByUtilisateurId(@PathVariable Long id){
        return service.deleteByUtilisateurId(id);
    }
    @Operation(summary = "find by etatAvancement id")
    @GetMapping("etatAvancement/id/{id}")
    public List<TacheDetailDto> findByEtatAvancementId(@PathVariable Long id){
        return findDtos(service.findByEtatAvancementId(id));
    }
    @Operation(summary = "delete by etatAvancement id")
    @DeleteMapping("etatAvancement/id/{id}")
    public int deleteByEtatAvancementId(@PathVariable Long id){
        return service.deleteByEtatAvancementId(id);
    }

    @Operation(summary = "Finds a tacheDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TacheDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        TacheDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TacheDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds tacheDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TacheDetailDto>> findByCriteria(@RequestBody TacheDetailCriteria criteria) throws Exception {
        ResponseEntity<List<TacheDetailDto>> res = null;
        List<TacheDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TacheDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated tacheDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TacheDetailCriteria criteria) throws Exception {
        List<TacheDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<TacheDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets tacheDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TacheDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TacheDetailDto> findDtos(List<TacheDetail> list){
        converter.initObject(true);
        List<TacheDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TacheDetailDto> getDtoResponseEntity(TacheDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TacheDetailAdminService service;
    @Autowired private TacheDetailConverter converter;





}
