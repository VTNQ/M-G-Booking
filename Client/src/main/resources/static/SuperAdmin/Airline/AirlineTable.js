const pageSize = 10;
let CurrentPage = 0;
let totalPages = 10;
function updatePaginationControls(totalCount){
    totalPages=Math.ceil(totalCount/pageSize);
    $('#prevPage').prop('disabled',CurrentPage);
    $('#nextPage').prop('disabled', CurrentPage === totalPages);
    let pageNumbersHtml = '';
    for (let i = 0; i < totalPages; i++) {
        pageNumbersHtml += `<li class="page-item ${i === CurrentPage ? 'active' : ''}">
<span class="page-link" onclick="changePage(${i})">${i+1}</span>
</li>`
    }
    $('#pageNumbers').html(pageNumbersHtml);
}
function changePage(page){
    if (page >= 0 && page <= totalPages) {
        CurrentPage = page;
        fetchAirline(CurrentPage, pageSize);  // Fetch countries for the selected page
    }
}
function fetchAirline(page,size){
    const token = document.getElementById('token') ? document.getElementById('token').textContent : null;
    if (!token) {
        console.error('No access token found.');
        return; // Exit early if no token is available
    }
    const url=`http://localhost:8686/Flight/GetFlight?page=${page}&size=${size}`;
    $.ajax({
        url: url,
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        },
        success: function (response) {
            const Airline=response.content;
            const totalCount = response.totalPages;
            updatePaginationControls(totalCount);
            $('#AirlineTableBody').empty();
            Airline.forEach((airline,index)=>{
                $('#AirlineTableBody').append(`
                <tr>
                <td>${index+1}</td>
                <td>${airline.name}</td>
                <td>${airline.country}</td>
                <td><img src="${airline.image}" style="width: 100px;height: 100px"/></td>
                <td>
                <a href="/SuperAdmin/UpdateAirline/${airline.id}" style="background-color: #4299e1;border-color: #4299e1" class="btn btn-info"><i style="color: white" class="fa fa-pencil"></i></a>
</td>
</tr>
                
                `)
            })
        }
    })
}
$('#prevPage').click(function (){
    if(CurrentPage>1){
        CurrentPage--;
        fetchAirline(CurrentPage,pageSize);
    }
})
$('#nextPage').click(function (){
    if(CurrentPage<totalPages){
        CurrentPage++;
        fetchAirline(CurrentPage,pageSize);
    }
});
document.getElementById('fileInput').addEventListener('change', function(event) {
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
fetchAirline(CurrentPage,pageSize);