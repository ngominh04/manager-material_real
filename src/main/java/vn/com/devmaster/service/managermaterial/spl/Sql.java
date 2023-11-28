package vn.com.devmaster.service.managermaterial.spl;

public class Sql {
    public static final String PRODUCT="" +
            "select ID id,NAME name,DESCRIPTION description,NOTES notes,IMAGE image,PRICE price,QUATITY quatity,\n" +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive \n" +
            "       from product where ISDELETE = 1";

    public static final String PRODUCT_PRICE_MIN_MAX="" +
            "select ID id,NAME name,DESCRIPTION description,NOTES notes,IMAGE image,PRICE price,QUATITY quatity,\n" +
            "                   case\n" +
            "                       when ISACTIVE = 1 then \"Còn bán\"\n" +
            "                       else \"Không còn sản phẩm\"\n" +
            "                    end isActive\n" +
            "                   from product\n" +
            "where ISDELETE = 1\n" +
            "order by PRICE asc";
    public static final String PRODUCT_PRICE_MAX_MIN="" +
            "select ID id,NAME name,DESCRIPTION description,NOTES notes,IMAGE image,PRICE price,QUATITY quatity,\n" +
            "                   case\n" +
            "                       when ISACTIVE = 1 then \"Còn bán\"\n" +
            "                       else \"Không còn sản phẩm\"\n" +
            "                    end isActive\n" +
            "                   from product\n" +
            "where ISDELETE = 1\n" +
            "order by PRICE desc";
    public static final String PRODUCT_HP="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity," +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive \n" +
            " from product p where ISDELETE = 1 and NAME like N'%hp%'";
    public static final String PRODUCT_ASUS="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity," +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive\n" +
            " from product p where ISDELETE = 1 and NAME like N'%asus%'";
    public static final String PRODUCT_DELL="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity," +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive\n" +
            " from product p where ISDELETE = 1 and NAME like N'%dell%'";
    public static final String PRODUCT_ACER="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity," +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive\n" +
            " from product p where ISDELETE = 1 and NAME like N'%acer%'";
    public static final String PRODUCT_MACBOOK="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity," +
            "       case\n" +
            "           when ISACTIVE = 1 then \"Còn bán\"\n" +
            "           else \"Không còn sản phẩm\"\n" +
            "        end isActive\n" +
            " from product p where ISDELETE = 1 and NAME like N'%macbook%'";

    public static final String PAYMENT_METHOD="select  NAME name,NOTES notes from payment_method pay";
    public static final String TRANSPORT_METHOD="select NAME name,NOTES notes from transport_method";

//    public static final String PRODUCT_CHITIET="" +
//            "select NAME name,DESCRIPTION description,NOTES notes,IMAGE image,PRICE price,QUATITY quatity,\n" +
//            "       case\n" +
//            "           when ISACTIVE = 1 then \"Còn sản phẩm\"\n" +
//            "           else \"Sản phẩm đã hết\"\n" +
//            "        end trangThai\n" +
//            "       from product";

    public static final String LOCPRICE_10="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity " +
            "from product where ISDELETE = 1 and PRICE < 10000000";
    public static final String LOCPRICE_10_15="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity from product where ISDELETE = 1 and PRICE >=10000000 and PRICE <= 15000000";
    public static final String LOCPRICE_15_20="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity from product where ISDELETE = 1 and PRICE >= 15000000 and PRICE <= 20000000";
    public static final String LOCPRICE_20_25="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity from product where ISDELETE = 1 and PRICE >= 20000000 and PRICE <= 25000000";
    public static final String LOCPRICE_25_30="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity from product where ISDELETE = 1 and PRICE >= 25000000 and PRICE <= 30000000";
    public static final String LOCPRICE_30="select ID id,NAME name,DESCRIPTION MoTa,IMAGE image,PRICE price,QUATITY quatity from product where ISDELETE = 1 and PRICE > 30000000";

    public static final String PRODUCT_IMAGE="select prod.ID id,prod.NAME name, prod.NOTES notes,pi.URL url " +
            "from product prod inner join `manager-material`.product_images pi on prod.ID = pi.ID_PRODUCT where prod.ID = ?";
    public static final String ODERS="" +
            "select p.ID id1,orders_details.ID id2,c.NAME tenNguoiDat,o.NAME_RECIVER tenNguoiNhan,o.PHONE phoneNguoiNhan,o.ADDRESS addressNguoiNhan,pm.NAME thanhToan,tm.NAME vanChuyen,\n" +
            "       orders_details.QTY soLuong,\n" +
            "        orders_details.PRICE tienSp,op.TOTAL phiThanhToan,ot.TOTAL phiVanChuyen, o.TOTAL_MONEY tongTien\n" +
            "from orders_details\n" +
            "inner join `manager-material`.product p on orders_details.IDPRODUCT = p.ID\n" +
            "inner join `manager-material`.orders o on orders_details.IDORD = o.ID\n" +
            "inner join `manager-material`.customer c on o.IDCUSTOMER = c.ID\n" +
            "inner join `manager-material`.orders_payment op on o.ID = op.IDORD\n" +
            "inner join `manager-material`.payment_method pm on op.IDPAYMENT = pm.ID\n" +
            "inner join `manager-material`.orders_transport ot on o.ID = ot.IDORD\n" +
            "inner join `manager-material`.transport_method tm on ot.IDTRANSPORT = tm.ID\n" +
            "where ot.NOTES =1 and op.STATUS = 1 and orders_details.ID =?";
    public static final String PRODUCT_TRANGCHU="select ID id,NAME name,IMAGE image,NOTES moTa,PRICE price,QUATITY quatity from product where ISDELETE = 1 and DESCRIPTION like N'%thanh lịch%'";
    public static final String THANH_TOAN="select ID id,NAME name from payment_method where ISACTIVE = 1";
    public static final String VAN_CHUYEN="" +
            "select distinct tm.ID id1,tm.NAME name,ot.TOTAL giaTien\n" +
            "\n" +
            "                  from transport_method  tm\n" +
            "                     inner join `manager-material`.product p on tm.ISACTIVE = p.ISACTIVE\n" +
            "            inner join `manager-material`.orders_transport ot on tm.ID = ot.IDTRANSPORT\n" +
            "            where tm.ISACTIVE=1 and ot.NOTES=1\n" +
            "            order by tm.ID asc";
    public static final String VAN_CHUYEN1="" +
            "select distinct p.ID id,tm.ID id1,tm.NAME name,\n" +
            "       case\n" +
            "            when tm.NAME = \"Xe máy\" then \"50000\"\n" +
            "            when tm.NAME = \"Xe tải\" then \"250000\"\n" +
            "            when tm.NAME = \"Máy bay\" then \"550000\"\n" +
            "        end giaTien,\n" +
            "        case\n" +
            "            when tm.NAME = \"Xe máy\" then p.PRICE+50000\n" +
            "            when tm.NAME = \"Xe tải\" then p.PRICE+250000\n" +
            "            when tm.NAME = \"Máy bay\" then p.PRICE+550000\n" +
            "        end tongTien\n" +
            "       from transport_method  tm\n" +
            "         inner join `manager-material`.product p on tm.ISACTIVE = p.ISACTIVE\n" +
            "inner join `manager-material`.orders_transport ot on tm.ID = ot.IDTRANSPORT\n" +
            "where tm.ISACTIVE=1 and ot.NOTES=1 and p.ID = ?1 and tm.ID = ?2\n" +
            "order by tm.ID asc";
    public static final String PAYMENT="select ID id,NAME name from payment_method where ISACTIVE=1 and ID = ?";
//    public static final String CUSTOMER="select ID id,NAME name,USERNAME uname, PASSWORD pwd from customer where ISACTIVE =1";

    public static final String DONHANG_ADMIN="" +
            "select o.TRANGTHAI trangThai,o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where  o.TRANGTHAI = 1\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG2_ADMIN="" +
            "select o.TRANGTHAI trangThai,o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where  o.TRANGTHAI = 2\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG_TH1="" +
            "select o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where n.id_customer = ? and o.TRANGTHAI = 1\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG_TH2="" +
            "select o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where n.id_customer = ? and o.TRANGTHAI = 2\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG_TH3="" +
            "select o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where n.id_customer = ? and o.TRANGTHAI = 3\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG_TH4="" +
            "select o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where n.id_customer = ? and o.TRANGTHAI = 4\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG_TH0="" +
            "select o.IDORDERS idOrder,o.ID id,n.id_customer idCus,ng.idguoi_nhan idNguoiNhan, o.TOTAL_MONEY total, o.ORDERS_DATE orderDate\n" +
            "            from orders o\n" +
            "                inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "            inner join `manager-material`.nguoinhan n on ng.idguoi_nhan = n.Id\n" +
            "            where n.id_customer = ? and o.TRANGTHAI = 0\n" +
            "            order by o.ORDERS_DATE desc";
    public static final String DONHANG1="" +
            "select distinct  o.TRANGTHAI trangThai,ot.TOTAL tienShip,o.IDORDERS idOrder, o.ID id, o.TOTAL_MONEY total,o.ORDERS_DATE orderDate\n" +
            "    ,n.name nameNguoiNhan,n.address addressNguoiNhan,n.phone phoneNguoiNhan\n" +
            "    ,pm.NAME namePayment,tm.NAME nameTransport\n" +
            "from orders o\n" +
            "    inner join `manager-material`.customer c on o.IDCUSTOMER = c.ID\n" +
            "    inner join `manager-material`.orders_details od on o.ID = od.IDORD\n" +
            "    inner join `manager-material`.orders_payment op on o.ID = op.IDORD\n" +
            "    inner join `manager-material`.payment_method pm on op.IDPAYMENT = pm.ID\n" +
            "    inner join `manager-material`.orders_transport ot on o.ID = ot.IDORD\n" +
            "    inner join `manager-material`.transport_method tm on ot.IDTRANSPORT = tm.ID\n" +
            "    inner join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "    inner join `manager-material`.orders_nguoinhan ng on o.ID = ng.idorder\n" +
            "    inner join `manager-material`.nguoinhan n on c.ID = n.id_customer\n" +
            "where c.ID = ? and o.ID = ? and n.Id = ?";
    public static final String ORDERDETAIL="" +
            "select p.NAME name,od.QTY qty,p.PRICE price,p.IMAGE image,p.NOTES notes from orders_details od\n" +
            "inner join `manager-material`.product p on od.IDPRODUCT = p.ID\n" +
            "where od.IDORD = ?";

}
