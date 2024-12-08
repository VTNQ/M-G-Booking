package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Entities.Hotel;
import com.mgbooking.server.Entities.Picture;
import com.mgbooking.server.Helper.FileHelper;
import com.mgbooking.server.Repositories.DistrictRepository;
import com.mgbooking.server.Repositories.HotelRepository;
import com.mgbooking.server.Repositories.ImageRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class HotelServiceImplement implements HotelService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OwnerService ownerService;
    @Override
    public boolean addHotel(HotelDTO hotel) {
        try{
            District district = districtRepository.findById(hotel.getDistrict_id())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            Hotel hoteldto=modelMapper.map(hotel, Hotel.class);
            hoteldto.setDistrict(district);
            hoteldto.setCityId(hotel.getCity_id());
            Hotel insertDto=hotelRepository.save(hoteldto);
            String uploadDir = System.getProperty("user.dir") + "/Server/src/main/resources/static/images/hotel";
               Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String filename=FileHelper.generateImageName(hotel.getImage().getOriginalFilename());
                Path filePath=uploadPath.resolve(filename);
                hotel.getImage().transferTo(filePath.toFile());
                Picture picture=new Picture();
                picture.setHotelId(insertDto.getId());
                picture.setImageUrl(filename);
                picture.setMain(true);
                imageRepository.save(picture);
                for(MultipartFile multipartFile :hotel.getImages()){
                    String filenameImages=FileHelper.generateImageName(multipartFile.getOriginalFilename());
                    Path filePathImages=uploadPath.resolve(filenameImages);
                    multipartFile.transferTo(filePathImages.toFile());
                    Picture pictureImage=new Picture();
                    pictureImage.setHotelId(insertDto.getId());
                    pictureImage.setImageUrl(filenameImages);
                    pictureImage.setMain(false);
                    imageRepository.save(pictureImage);
                }
//
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HotelListDto> FindAllHotels(String token) {
        try{

            if(token.startsWith("Bearer ")){
                token = token.substring(7);
            }
            AccountDto accountDto=ownerService.GetAccount(token);
            return modelMapper.map(hotelRepository.FindHotelAll(accountDto.getId()),new TypeToken<List<HotelListDto>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HotelUpdateDTO findHotels(int id) {
       try {
           HotelUpdateDTO hotels=hotelRepository.findHotelById(id);
            hotels.setImageList(imageRepository.findListImage(id));
            return hotels;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }


}
