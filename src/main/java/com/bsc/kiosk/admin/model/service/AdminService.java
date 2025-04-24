package com.bsc.kiosk.admin.model.service;

import com.bsc.kiosk.admin.model.dao.AdminRepository;
import com.bsc.kiosk.admin.model.dto.AdminDTO;

import java.sql.Connection;

import static com.bsc.kiosk.common.JDBCTemplate.*;

public class AdminService {
    private static final AdminRepository adminRepository = new AdminRepository();
    public void insertMenu(AdminDTO admins) {

        Connection con = getConnection();
        int result = adminRepository.insertMenu(con, admins);

        // 수행 결과에 따라 Commit, Rollback 정해야한다.
        if (result > 0) {
            commit(con);
            System.out.println("추가 완료! 추가된 메뉴 : \n" + admins);
        } else {
            rollback(con);
            System.out.println("메뉴 추가 실패");
        }
        close(con);
    }

    public void updateMenu(AdminDTO admins) {
        Connection con = getConnection();
        int result = adminRepository.updateMenu(con, admins);

        // 수행 결과에 따라 Commit, Rollback 정해야한다.
        if (result > 0) {
            commit(con);
            System.out.println("수정 완료! \n" + admins);
        } else {
            rollback(con);
            System.out.println("메뉴 수정 실패");
        }
        close(con);
    }

    public void deleteMenu(AdminDTO admins) {
        Connection con = getConnection();
        int result = adminRepository.deleteMenu(con, admins);

        if (result > 0) {
            commit(con);
            System.out.println("삭제 완료!");
        } else {
            rollback(con);
            System.out.println("메뉴 삭제 실패");
        }
        close(con);
    }
}
