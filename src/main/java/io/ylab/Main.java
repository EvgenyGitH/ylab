package io.ylab;

import io.ylab.in.Menu;
import io.ylab.service.impl.ServiceFactory;

public class Main {
    public static void main(String[] args) {
        ServiceFactory factory = new ServiceFactory();
        Menu menu = new Menu(factory);
        menu.mainMenu();
    }

}
