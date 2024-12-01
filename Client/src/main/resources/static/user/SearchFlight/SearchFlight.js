const flightDetails = document.querySelector('.flight-details');
const detailsDivs = flightDetails.querySelectorAll('div');
const dropdownButton = document.querySelector('#dropdownToggle');

// Ẩn tất cả các div con ban đầu
detailsDivs.forEach(div => {
    div.classList.add('hidden');
});
function convertTimeToDecimal(time) {
    const [hours, minutes] = time.split(':').map(Number);
    return hours + (minutes / 60);
}
// Thay đổi icon thành mũi tên xuống
dropdownButton.innerHTML = '<i class="fa-solid fa-arrow-down"></i>';

// Thêm sự kiện click cho nút dropdown
dropdownButton.addEventListener('click', () => {
    // Chuyển đổi trạng thái hiển thị và icon
    if (detailsDivs[0].classList.contains('hidden')) {
        detailsDivs.forEach((div, index) => {
            setTimeout(() => {
                div.classList.remove('hidden');
                div.classList.add('show');
            }, index * 100);
        });
        dropdownButton.innerHTML = '<i class="fa-solid fa-arrow-up"></i>';
    } else {
        detailsDivs.forEach((div, index) => {
            setTimeout(() => {
                div.classList.remove('show');
                div.classList.add('hidden');
            }, index * 100);
        });
        dropdownButton.innerHTML = '<i class="fa-solid fa-arrow-down"></i>';
    }
});


let selectedAirlines=[];
function updateFlightVisibility(){
    document.querySelectorAll('.flight-item').forEach(function(flightItem) {
        const airlineId = flightItem.getAttribute('data-airline-id');


        if (selectedAirlines.length === 0 || selectedAirlines.includes(airlineId)) {
            flightItem.style.display = 'block'; // Show flight
        } else {
            flightItem.style.display = 'none'; // Hide flight
        }
    });
}
document.getElementById('selectAllToggle').addEventListener('change',function (){
    const isChecked=this.checked;
    if(isChecked){
        selectedAirlines=[];
        document.querySelectorAll('.flight-select').forEach(function(checkbox) {
            const airlineId = checkbox.closest('.custom-control').querySelector('input').getAttribute('id').split('-')[1];
            selectedAirlines.push(airlineId);
            checkbox.checked = true;
        });
    }else{
        selectedAirlines = [];
        document.querySelectorAll('.flight-select').forEach(function(checkbox) {
            checkbox.checked = false;
        });
    }
    updateFlightVisibility();
})
document.querySelectorAll('.airline-checkbox').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        const airlineId = this.id.split('-')[1];
        console.log(airlineId)
        if (this.checked) {
            selectedAirlines.push(airlineId); // Add airline ID to selected list
        } else {
            selectedAirlines = selectedAirlines.filter(function(id) {
                return id !== airlineId;
            });
        }

      updateFlightVisibility();

    });
});
document.addEventListener("DOMContentLoaded", function() {
    var priceRange = document.getElementById('price-range');
    var priceRangeText = document.getElementById('price-range-text');

    // Initialize the noUiSlider
    noUiSlider.create(priceRange, {
        start: [200, 120000], // Set the default values for the range
        connect: true, // Connect the handles
        range: {
            'min': 200,    // Minimum value of the slider
            'max': 120000   // Maximum value of the slider
        },
        step: 10,  // Step size for the slider
        tooltips: true, // Show the current value when the user drags the handle
        format: {
            to: function (value) {
                return '£' + Math.round(value); // Format values as currency
            },
            from: function (value) {
                return value.replace('£', ''); // Convert from the formatted string
            }
        }
    });

    // Event listener to update the text display when the slider changes
    priceRange.noUiSlider.on('update', function (values, handle) {
        const minPrice = parseFloat(values[0].replace('£', '').replace(',', ''));
        const maxPrice = parseFloat(values[1].replace('£', '').replace(',', ''));

        priceRangeText.textContent =  values[0]+' - '+values[1];
        document.querySelectorAll('.flight-item').forEach(function (flightItem) {
            const flightPrice = parseFloat(flightItem.getAttribute('data-price'));

            if (flightPrice >= minPrice && flightPrice <= maxPrice) {
                flightItem.style.display = 'block';  // Show flight
            } else {
                flightItem.style.display = 'none';   // Hide flight
            }
        });
    });
});
document.addEventListener("DOMContentLoaded",function (){
    var ScheduleRange=document.getElementById("Schedule-range");
    var ScheduleStart=document.getElementById("ScheduleStart");
    var ScheduleEnd=document.getElementById("Schedule-range-end");
    var ScheduleEndText=document.getElementById("ScheduleEnd");
    var Timerange=document.getElementById("Time-range");
    var TimerangeText=document.getElementById("TimeRange");
    noUiSlider.create(Timerange, {
        start: [79],
        connect: [true,false],
        range: {
            'min': 0,
            'max': 79
        },
        step: 1,  // Step size for the slider
        tooltips: true,
        format: {
            to: function (value) {
                return Math.round(value);

            },
            from: function (value) {


                return parseInt(value, 10); // Convert from the formatted string
            }
        }
    });
    noUiSlider.create(ScheduleEnd, {
        start: [0, 24],
        connect: true,
        range: {
            'min': 0,
            'max': 24
        },
        step: 0.5,  // Step size for the slider
        tooltips: true, // Show the current value when the user drags the handle
        format: {
            to: function (value) {
                var hours = Math.floor(value);
                var minutes = Math.round((value - hours) * 60);


                if (minutes === 60) {
                    hours += 1;
                    minutes = 0;
                }
                return hours.toString().padStart(2, '0') + ':' + minutes.toString().padStart(2, '0');

            },
            from: function (value) {


                return value.replace('£', ''); // Convert from the formatted string
            }
        }
    });
    noUiSlider.create(ScheduleRange, {
        start: [0, 24],
        connect: true,
        range: {
            'min': 0,
            'max': 24
        },
        step: 0.5,  // Step size for the slider
        tooltips: true, // Show the current value when the user drags the handle
        format: {
            to: function (value) {
                var hours = Math.floor(value);
                var minutes = Math.round((value - hours) * 60);


                if (minutes === 60) {
                    hours += 1;
                    minutes = 0;
                }
                return hours.toString().padStart(2, '0') + ':' + minutes.toString().padStart(2, '0');

            },
            from: function (value) {


                return value.replace('£', ''); // Convert from the formatted string
            }
        }
    });
    ScheduleRange.noUiSlider.on('update', function (values, handle) {
        const start = parseFloat(values[0]);
        const end = parseFloat(values[1]);
        ScheduleStart.textContent="Đi "+values[0]+" - "+values[1];
        document.querySelectorAll('.flight-item').forEach(function (flightItem) {
            const flightTime = flightItem.getAttribute('data-TimeDepart');
            const flightHour = convertTimeToDecimal(flightTime);
            if (flightHour >= start && flightHour <= end) {
                flightItem.style.display = 'block';
            } else {
                flightItem.style.display = 'none'; // Ẩn
            }

        });
    });
    Timerange.noUiSlider.on('update', function (values, handle) {
       TimerangeText.textContent='Dưới '+values[0]+' Tiếng';
        document.querySelectorAll('.flight-item').forEach(function (flightItem) {
            const flightTime =parseInt(flightItem.getAttribute('data-duration'));
            if(flightTime<=values[0]){
                flightItem.style.display='block'
            }else{
                flightItem.style.display='none'
            }


        });
    });
    ScheduleEnd.noUiSlider.on('update', function (values, handle) {
        const start = parseFloat(values[0]);
        const end = parseFloat(values[1]);
        ScheduleEndText.textContent="Đến "+values[0]+" - "+values[1];
        document.querySelectorAll('.flight-item').forEach(function (flightItem) {
            const flightTime = flightItem.getAttribute('data-timeArrival');

            const flightHour = convertTimeToDecimal(flightTime);
            if (flightHour >= start && flightHour <= end) {
                flightItem.style.display = 'block';
            } else {
                flightItem.style.display = 'none'; // Ẩn
            }

        });
    });
})
document.addEventListener("DOMContentLoaded", function() {
    // Get all the tab buttons
    const tabButtons = document.querySelectorAll('.nav-tabs .nav-link');
    const tabPanes = document.querySelector(`#TabPanel0`);
    const indexToActivate = 0;
    tabButtons[0].classList.add('active');

    tabPanes.classList.add('show','active')
    function activateTab(index) {
        // Remove 'active' class from all tabs and tab panes
        const tabPanes = document.querySelector(`#TabPanel${index}`);
        tabButtons.forEach(button => button.classList.remove('active'));
        tabPanes.classList.remove('show', 'active');

        // Add 'active' class to the clicked tab and corresponding tab pane
        tabButtons[index].classList.add('active');
        tabPanes.classList.add('show', 'active');
    }
    tabButtons.forEach((button, index) => {
        button.addEventListener('click', function() {
            const allTabPanes = document.querySelectorAll('.TabShow');
            allTabPanes.forEach(pane => pane.classList.remove('show', 'active'));
            const tabPane = document.querySelector(`#TabPanel${index}`);

            activateTab(index);
            tabPane.classList.add('show','active');
        });
    });
});

function initFlatpickr() {
    flatpickr("#datePickerInput", {
        mode: "range", // Enable date range selection
        dateFormat: "Y-m-d", // Format to match LocalDate (yyyy-MM-dd)
        defaultDate: [new Date()], // Set default dates in LocalDate format
        onReady: function (selectedDates, dateStr, instance) {
            if (selectedDates.length) {
                // Display only the first date in the range
                const firstDate = instance.formatDate(selectedDates[0], "Y-m-d");
                instance.element.value = firstDate; // Set the input value to the first date
            }
        },
        onChange: function (selectedDates, dateStr, instance) {
            if (selectedDates.length) {
                // Update the input value to show only the first selected date
                const firstDate = instance.formatDate(selectedDates[0], "Y-m-d");
                instance.element.value = firstDate;
            }
        }
    });
}


document.getElementById("datePickerInput").addEventListener("focus", function () {
    // Initialize flatpickr only if not already initialized
    if (!this.classList.contains("flatpickr-input-active")) {
        initFlatpickr();
        this.classList.add("flatpickr-input-active");
    }
});
document.querySelectorAll('.dropdown-item').forEach(item => {
    item.addEventListener('click', function(e) {
        // Get the selected item text
        var selectedItem = this.textContent;

        // Update the button text to the selected item
        var button = this.closest('.dropdown').querySelector('.dropdown-toggle');
        button.querySelector('.filter-option-inner-inner').textContent = selectedItem;

        // Remove 'selected' class from all items and add it to the clicked one
        var dropdownItems = this.closest('.dropdown-menu').querySelectorAll('.dropdown-item');
        dropdownItems.forEach(i => i.classList.remove('selected'));
        this.classList.add('selected');
    });
});
