
document.addEventListener('DOMContentLoaded',()=>{
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function() {
            const imageId = this.getAttribute('data-id');
            const hotelId = this.getAttribute('data-hotel-id');

            deleteImage(imageId, hotelId);
        });
    });

    function deleteImage(imageId, hotelId) {
    console.log(imageId)
        // Make the API request to delete the image
        fetch(`http://localhost:8686/Hotel/DeletePictureImage/${imageId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token  // Use the appropriate token for authentication
            },

        })
            .then(response => response.json())
            .then(data => {
                if (data.status === 200) {
                    // Show success message
                    Swal.fire(
                        'Deleted!',
                        'The image has been deleted.',
                        'success'
                    ).then(() => {
                        // Redirect to the desired page after a successful deletion
                        window.location.href = 'http://localhost:8386/Owner/Hotel/6';
                    });
                } else {
                    // Show error message
                    Swal.fire(
                        'Error!',
                        'There was an issue deleting the image.',
                        'error'
                    );
                }
            })
            .catch(error => {
                console.error('Error deleting image:', error);
                Swal.fire(
                    'Error!',
                    'An error occurred while deleting the image.',
                    'error'
                );
            });
    }

    const citySelect = new TomSelect("#city-select", {
        placeholder: "Select a city",
        onChange: function (cityId) {
            // Load the districts when city changes
            loadDistricts(cityId);
        }
    });
    const idDistrict=document.getElementById("DistrictSelect");

    console.log(idDistrict)
    const districtSelect = document.getElementById("DistrictSelect");

    const token = document.getElementById('token') ? document.getElementById('token').textContent : null;
    if (!token) {
        console.error('No access token found.');
        return;
    }
    const loadDistricts=(cityId)=>{
        const url=`http://localhost:8686/District/GetDistrict/${cityId}`;
        $.ajax({
            url:url,
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            success: function (response) {
                // Extract district names from the response to use in the autocomplete
                const districtNames = response.map(district => district.name);
                const districtsreponse = response.map(district => ({
                    label: district.name,  // The district name is the label
                    value: district.id     // The district ID is the value
                }));
                let district=document.getElementById('district-search');
                let districtid=document.getElementById('districtid');
                response.forEach(districts => {
                    console.log(districts.id)
                  if(districtid.value==districts.id){

                      district.value=districts.name;

                  }
                });
                console.log(district)
                console.log(district)
                // Apply jQuery UI Autocomplete to the input field
                $('#district-search').autocomplete({
                    minLength: 0,  // Allow suggestions to show with zero characters typed
                    source: function(request, response) {
                        // If the user hasn't typed anything, show all districts

                        if (request.term.length === 0) {
                            response(districtNames);  // Show all districts when no input
                        } else {
                            // Otherwise, filter based on user input
                            const filteredDistricts = districtNames.filter(function(district) {
                                return district.toLowerCase().includes(request.term.toLowerCase());
                            });
                            response(filteredDistricts);  // Show the filtered list
                        }
                    },
                    select: function (event, ui) {
                        // Optionally, you can handle the selection event if needed
                        console.log("Selected district:", ui.item.value);
                        districtsreponse.forEach(dis=>{

                            if(dis.label==ui.item.value){
                                districtid.value=dis.value;
                            }
                        })
                    }
                });

                console.log('Districts loaded successfully');
            },
            error: function (xhr, status, error) {
                console.error('Failed to fetch districts:', error);
            }
        })
    }
    const initialCityId = citySelect.getValue(); // Lấy giá trị mặc định của citySelect
    if (initialCityId) {
        loadDistricts(initialCityId);
    }
    citySelect.on('change',(cityId)=>{
        const token = document.getElementById('token') ? document.getElementById('token').textContent : null;
        if (!token) {
            console.error('No access token found.');
            return;
        }
        const url=`http://localhost:8686/District/GetDistrict/${cityId}`;
        $.ajax({
            url:url,
            method:'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            },
            success: function (response) {
                districtSelect.clearOptions();
                response.forEach(district => {
                    districtSelect.addOption({ value: district.id, text: district.name });
                });
                districtSelect.refreshOptions();
            },
            error: function (xhr, status, error) {
                console.error('Failed to fetch districts:', error);
            }
        })
    })
})
const fileInput = document.getElementById('fileInput');
const uploadArea = document.getElementById('uploadArea');
const imagePreviewContainer = document.getElementById('imagePreviewContainer');
uploadArea.addEventListener('click', () => {
    fileInput.click(); // Mở hộp thoại chọn file
});
fileInput.addEventListener('change', (event) => {
    const files = event.target.files;
    imagePreviewContainer.innerHTML = ''; // Xóa preview cũ
    console.log(files)
    if (files.length > 0) {
        const file = files[0]; // Chỉ lấy file đầu tiên

        if (file.type.startsWith('image/')) {
            const reader = new FileReader();

            // Đọc file và tạo bản xem trước
            reader.onload = (e) => {
                const imageDiv = document.createElement('div');
                imageDiv.className = 'image-preview';
                imageDiv.style.backgroundImage = `url(${e.target.result})`;

                const img = document.createElement('img');
                img.src = e.target.result;
                imageDiv.appendChild(img);

                // Thêm vào container (chỉ 1 hình ảnh)
                imagePreviewContainer.appendChild(imageDiv);
            };

            reader.readAsDataURL(file);
        } else {
            console.error('File không phải là hình ảnh:', file.name);
        }
    }
});
document.getElementById("fileInputArea").addEventListener("change",function (event){
    const imagePreviewContainer=document.getElementById("imagePreviewContainerArea");
    imagePreviewContainer.innerHTML="";
    const files=event.target.files;
    if (files.length === 0) {
        imagePreviewContainer.innerHTML = "<p>No files selected.</p>";
        return;
    }
    Array.from(files).forEach((file)=>{
        if(file.type.startsWith("image/")){
            const reader = new FileReader();
            reader.onload = (e) => {
                const img = document.createElement("img");
                img.src = e.target.result;
                img.alt = file.name;
                img.style.width = "100px";
                img.style.margin = "5px";
                img.className = "uploaded-image-preview";
                imagePreviewContainer.appendChild(img);
            };
            reader.readAsDataURL(file);
        }else{
            const error = document.createElement("p");
            error.textContent = `File "${file.name}" is not an image.`;
            error.style.color = "red";
            imagePreviewContainer.appendChild(error);
        }
    })
})


