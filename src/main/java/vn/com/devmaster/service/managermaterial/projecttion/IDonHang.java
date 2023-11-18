package vn.com.devmaster.service.managermaterial.projecttion;

public interface IDonHang {
    Integer getId();
    String getIdOrder();
    double getTotal();
    double getTienShip();
    String getNameProduct();
    Integer getTrangThai();
    int getQty();
    String getImage();
    String getOrderDate();
    Integer getIdNguoiNhan();
    String getNameNguoiNhan();
    String getAddressNguoiNhan();
    String getPhoneNguoiNhan();
    String getNamePayment();
    String getNameTransport();
}
