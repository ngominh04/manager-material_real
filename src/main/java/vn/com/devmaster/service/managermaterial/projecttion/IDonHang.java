package vn.com.devmaster.service.managermaterial.projecttion;

public interface IDonHang {
    Integer getId();
    String getIdOrder();
    double getTotal();
    String getNameProduct();
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
