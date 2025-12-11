package com.aot.be.controllers;

import com.aot.be.Enum.AirlineStatus;
import com.aot.be.model.dtos.reponse.AirlineResponse;
import com.aot.be.model.dtos.reponse.ApiResponse;
import com.aot.be.model.dtos.reponse.PageResponse;
import com.aot.be.model.dtos.request.AirlineRequest;
import com.aot.be.services.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService service;

    @PreAuthorize("hasRole( 'SUPERADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> save(
            @ModelAttribute
            AirlineRequest request, @RequestParam("file") MultipartFile file
                                                 ) {
        service.create(request, file);
        return ResponseEntity.ok(ApiResponse.created("Airline created successfully"));
    }
    @PreAuthorize("hasRole( 'SUPERADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AirlineResponse>>> findAll
            (@RequestParam(defaultValue = "1") int page,
             @RequestParam(defaultValue = "5") int size,
             @RequestParam(defaultValue = "id,desc") String sort,
             @RequestParam(required = false) String filter,
             @RequestParam(required = false) String searchField,
             @RequestParam(required = false) String searchValue,
             @RequestParam(required = false) boolean all){
            PageResponse<AirlineResponse> pageResponse =
                new PageResponse<>(service.getAll(page,size,sort,filter,searchField,searchValue,all));
                return ResponseEntity.ok(
                        ApiResponse.ok(pageResponse));
    }
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Void>> update
            (@ModelAttribute AirlineRequest request,
             @PathVariable("id") Integer id,
             @RequestParam("file")MultipartFile file){
        service.update(id,request,file);
        return ResponseEntity.ok(ApiResponse.success("Airline updated successfully",null));
    }

    @PreAuthorize("hasRole('SUPERADMIN')")
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Void>> changeStatus
            (@PathVariable Integer id,
             @RequestParam Integer status){
        String name = AirlineStatus.fromCode(status).getDescription();
        String message = String.format("Change status %s successfully", name);

        service.changeStatus(id,status);
        return ResponseEntity.ok(ApiResponse.success(
                message,
                null));
    }
}
