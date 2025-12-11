package com.aot.be.Enum;

public enum AirlineStatus {

    ACTIVE(1,"Active","Hãng đang hoạt động bình thường"),
    INACTIVE(2,"InActive","Hãng đã ngừng hoạt động tạm thời hoặc lâu dài."),
    SUSPENDED(3,"SuSpended","Hãng bị đình chỉ hoạt động bởi quốc gia hoặc tổ chức hàng không."),
    MAINTENANCE(4,"Maintenance","Hãng đang trong giai đoạn bảo trì/điều chỉnh hệ thống"),
    DELETED(5,"Deleted","Không còn sử dụng trong hệ thống nhưng vẫn lưu.");

    private final int code;
    private final String description;
    private final String vnDescription;
    AirlineStatus(int code,String description, String vnDescription) {
        this.code = code;
        this.description = description;
        this.vnDescription = vnDescription;
    }
    public String getDescription() {
        return description;
    }
    public int getCode() {
        return code;
    }
    public String getVnDescription() {
        return vnDescription;
    }
    public static AirlineStatus fromCode(int code) {
        for (AirlineStatus status : AirlineStatus.values()) {
            if(status.getCode() == code)
                return status;
        }
        throw new IllegalArgumentException("Invalid code " + code);
    }
}
