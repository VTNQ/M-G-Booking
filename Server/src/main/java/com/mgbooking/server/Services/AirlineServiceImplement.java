package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AirLineDTO;
import com.mgbooking.server.DTOS.ListFlightDto;
import com.mgbooking.server.DTOS.UpdateFlightDTO;
import com.mgbooking.server.Entities.Airline;
import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Entities.Picture;
import com.mgbooking.server.Helper.FileHelper;
import com.mgbooking.server.Repositories.AirlineRepository;
import com.mgbooking.server.Repositories.CountryRepository;
import com.mgbooking.server.Repositories.ImageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
public class AirlineServiceImplement implements AirlineService {
    @Autowired
    private Environment environment;
    @Autowired
    private AirlineRepository AirlineRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public boolean AddFlight(AirLineDTO flightDTO) {
        try {
            Country country = countryRepository.findById(flightDTO.getCountry_id())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            Airline airline=modelMapper.map(flightDTO, Airline.class);

            airline.setCountry(country);
            Airline savedAirline= AirlineRepository.save(airline);
            int airlineId=savedAirline.getId();
           MultipartFile multipartFile=flightDTO.getImage();
           if(multipartFile!=null && !multipartFile.isEmpty()){
               String uploadDir = System.getProperty("user.dir") + "/Server/src/main/resources/static/images/flight";
               Path uploadPath = Paths.get(uploadDir);
               if (!Files.exists(uploadPath)) {
                   Files.createDirectories(uploadPath);
               }
               String filename = FileHelper.generateImageName(multipartFile.getOriginalFilename());
               Path filePath = uploadPath.resolve(filename);
               multipartFile.transferTo(filePath.toFile());
               Picture image=new Picture();
               image.setMain(true);
               image.setAirlineId(airlineId);
               image.setImageUrl(filename);
               imageRepository.save(image);
               return true;
           }else{
               return false;
           }
        }catch (Exception e){
            e.printStackTrace();
        return false;
        }


    }

    @Override
    public List<ListFlightDto> FindAll() {
        String flightUrl = "http://localhost:8686/images/flight/"; // This should be the base URL to your images

        // Get all airlines from the repository
        List<ListFlightDto> airlines = AirlineRepository.ShowAll();

        // Use modelMapper to map each airline to ListFlightDto
        List<ListFlightDto> flightDtos = modelMapper.map(airlines, new TypeToken<List<ListFlightDto>>(){}.getType());

        // Set flightUrl and concatenate image_url for each ListFlightDto
        for (ListFlightDto flightDto : flightDtos) {
            flightDto.setImage_url(flightUrl+flightDto.getImage_url()); // Set the base flight URL
        }

        return flightDtos;
    }


    @Override
    public UpdateFlightDTO FindFlight(int id) {
        try {
            return modelMapper.map(AirlineRepository.FindById(id),new TypeToken<UpdateFlightDTO>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean UpdateFlight(UpdateFlightDTO flightDTO,MultipartFile file) {
        try {
            String imagecase = flightDTO.getImage() != null ? flightDTO.getImage() : "null";
            Country country = countryRepository.findById(flightDTO.getIdCountry())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            Airline airline=modelMapper.map(flightDTO, Airline.class);
            switch (imagecase){
                case "null":

                    airline.setCountry(country);
                    AirlineRepository.save(airline);
                    break;
                default:
                    UpdateFlightDTO FindID=AirlineRepository.FindById(flightDTO.getId());
                    if (FindID == null) {
                        throw new RuntimeException("Flight not found!");
                    }
                    String uploadDir = System.getProperty("user.dir") + "/Server/src/main/resources/static/images/flight";
                    String oldFilename=FindID.getImage();
                    File oldFile=new File(uploadDir,oldFilename);
                    if(oldFile.exists() && oldFile.isFile()){
                        oldFile.delete();
                    }
                   country = countryRepository.findById(flightDTO.getIdCountry())
                            .orElseThrow(() -> new RuntimeException("Country not found"));
                     airline=modelMapper.map(flightDTO, Airline.class);
                    airline.setCountry(country);
                    airline.setCountry(country);
                    AirlineRepository.save(airline);
                    MultipartFile multipartFile=file;

                    Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    String filename=FileHelper.generateImageName(multipartFile.getOriginalFilename());
                    Path filePath = uploadPath.resolve(filename);
                    multipartFile.transferTo(filePath.toFile());
                    Picture image= imageRepository.findByImageId(flightDTO.getId());
                    image.setMain(true);
                    image.setAirlineId(flightDTO.getId());
                    image.setImageUrl(filename);
                    imageRepository.save(image);
                    break;
            }

            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ListFlightDto> ShowAirlineDto(int id) {
        List<ListFlightDto>airlines=AirlineRepository.ShowAirlineDto(id);
        return modelMapper.map(airlines,new TypeToken<List<ListFlightDto>>(){}.getType());
    }

    @Override
    public List<Airline> SearchAirline(int departureAirport, int arrivalAirport, LocalDate departureTime, String TypeFlight) {
        return modelMapper.map(AirlineRepository.SearchAirline(departureAirport,arrivalAirport,departureTime,TypeFlight),new TypeToken<List<Airline>>(){}.getType());
    }
}
