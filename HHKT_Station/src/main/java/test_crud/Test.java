package test_crud;

import dao.NhanVien_DAO;
import entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import net.datafaker.Faker;

public class Test {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("HHKT Station").createEntityManager();

        try {
            new NhanVien_DAO(em).getDSNhanVien().forEach(System.out::println);
        } catch (Exception e) {
        }
    }
}
