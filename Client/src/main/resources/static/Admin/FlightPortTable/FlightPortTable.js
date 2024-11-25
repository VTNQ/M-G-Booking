 document.addEventListener("DOMContentLoaded", function () {
    flatpickr("#datetimepicker", {
        enableTime: true,
        dateFormat: "Y-m-d\\TH:i",
        time_24hr: true // Định dạng 24 giờ
    });
});
document.addEventListener("DOMContentLoaded", function () {
    flatpickr("#Departure_Time", {
        enableTime: true,
        dateFormat: "Y-m-d\\TH:i",
        time_24hr: true // Định dạng 24 giờ
    });
});
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
     detailFlights.push({ index: detailIndex, type: "", price: 0, quantity: 0 });

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
