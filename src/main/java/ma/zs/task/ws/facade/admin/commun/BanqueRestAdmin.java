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

import ma.zs.task.bean.core.commun.Banque;
import ma.zs.task.dao.criteria.core.commun.BanqueCriteria;
import ma.zs.task.service.facade.admin.commun.BanqueAdminService;
import ma.zs.task.ws.converter.commun.BanqueConverter;
import ma.zs.task.ws.dto.commun.BanqueDto;
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
@RequestMapping("/api/admin/banque/")
public class BanqueRestAdmin {




    @Operation(summary = "Finds a list of all banques")
    @GetMapping("")
    public ResponseEntity<List<BanqueDto>> findAll() throws Exception {
        ResponseEntity<List<BanqueDto>> res = null;
        List<Banque> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BanqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all banques")
    @GetMapping("optimized")
    public ResponseEntity<List<BanqueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<BanqueDto>> res = null;
        List<Banque> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BanqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a banque by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BanqueDto> findById(@PathVariable Long id) {
        Banque t = service.findById(id);
        if (t != null) {
            BanqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a banque by code")
    @GetMapping("code/{code}")
    public ResponseEntity<BanqueDto> findByCode(@PathVariable String code) {
	    Banque t = service.findByReferenceEntity(new Banque(code));
        if (t != null) {
            BanqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  banque")
    @PostMapping("")
    public ResponseEntity<BanqueDto> save(@RequestBody BanqueDto dto) throws Exception {
        if(dto!=null){
            Banque myT = converter.toItem(dto);
            Banque t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                BanqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  banque")
    @PutMapping("")
    public ResponseEntity<BanqueDto> update(@RequestBody BanqueDto dto) throws Exception {
        ResponseEntity<BanqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Banque t = service.findById(dto.getId());
            converter.copy(dto,t);
            Banque updated = service.update(t);
            BanqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of banque")
    @PostMapping("multiple")
    public ResponseEntity<List<BanqueDto>> delete(@RequestBody List<BanqueDto> dtos) throws Exception {
        ResponseEntity<List<BanqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Banque> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified banque")
    @DeleteMapping("")
    public ResponseEntity<BanqueDto> delete(@RequestBody BanqueDto dto) throws Exception {
		ResponseEntity<BanqueDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Banque t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified banque")
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
    @Operation(summary = "Delete multiple banques by ids")
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



    @Operation(summary = "Finds a banque and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<BanqueDto> findWithAssociatedLists(@PathVariable Long id) {
        Banque loaded =  service.findWithAssociatedLists(id);
        BanqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds banques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BanqueDto>> findByCriteria(@RequestBody BanqueCriteria criteria) throws Exception {
        ResponseEntity<List<BanqueDto>> res = null;
        List<Banque> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<BanqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated banques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BanqueCriteria criteria) throws Exception {
        List<Banque> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<BanqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets banque data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BanqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<BanqueDto> findDtos(List<Banque> list){
        List<BanqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<BanqueDto> getDtoResponseEntity(BanqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private BanqueAdminService service;
    @Autowired private BanqueConverter converter;





}
