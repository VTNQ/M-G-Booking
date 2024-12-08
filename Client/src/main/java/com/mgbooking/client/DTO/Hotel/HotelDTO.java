    package com.mgbooking.client.DTO.Hotel;

    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;

    public class HotelDTO {
        private Integer id;
        private String name;
        private String address;
        private int city_id;
        private String decription;

        public List<MultipartFile> getImages() {
            return images;
        }

        public void setImages(List<MultipartFile> images) {
            this.images = images;
        }

        private int district_id;
        private List<MultipartFile> images;

        private int ownerId;
           private MultipartFile image;

        public MultipartFile getImage() {
            return image;
        }

        public void setImage(MultipartFile image) {
            this.image = image;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getDecription() {
            return decription;
        }

        public void setDecription(String decription) {
            this.decription = decription;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }
    }
