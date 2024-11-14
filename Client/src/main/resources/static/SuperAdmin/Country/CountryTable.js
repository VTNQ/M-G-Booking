const pageSize = 10;
let CurrentPage = 1;
let totalPages = 10;

function updatePaginationControls(totalCount) {
    totalPages = Math.ceil(totalCount / pageSize);
    $('#prevPage').prop('disabled', CurrentPage);
    $('#nextPage').prop('disabled', CurrentPage === totalPages);
    let pageNumbersHtml = '';
    for (let i = 1; i <= totalPages; i++) {
        pageNumbersHtml += `<li class="page-item ${i === CurrentPage ? 'active' : ''}">
<span class="page-link" onclick="changePage(${i})">${i}</span>
</li>`
    }
    $('#pageNumbers').html(pageNumbersHtml);
}
function changePage(page){
    if (page >= 1 && page <= totalPages) {
        CurrentPage = page;
        fetchCountries(CurrentPage, pageSize);  // Fetch countries for the selected page
    }
}
function fetchCountries(page, size) {
    const token = document.getElementById('token') ? document.getElementById('token').textContent : null;

    if (!token) {
        console.error('No access token found.');
        return; // Exit early if no token is available
    }
    console.log(token);
    const url = `http://localhost:8686/Country/GetAllCountries?page=${page}&size=${size}`;
    console.log(`Fetching countries from: ${url}`);

    $.ajax({
        url: url,
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        },
        success: function (response) {

            const countries = response.content;
            const totalCount = response.totalPages;

            updatePaginationControls(totalCount)
            $('#countryTableBody').empty();
            countries.forEach((country, index) => {
                $('#countryTableBody').append(`<tr>
                    <td>${index + 1}</td>
                    <td>${country.name}</td>
                    <td>
                    <a href="/SuperAdmin/EditCountry/${country.id}" style="background-color: #4299e1;border-color: #4299e1" class="btn btn-info"><i style="color: white" class="fa fa-pencil"></i></a>
                    <a href="/SuperAdmin/DeleteCountry/${country.id}" class="btn btn-danger">
                    <i style="color: white" class="fa fa-trash"></i>
</a>
                    </td>
</tr>`);
            });
            // You can further process and display the countries as needed

        },
        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            // Optionally, handle specific error responses or show a message to the user
        }
    });
}
$('#prevPage').click(function (){
    if(CurrentPage>1){
        CurrentPage--;
        fetchCountries(CurrentPage,pageSize);
    }
})
$('#nextPage').click(function (){
    if(CurrentPage<totalPages){
        CurrentPage++;
        fetchCountries(CurrentPage,pageSize);
    }
})
fetchCountries(CurrentPage, pageSize);
