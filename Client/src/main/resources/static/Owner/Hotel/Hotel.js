ClassicEditor
    .create(document.querySelector('#editor'), {
        toolbar: ['bold', 'italic', 'link', 'undo', 'redo']
    })
    .catch(error => {
        console.error(error);
    });
ClassicEditor
    .create(document.querySelector('#description'), {
        toolbar: ['bold', 'italic', 'link', 'undo', 'redo']
    })
    .catch(error => {
        console.error(error);
    });
document.addEventListener('DOMContentLoaded',()=>{
    const citySelect = new TomSelect("#city-select", {
        placeholder: "Select a city",

    });
    const districtSelect = new TomSelect('#DistrictSelect', {
        placeholder: "Select a District",

    });

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