package com.bsc.kiosk.menu.model.service;

import com.bsc.kiosk.menu.model.dao.MenuRepository;

public class MenuService {

    private final MenuRepository menuRepository = new MenuRepository();

    public void checkMenu() {
        System.out.println("[MenuService] checkMenu() : 메뉴 조회 성공 - 개발 완료 후 삭제 구문");


    }

}
