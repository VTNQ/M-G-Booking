package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.DTOS.Hotel.ImageListDto;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
    private final java.util.concurrent.ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    @Override
    public boolean addHotel(HotelDTO hotel) {
        try{
            District district = districtRepository.findById(hotel.getDistrict_id())
                    .orElseThrow(() -> new RuntimeException("District not found"));
            Hotel hoteldto=modelMapper.map(hotel, Hotel.class);
            hoteldto.setDistrict(district);
            hoteldto.setCityId(hotel.getCity_id());
            Hotel insertDto=hotelRepository.save(hoteldto);


            String uploadDir = Paths.get(System.getProperty("user.dir"), "Server", "src", "main", "resources", "static", "images", "hotel").toString();
            Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
            String newFileName = FileHelper.generateImageName(hotel.getImage().getOriginalFilename());
                if(saveFileWithStream(hotel.getImage(), uploadDir, newFileName)){
                    Picture picture=new Picture();
                    picture.setHotelId(insertDto.getId());
                    picture.setImageUrl(newFileName);
                    picture.setMain(true);
                    imageRepository.save(picture);
                }else{
                    return false;
                }


                if(hotel.getImages() != null && !hotel.getImages().isEmpty()){
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

                }

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

            return hotels;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override

    public boolean updateHotel(HotelUpdateDTO hotel, MultipartFile multi) {
        try {
            // Find district associated with the hotel
            District district = districtRepository.findById(hotel.getDistrict_id())
                    .orElseThrow(() -> new RuntimeException("District not found"));

            // Map hotel to DTO object
            Hotel hoteldto = modelMapper.map(hotel, Hotel.class);

            // If there is no image uploaded, just save the hotel details
            if (multi == null || multi.isEmpty()) {
                hoteldto.setDistrict(district);
                hotelRepository.save(hoteldto);
                return true;
            }

            // Otherwise, proceed with updating hotel and uploading image
            HotelUpdateDTO updateDTO = hotelRepository.findHotelById(hotel.getId());
            if (updateDTO == null) {
                throw new RuntimeException("Hotel not found!");
            }

            // Handle old image removal if exists
            String uploadDir = Paths.get(System.getProperty("user.dir"), "Server", "src", "main", "resources", "static", "images", "hotel").toString();
            Path uploadPath = Paths.get(uploadDir);
            String oldFilename = updateDTO.getImageUrl();
            File oldFile = new File(uploadDir, oldFilename);
            if (oldFile.exists() && oldFile.isFile()) {
                oldFile.delete();
            }
            String newFileName = FileHelper.generateImageName(multi.getOriginalFilename());
            if (saveFileWithStream(multi, uploadDir, newFileName)) {
                Picture image= imageRepository.findByHotelId(hotel.getId());


                image.setHotelId(updateDTO.getId());
                image.setImageUrl(newFileName);
                image.setMain(true);
                imageRepository.save(image);
                // Lưu đường dẫn ảnh mới
            } else {
                throw new RuntimeException("Failed to save image file!");
            }
            // Set district and save updated hotel details
            hoteldto.setDistrict(district);
            hotelRepository.save(hoteldto);

            // Process the new uploaded image asynchronously


            return true;
        } catch (Exception e) {
            e.printStackTrace(); // You can replace this with better logging in production
            return false;
        }
    }


    private void updatePictureForHotel(int hotelId, String filename) {
        Picture image = imageRepository.findByHotelId(hotelId);
        if (image != null) {
            image.setMain(true);
            image.setImageUrl(filename);
        } else {
            image = new Picture();
            image.setMain(true);
            image.setHotelId(hotelId);
            image.setImageUrl(filename);
        }
        imageRepository.save(image);
    }

    @Async
    public boolean saveFileWithStream(MultipartFile file, String uploadDir, String fileName) {
        try {
            // Tạo thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Đường dẫn tệp đích
            Path filePath = uploadPath.resolve(fileName);

            // Thực hiện trì hoãn 5 giây trước khi lưu file


            // Lưu file vào đĩa
            file.transferTo(filePath.toFile());

            // Kiểm tra xem file đã được lưu thành công chưa
            if (Files.exists(filePath)) {
               return true;
            } else {
              return false;
            }

        } catch (IOException e) {
            // Nếu có lỗi, in thông báo lỗi
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ImageListDto> FindImages(int id) {
        try{
            return modelMapper.map(imageRepository.findListImage(id),new TypeToken<List<ImageListDto>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean DeleteHotel(int id) {
        try {
            Picture picture=imageRepository.findById(id).get();
            String uploadDir = System.getProperty("user.dir") + "/Server/src/main/resources/static/images/hotel";
            String oldFilename=picture.getImageUrl();
            File oldFile=new File(uploadDir,oldFilename);
            if(oldFile.exists() && oldFile.isFile()){
                oldFile.delete();
            }
            imageRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean UpdateMultipleImages(int id, List<MultipartFile> files) {
     try {
         String uploadDir = Paths.get(System.getProperty("user.dir"), "Server", "src", "main", "resources", "static", "images", "hotel").toString();
         Path uploadPath = Paths.get(uploadDir);
         if (!Files.exists(uploadPath)) {
             Files.createDirectories(uploadPath);
         }
        for (MultipartFile file : files) {
            String filenameImages=FileHelper.generateImageName(file.getOriginalFilename());
            Path filePathImages=uploadPath.resolve(filenameImages);
            file.transferTo(filePathImages.toFile());
            Picture pictureImage=new Picture();
            pictureImage.setHotelId(id);
            pictureImage.setImageUrl(filenameImages);
            pictureImage.setMain(false);
            imageRepository.save(pictureImage);
        }
        return true;
     }catch (Exception e){
         e.printStackTrace();
         return false;
     }
    }


}
