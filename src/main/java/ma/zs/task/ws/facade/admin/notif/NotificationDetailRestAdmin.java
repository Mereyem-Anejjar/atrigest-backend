package  ma.zs.task.ws.facade.admin.notif;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.task.bean.core.notif.NotificationDetail;
import ma.zs.task.dao.criteria.core.notif.NotificationDetailCriteria;
import ma.zs.task.service.facade.admin.notif.NotificationDetailAdminService;
import ma.zs.task.ws.converter.notif.NotificationDetailConverter;
import ma.zs.task.ws.dto.notif.NotificationDetailDto;
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
@RequestMapping("/api/admin/notificationDetail/")
public class NotificationDetailRestAdmin {




    @Operation(summary = "Finds a list of all notificationDetails")
    @GetMapping("")
    public ResponseEntity<List<NotificationDetailDto>> findAll() throws Exception {
        ResponseEntity<List<NotificationDetailDto>> res = null;
        List<NotificationDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<NotificationDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a notificationDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<NotificationDetailDto> findById(@PathVariable Long id) {
        NotificationDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            NotificationDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  notificationDetail")
    @PostMapping("")
    public ResponseEntity<NotificationDetailDto> save(@RequestBody NotificationDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            NotificationDetail myT = converter.toItem(dto);
            NotificationDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                NotificationDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  notificationDetail")
    @PutMapping("")
    public ResponseEntity<NotificationDetailDto> update(@RequestBody NotificationDetailDto dto) throws Exception {
        ResponseEntity<NotificationDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            NotificationDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            NotificationDetail updated = service.update(t);
            NotificationDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of notificationDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<NotificationDetailDto>> delete(@RequestBody List<NotificationDetailDto> dtos) throws Exception {
        ResponseEntity<List<NotificationDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<NotificationDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified notificationDetail")
    @DeleteMapping("")
    public ResponseEntity<NotificationDetailDto> delete(@RequestBody NotificationDetailDto dto) throws Exception {
		ResponseEntity<NotificationDetailDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            NotificationDetail t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified notificationDetail")
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
    @Operation(summary = "Delete multiple notificationDetails by ids")
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



    @Operation(summary = "Finds a notificationDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<NotificationDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        NotificationDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        NotificationDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds notificationDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<NotificationDetailDto>> findByCriteria(@RequestBody NotificationDetailCriteria criteria) throws Exception {
        ResponseEntity<List<NotificationDetailDto>> res = null;
        List<NotificationDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<NotificationDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated notificationDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody NotificationDetailCriteria criteria) throws Exception {
        List<NotificationDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<NotificationDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets notificationDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody NotificationDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<NotificationDetailDto> findDtos(List<NotificationDetail> list){
        converter.initObject(true);
        List<NotificationDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<NotificationDetailDto> getDtoResponseEntity(NotificationDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private NotificationDetailAdminService service;
    @Autowired private NotificationDetailConverter converter;





}
