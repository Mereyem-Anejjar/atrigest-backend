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

import ma.zs.task.bean.core.task.TacheEntiteExterne;
import ma.zs.task.dao.criteria.core.task.TacheEntiteExterneCriteria;
import ma.zs.task.service.facade.admin.task.TacheEntiteExterneAdminService;
import ma.zs.task.ws.converter.task.TacheEntiteExterneConverter;
import ma.zs.task.ws.dto.task.TacheEntiteExterneDto;
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
@RequestMapping("/api/admin/tacheEntiteExterne/")
public class TacheEntiteExterneRestAdmin {




    @Operation(summary = "Finds a list of all tacheEntiteExternes")
    @GetMapping("")
    public ResponseEntity<List<TacheEntiteExterneDto>> findAll() throws Exception {
        ResponseEntity<List<TacheEntiteExterneDto>> res = null;
        List<TacheEntiteExterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<TacheEntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a tacheEntiteExterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TacheEntiteExterneDto> findById(@PathVariable Long id) {
        TacheEntiteExterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            TacheEntiteExterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  tacheEntiteExterne")
    @PostMapping("")
    public ResponseEntity<TacheEntiteExterneDto> save(@RequestBody TacheEntiteExterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            TacheEntiteExterne myT = converter.toItem(dto);
            TacheEntiteExterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TacheEntiteExterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  tacheEntiteExterne")
    @PutMapping("")
    public ResponseEntity<TacheEntiteExterneDto> update(@RequestBody TacheEntiteExterneDto dto) throws Exception {
        ResponseEntity<TacheEntiteExterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TacheEntiteExterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            TacheEntiteExterne updated = service.update(t);
            TacheEntiteExterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of tacheEntiteExterne")
    @PostMapping("multiple")
    public ResponseEntity<List<TacheEntiteExterneDto>> delete(@RequestBody List<TacheEntiteExterneDto> dtos) throws Exception {
        ResponseEntity<List<TacheEntiteExterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<TacheEntiteExterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified tacheEntiteExterne")
    @DeleteMapping("")
    public ResponseEntity<TacheEntiteExterneDto> delete(@RequestBody TacheEntiteExterneDto dto) throws Exception {
		ResponseEntity<TacheEntiteExterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            TacheEntiteExterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified tacheEntiteExterne")
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
    @Operation(summary = "Delete multiple tacheEntiteExternes by ids")
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



    @Operation(summary = "Finds a tacheEntiteExterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TacheEntiteExterneDto> findWithAssociatedLists(@PathVariable Long id) {
        TacheEntiteExterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        TacheEntiteExterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds tacheEntiteExternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TacheEntiteExterneDto>> findByCriteria(@RequestBody TacheEntiteExterneCriteria criteria) throws Exception {
        ResponseEntity<List<TacheEntiteExterneDto>> res = null;
        List<TacheEntiteExterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<TacheEntiteExterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated tacheEntiteExternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TacheEntiteExterneCriteria criteria) throws Exception {
        List<TacheEntiteExterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<TacheEntiteExterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets tacheEntiteExterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TacheEntiteExterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TacheEntiteExterneDto> findDtos(List<TacheEntiteExterne> list){
        converter.initObject(true);
        List<TacheEntiteExterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TacheEntiteExterneDto> getDtoResponseEntity(TacheEntiteExterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TacheEntiteExterneAdminService service;
    @Autowired private TacheEntiteExterneConverter converter;





}
