let selectedFiles = [];
document.addEventListener('DOMContentLoaded',()=>{



    const citySelect = new TomSelect("#city-select", {
        placeholder: "Select a city",
        onChange: function (cityId) {
            // Load the districts when city changes
            const token = document.getElementById('token') ? document.getElementById('token').textContent : null;
            if (!token) {
                console.error('No access token found.');
                return;
            }
            const url=`http://localhost:8686/District/GetDistrict/${cityId}`;
            $.ajax({
                url:url,
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                success: function (response) {
                    const districtSelect = document.getElementById("DistrictSelect");
                    districtSelect.innerHTML = "";

                    // Populate new options
                    response.forEach(district => {
                        const option = document.createElement("option");
                        option.value = district.id; // Set the district ID
                        option.textContent = district.name; // Set the district name
                        districtSelect.appendChild(option);
                    });

                    // Re-initialize TomSelect for DistrictSelect if needed
                    new TomSelect("#DistrictSelect", {
                        placeholder: "Select a district",
                    });
                },
                error: function (xhr, status, error) {
                    console.error('Failed to fetch districts:', error);
                }
            })
        }
    });






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

function addNewImage() {
    // Mở hộp thoại chọn file hình ảnh
    const fileInput = document.createElement('input');
    fileInput.type = 'file';
    fileInput.id="MultiImage";
    fileInput.accept = 'image/*';
    fileInput.onchange = (event) => {
        const file = event.target.files[0];
        if (file) {
            selectedFiles.push(file);
            const imageContainer = document.getElementById('image-container');
            const reader = new FileReader();
            reader.onload = function (e) {
                // Tạo HTML cho hình ảnh mới


                // Thêm hình ảnh mới
                const newImageItem = document.createElement('div');
                newImageItem.classList.add('image-item');
                newImageItem.innerHTML = `
          <img src="${e.target.result}" alt="New Image" class="product-image">
          <button type="button" class="delete-btn" onclick="deleteImage(this)">
            <i class="fa fa-trash-alt" style="color: black"></i>
          </button>
        `;
                imageContainer.appendChild(newImageItem);

                // Di chuyển nút "Add Image" tới vị trí cuối
                const addImageContainer = document.querySelector('.add-image-container');
                imageContainer.appendChild(addImageContainer);
            };
            reader.readAsDataURL(file); // Đọc file và chuyển sang URL base64
        }
    };
    fileInput.click();
}
function deleteImage(button) {
    // Tìm phần tử cha (image-item) chứa nút xóa
    const imageItem = button.closest('.image-item');

    if (imageItem) {
        // Hiển thị xác nhận trước khi xóa (tùy chọn)
        const confirmDelete = confirm('Bạn có chắc muốn xóa hình ảnh này?');
        if (confirmDelete) {
            // Xóa phần tử hình ảnh khỏi DOM
            imageItem.remove();

            // Tìm khung "Add Image" kế tiếp sau hình ảnh vừa xóa
            const nextAddImageContainer = imageItem.nextElementSibling;
            if (nextAddImageContainer && nextAddImageContainer.classList.contains('add-image-container')) {
                // Xóa khung "Add Image" nếu nó tồn tại
                nextAddImageContainer.remove();
            }
        }
    }
}


