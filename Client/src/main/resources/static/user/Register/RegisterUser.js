$(document).ready(function() {
    // Lắng nghe sự kiện thay đổi trên dropdown quốc gia
    $('#countrySelect').change(function() {
        var countryId = $(this).val(); // Lấy ID quốc gia được chọn
        console.log(countryId)
        // Kiểm tra xem có quốc gia nào được chọn hay không
        if (countryId) {
            // Gửi yêu cầu GET để lấy danh sách thành phố từ API
            $.ajax({
                url: 'http://localhost:8686/City/' + countryId, // Gọi API để lấy thành phố của quốc gia
                method: 'GET',
                success: function(data) {
                    // Làm rỗng dropdown thành phố trước khi cập nhật
                    $('#citySelect').empty();

                    // Thêm option đầu tiên "Chọn thành phố"
                    $('#citySelect').append('<option value="" disabled selected>Select City</option>');

                    // Lặp qua các thành phố và thêm chúng vào dropdown
                    $.each(data, function(index, city) {
                        $('#citySelect').append('<option value="' + city.id + '">' + city.name + '</option>');
                    });
                },
                error: function() {
                    alert('Error loading cities');
                }
            });
        } else {
            // Nếu không có quốc gia nào được chọn, làm rỗng dropdown thành phố
            $('#citySelect').empty();
            $('#citySelect').append('<option value="" disabled selected>Select City</option>');
        }
    });
});
