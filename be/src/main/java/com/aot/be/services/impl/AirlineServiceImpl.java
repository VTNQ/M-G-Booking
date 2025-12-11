package com.aot.be.services.impl;

import com.aot.be.Enum.AirlineStatus;
import com.aot.be.configurations.JwtTokenProvider;
import com.aot.be.mapper.AirlineMapper;
import com.aot.be.model.dtos.reponse.AirlineResponse;
import com.aot.be.model.dtos.request.AirlineRequest;
import com.aot.be.model.entities.Airline;
import com.aot.be.repositories.AirlineRepository;
import com.aot.be.services.AirlineService;
import com.aot.be.utils.MediaUtil;
import com.aot.be.utils.SearchHelper;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl extends
        BaseServiceImpl<AirlineResponse,Integer>
        implements AirlineService{
    private final AirlineRepository repository;
    private final AirlineMapper mapper;
    private final MediaUtil mediaUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private static final List<String> SEARCH_FIELDS = List.of("airlineCode", "airlineName");
    @Override
    public void create(AirlineRequest request, MultipartFile file) {
        try {
            Airline airline = mapper.toEntity
                    (request,
                     jwtTokenProvider.getUserIdFromJWT()
                    );
            String url = mediaUtil.uploadFile(file,"airline/");
            airline.setLogoUrl(url);
            repository.save(airline);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id,AirlineRequest request, MultipartFile file) {
        try {
            Airline airline = repository.getReferenceById(id);
            mapper.updateEntity(request,airline,
                                jwtTokenProvider.getUserIdFromJWT());

            if(file !=null && !file.isEmpty()){
                if(airline.getLogoUrl()!=null){
                    mediaUtil.deleteFile(airline.getLogoUrl());
                }
                String newUrl = mediaUtil.uploadFile(file,"airline/");
                airline.setLogoUrl(newUrl);
            }
            repository.save(airline);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void changeStatus(Integer id, Integer status) {
        Airline airline = repository.getReferenceById(id);

        airline.setStatus(AirlineStatus.fromCode(status).getCode());
        repository.save(airline);
    }

    @Override
    public Page<AirlineResponse>  getAll(
            Integer page, Integer size, String sort, String filter, String searchField,
            String searchValue, Boolean all) {
        try {
            Specification<Airline> sortable = RSQLJPASupport.toSort(sort);
            Specification<Airline> filterable = RSQLJPASupport.toSpecification(filter);
            Specification<Airline> searchable = SearchHelper.buildSearchSpec
                    (searchField,searchValue,SEARCH_FIELDS);
            Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page-1,size);
            return repository.findAll(sortable.and(filterable)
                                              .and(searchable),pageable)
                    .map(mapper::toResponse);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
