import dao.KhachHang_DAO;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Test_KhachHang {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();
        KhachHang_DAO khachHang_dao = new KhachHang_DAO();

        try{
            KhachHang kh = new KhachHang("Nguyen Van A", "12345678912", "0223456789", "nguyenvana@gmail.com");
            khachHang_dao.create(kh);

            System.out.println("Khách hàng vừa thêm: " + khachHang_dao.getAllKhachHang().getLast().getMaKH() + " - " + khachHang_dao.getAllKhachHang().getLast().getTenKH()
                    + " - " + khachHang_dao.getAllKhachHang().getLast().getSdt() + " - " + khachHang_dao.getAllKhachHang().getLast().getEmail());
            KhachHang khbefore = khachHang_dao.getKhachHangTheoMaKH(khachHang_dao.getAllKhachHang().getLast().getMaKH());
            KhachHang khafter = new KhachHang();
            if(khachHang_dao.updateSoSDT(khbefore.getMaKH(), "0123456789")){
                khafter = khachHang_dao.getKhachHangTheoMaKH(khachHang_dao.getAllKhachHang().getLast().getMaKH());
            }
            System.out.println("Sau khi update số điện thoại: " + khafter.getMaKH() + " - " + khafter.getSdt());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
