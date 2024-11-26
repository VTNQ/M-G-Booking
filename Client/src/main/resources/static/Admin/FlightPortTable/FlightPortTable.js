document.addEventListener("DOMContentLoaded", function () {
    flatpickr("#datetimepicker", {
        enableTime: true, dateFormat: "Y-m-d\\TH:i", time_24hr: true,
        altFormat: "d/m/Y H:i",
        altInput: true,
        defaultDate: document.getElementById("datetimepicker").value// Định dạng 24 giờ
    });
});
document.addEventListener("DOMContentLoaded", function () {
    flatpickr("#Departure_Time", {
        enableTime: true,
        dateFormat: "Y-m-d\\TH:i", // Format for the actual value
        altInput: true,           // Enables the alternative input display
        altFormat: "d/m/Y H:i",   // User-friendly display format
        time_24hr: true,          // 24-hour format
        defaultDate: document.getElementById("Departure_Time").value // Set the initial value
    });
});

console.log(document.getElementById("Departure_Time").value)
let detailIndex = 0; // Tracks the number of detail flights
const detailFlights = []; // Array to store detail flights locally
const detailFlightsContainer = document.getElementById("detailFlights");
const addDetailFlightButton = document.getElementById("addDetailFlight");
addDetailFlightButton.addEventListener("click", () => {
    const detailFlightTemplate = `
        <div class="detail-flight mb-3" id="detailFlight-${detailIndex}">
            <div class="row">
                <div class="col-md-4">
                    <label for="detailFlights[${detailIndex}].type" class="form-label">Type</label>
                    <input type="text" class="form-control" name="detailFlights[${detailIndex}].type" placeholder="Enter Type" required>
                </div>
                <div class="col-md-4">
                    <label for="detailFlights[${detailIndex}].price" class="form-label">Price</label>
                    <input type="number" step="0.01" class="form-control" name="detailFlights[${detailIndex}].price" placeholder="Enter Price" required>
                </div>
                <div class="col-md-4">
                    <label for="detailFlights[${detailIndex}].quantity" class="form-label">Quantity</label>
                    <input type="number" class="form-control" name="detailFlights[${detailIndex}].quantity" placeholder="Enter Quantity" required>
                </div>
            </div>
            <div class="text-end mt-2">
                <button type="button" class="btn btn-danger btn-sm removeDetailFlight" data-index="${detailIndex}">Remove</button>
            </div>
        </div>
    `;
    detailFlightsContainer.insertAdjacentHTML("beforeend", detailFlightTemplate);

    // Add a blank entry to detailFlights array
    detailFlights.push({index: detailIndex, type: "", price: 0, quantity: 0});

    // Listen for changes in inputs
    const detailFlightElement = document.getElementById(`detailFlight-${detailIndex}`);
    detailFlightElement.querySelectorAll("input").forEach(input => {
        input.addEventListener("input", () => {
            const field = input.name.split(".")[1]; // Extract the field name (type, price, quantity)
            const flightDetail = detailFlights.find(df => df.index === detailIndex);
            if (flightDetail) {
                flightDetail[field] = input.value;
            }
        });
    });

    // Add event listener to the Remove button
    document.querySelector(`#detailFlight-${detailIndex} .removeDetailFlight`).addEventListener("click", (e) => {
        const detailFlightId = e.target.getAttribute("data-index");
        document.getElementById(`detailFlight-${detailFlightId}`).remove();

        // Remove from detailFlights array
        const indexToRemove = detailFlights.findIndex(df => df.index == detailFlightId);
        if (indexToRemove > -1) {
            detailFlights.splice(indexToRemove, 1);
        }
    });

    detailIndex++;
});
let pageSize = 10;
let CurrentPage = 0;
let TotalPages = 10;

function updatePaginationControls(totalCount) {
    totalPages = Math.ceil(totalCount / pageSize);
    $('#prevPage').prop('disabled', CurrentPage === 1);
    $('#nextPage').prop('disabled', CurrentPage === totalPages);
    let pageNumbersHtml = '';
    for (let i = 0; i < totalPages; i++) {
        pageNumbersHtml += `<li class="page-item ${i === CurrentPage ? 'active' : ''}">
            <span class="page-link" onclick="changePage(${i})">${i+1}</span>
        </li>`;
    }
    $('#pageNumbers').html(pageNumbersHtml);
}

$('#prevPageItem').click(function () {
    if (CurrentPage >= 0) {
        CurrentPage--;

        fetchFlight(CurrentPage, pageSize);
    }

    // Disable the button if CurrentPage is 0 or less
    if (CurrentPage <= 0) {
        $('#prevPageItem').prop('disabled', true); // Disable the button
    } else {
        $('#prevPageItem').prop('disabled', false); // Enable the button
    }
});
$('#nextPage').click(function () {
    if (CurrentPage < TotalPages) {
        CurrentPage++;

        fetchFlight(CurrentPage,pageSize);
    }
})

function changePage(page) {

    if (page >= 0 && page <= totalPages) {
        CurrentPage = page;
        fetchFlight(CurrentPage , pageSize);
    }
}
document.addEventListener('DOMContentLoaded', function () {
    const editButtons = document.querySelectorAll('.edit-btn');

    editButtons.forEach(button => {
        button.addEventListener('click', function () {
            const id = this.getAttribute('data-id');
            console.log(id);

            // Get input and label elements
            const typeInput = document.getElementById(`type-${id}`);
            const priceInput = document.getElementById(`price-${id}`);
            const quantityInput = document.getElementById(`quantity-${id}`);
            const typeLabel = document.getElementById(`type-label-${id}`);
            const priceLabel = document.getElementById(`price-label-${id}`);
            const quantityLabel = document.getElementById(`quantity-label-${id}`);

            // If the button is in "Edit" mode, show inputs and hide labels
            if (this.classList.contains('btn-info')) {
                typeInput.style.display = 'block';
                priceInput.style.display = 'block';
                quantityInput.style.display = 'block';

                typeLabel.style.display = 'none';
                priceLabel.style.display = 'none';
                quantityLabel.style.display = 'none';

                // Change button to "Save"
                this.innerHTML = '<i style="color: white" class="fa fa-save"></i> ';
                this.classList.remove('btn-info');

            } else { // If the button is in "Save" mode, save the values and switch back to "Edit"
                const updatedData = {
                    id: id,
                    type: typeInput.value,
                    price: priceInput.value,
                    quantity: quantityInput.value
                };

                // Update labels with the new values
                typeLabel.textContent = updatedData.type;
                priceLabel.textContent = updatedData.price;
                quantityLabel.textContent = updatedData.quantity;

                // Hide inputs and show labels
                typeInput.style.display = 'none';
                priceInput.style.display = 'none';
                quantityInput.style.display = 'none';

                typeLabel.style.display = 'inline-block';
                priceLabel.style.display = 'inline-block';
                quantityLabel.style.display = 'inline-block';

                // Reset button to "Edit"
                this.innerHTML = '<i style="color: white" class="fa fa-pencil"></i> ';

                this.classList.add('btn-info');
            }
        });
    });
});

function fetchFlight(page, size) {
    const token = document.getElementById('token') ? document.getElementById('token').textContent : null;
    const id = document.getElementById('IdCountry') ? document.getElementById('IdCountry').textContent : null;
    if (!token) {
        console.error('No access token found.');
        return;
    }
    const url = `http://localhost:8686/Flight/FindAll/${id}?page=${page}&size=${size}`;
    $.ajax({
        url: url, method: 'GET', headers: {
            'Authorization': `Bearer ${token}`
        }, success: function (response) {
            const flight = response.content;
            const totalCount = response.totalElements;
            updatePaginationControls(totalCount);
            $('#FlightTableBody').empty();
            flight.forEach((flights, index) => {
                $('#FlightTableBody').append(`<tr>
                <td>${index + 1 + page * size}</td>
                <td>${flights.nameAirline}</td>
                <td>${flights.departure_airport}</td>
                <td>${flights.arrival_airport}</td>
                 <td>
                    <a href="/Admin/Flight/Edit/${flights.id}"  style="background-color: #4299e1;border-color: #4299e1" class="btn btn-info"><i style="color: white" class="fa fa-pencil"></i></a>
             
                    </td>
            </tr>`)
            })
        },
        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
        }
    })

}
fetchFlight(CurrentPage,pageSize);