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

import ma.zs.task.bean.core.notif.Notification;
import ma.zs.task.dao.criteria.core.notif.NotificationCriteria;
import ma.zs.task.service.facade.admin.notif.NotificationAdminService;
import ma.zs.task.ws.converter.notif.NotificationConverter;
import ma.zs.task.ws.dto.notif.NotificationDto;
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
@RequestMapping("/api/admin/notification/")
public class NotificationRestAdmin {




    @Operation(summary = "Finds a list of all notifications")
    @GetMapping("")
    public ResponseEntity<List<NotificationDto>> findAll() throws Exception {
        ResponseEntity<List<NotificationDto>> res = null;
        List<Notification> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<NotificationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a notification by id")
    @GetMapping("id/{id}")
    public ResponseEntity<NotificationDto> findById(@PathVariable Long id) {
        Notification t = service.findById(id);
        if (t != null) {
            converter.init(true);
            NotificationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  notification")
    @PostMapping("")
    public ResponseEntity<NotificationDto> save(@RequestBody NotificationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Notification myT = converter.toItem(dto);
            Notification t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                NotificationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  notification")
    @PutMapping("")
    public ResponseEntity<NotificationDto> update(@RequestBody NotificationDto dto) throws Exception {
        ResponseEntity<NotificationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Notification t = service.findById(dto.getId());
            converter.copy(dto,t);
            Notification updated = service.update(t);
            NotificationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of notification")
    @PostMapping("multiple")
    public ResponseEntity<List<NotificationDto>> delete(@RequestBody List<NotificationDto> dtos) throws Exception {
        ResponseEntity<List<NotificationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Notification> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified notification")
    @DeleteMapping("")
    public ResponseEntity<NotificationDto> delete(@RequestBody NotificationDto dto) throws Exception {
		ResponseEntity<NotificationDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Notification t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified notification")
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
    @Operation(summary = "Delete multiple notifications by ids")
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



    @Operation(summary = "Finds a notification and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<NotificationDto> findWithAssociatedLists(@PathVariable Long id) {
        Notification loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        NotificationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds notifications by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<NotificationDto>> findByCriteria(@RequestBody NotificationCriteria criteria) throws Exception {
        ResponseEntity<List<NotificationDto>> res = null;
        List<Notification> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<NotificationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated notifications by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody NotificationCriteria criteria) throws Exception {
        List<Notification> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<NotificationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets notification data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody NotificationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<NotificationDto> findDtos(List<Notification> list){
        converter.initList(false);
        List<NotificationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<NotificationDto> getDtoResponseEntity(NotificationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private NotificationAdminService service;
    @Autowired private NotificationConverter converter;





}
