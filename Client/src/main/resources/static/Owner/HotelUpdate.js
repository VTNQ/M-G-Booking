
document.addEventListener('DOMContentLoaded',()=>{
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
document.getElementById('fileInput1').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const previewImage = document.getElementById('previewImage');
            previewImage.src = e.target.result;
            previewImage.style.display = 'block'; // Hiển thị ảnh
        };
        reader.readAsDataURL(file); // Đọc tệp và chuyển thành URL
    }
});