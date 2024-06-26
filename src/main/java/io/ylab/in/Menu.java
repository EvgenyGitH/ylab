package io.ylab.in;

import io.ylab.out.Printer;
import io.ylab.service.BookingService;
import io.ylab.service.ReservationService;
import io.ylab.service.UserService;
import io.ylab.service.impl.ServiceFactory;

import java.util.Scanner;

/**
 * Menu, предоставляющая пользовательский интерфейс для ввода информации.
 */
public class Menu {
    private final UserService userService;
    private final ReservationService reservationService;
    private final BookingService bookingService;

    public Menu(ServiceFactory factory) {
        this.userService = factory.getUserService();
        this.reservationService = factory.getReservationService();
        this.bookingService = factory.getBookingService();
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuNumber;
        while (true) {
            Printer.printMessage("Сервис бронирования Ylab." +
                    "\n Введите номер из меню: " +
                    "\n 1 - Регистрация пользователя " +
                    "\n 2 - Войти в систему \n 0 - Выход");

            if (scanner.hasNextInt()) {
                menuNumber = scanner.nextInt();
                if (menuNumber == 1) {
                    Printer.printMessage("Регистрация пользователя");
                    createUser();
                    break;
                } else if (menuNumber == 2) {
                    Printer.printMessage("Войти в систему");
                    loginToService();
                    break;
                } else if (menuNumber == 0) {
                    Printer.printMessage("Выход");
                    break;
                } else {
                    Printer.printMessage("Данный пункт отсутствует. Попробуйте ввести новый пункт меню");
                }
            } else {
                Printer.printMessage("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
    }

    public void userMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuNumber;
        while (true) {
            Printer.printMessage("Личный кабинет Пользователя." +
                    "\n Введите номер из меню: " +
                    "\n 1 - Посмотреть список рабочих мест " +
                    "\n 2 - Просмотр доступных слотов для бронирования рабочих мест на конкретную дату " +
                    "\n 3 - Создать бронирование рабочего места " +
                    "\n 4 - Посмотреть список бронирования рабочих мест " +
                    "\n 5 - Изменить бронирование рабочего место " +
                    "\n 6 - Удалить бронирование рабочего место  " +
                    "\n 7 - Посмотреть список Конференц-зал " +
                    "\n 8 - Просмотр доступных слотов для бронирования Конференц-зал на конкретную дату " +
                    "\n 9 - Создать бронирование Конференц-зал " +
                    "\n 10 - Посмотреть cписок бронирования Конференц-залов " +
                    "\n 11 - Изменить бронирование Конференц-зал " +
                    "\n 12 - Удалить бронирование Конференц-зал  " +
                    "\n 0 - Выход");
            if (scanner.hasNextInt()) {
                menuNumber = scanner.nextInt();
                switch (menuNumber) {
                    case 1:
                        Printer.printMessage("Посмотреть список рабочих мест");
                        getListWorkplaceForUser();
                        break;
                    case 2:
                        Printer.printMessage("Просмотр доступных слотов для бронирования рабочих мест на конкретную дату");
                        getListBookingWorkplaceByDate();
                        break;
                    case 3:
                        Printer.printMessage("Создать бронирование рабочего места");
                        createBookingWorkplace();
                        break;
                    case 4:
                        Printer.printMessage("Посмотреть список бронирования рабочих мест (фильтр: all, date, user)");
                        getListBookingWorkplace();
                        break;
                    case 5:
                        Printer.printMessage("Изменить бронирование рабочего место");
                        updateBookingWorkplace();
                        break;
                    case 6:
                        Printer.printMessage("Удалить бронирование рабочего место");
                        deleteBookingWorkplace();
                        break;
                    case 7:
                        Printer.printMessage("Посмотреть список Конференц-зал");
                        getListRoomForUser();
                        break;
                    case 8:
                        Printer.printMessage("Просмотр доступных слотов для бронирования Конференц-зал на конкретную дату");
                        getListBookingRoomByDate();
                        break;
                    case 9:
                        Printer.printMessage("Создать бронирование Конференц-зал");
                        createBookingRoom();
                        break;
                    case 10:
                        Printer.printMessage("Посмотреть cписок бронирования Конференц-залов (фильтр: all, date, user)");
                        getListBookingRoom();
                        break;
                    case 11:
                        Printer.printMessage("Изменить бронирование Конференц-зал");
                        updateBookingRoom();
                        break;
                    case 12:
                        Printer.printMessage("Удалить бронирование Конференц-зал");
                        deleteBookingRoom();
                        break;
                    case 0:
                        Printer.printMessage("Выход");
                        mainMenu();
                        break;
                    default:
                        System.out.println("Данный пункт отсутствует. Попробуйте ввести новый пункт меню");
                }
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }


        }
    }


    public void createUser() {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        String login = "";
        String password = "";

        while (name.isBlank()) {
            System.out.println("Введите ваше Имя: ");
            name = scanner.nextLine();
        }

        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }

        while (password.isBlank()) {
            System.out.println("Введите ваше Пароль: ");
            password = scanner.nextLine();
        }
        userService.addUser(name, login, password);
        mainMenu();
    }

    public void loginToService() {
        Scanner scanner = new Scanner(System.in);
        String login = "";
        String password = "";

        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }

        while (password.isBlank()) {
            System.out.println("Введите ваше Пароль: ");
            password = scanner.nextLine();
        }

        if (login.equals("admin") && password.equals("admin")) {
            adminMenu();
        } else if (userService.authorization(login, password)) {
            userMenu();
        } else {
            mainMenu();
        }

    }

    //-----
    public void getListWorkplaceForUser() {
        reservationService.getListWorkplace();
        userMenu();
    }

    public void getListBookingWorkplaceByDate() {
        Scanner scanner = new Scanner(System.in);
        String bookingDate = "";
        while (bookingDate.isBlank()) {
            System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
            bookingDate = scanner.nextLine();
        }
        bookingService.getListBookingWorkplaceByDate(bookingDate);
        userMenu();
    }

    public void createBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        String login = "";
        String startTime = "";
        while (workplaceNumber == 0) {
            System.out.println("Введите номер рабочего места");
            if (scanner.hasNextInt()) {
                workplaceNumber = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }
        while (startTime.isBlank()) {
            System.out.println("Введите дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);
        userMenu();
    }

    public void getListBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        String login = "";
        String bookingDate = "";
        while (action.isBlank()) {
            System.out.println("Выберите фильтр: all - показать все, date - на указанную дату, user - по Пользователю ");
            action = scanner.nextLine();
        }

        if (action.equals("all")) {
            login = "";
            bookingDate = "";
        } else if (action.equals("date")) {
            while (bookingDate.isBlank()) {
                System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
                bookingDate = scanner.nextLine();
            }
        } else if (action.equals("user")) {
            while (login.isBlank()) {
                System.out.println("Введите ваше Логин: ");
                login = scanner.nextLine();
            }
        } else {
            System.out.println("Данный пункт отсутствует");
            userMenu();
        }
        bookingService.getListBookingWorkplace(login, bookingDate);
        userMenu();

    }

    public void updateBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        String login = "";
        int bookingId = 0;
        String startTime = "";
        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }
        while (bookingId == 0) {
            System.out.println("Введите номер бронирования (BookingId) рабочего места");
            if (scanner.hasNextInt()) {
                bookingId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (workplaceNumber == 0) {
            System.out.println("Введите новый номер рабочего места");
            if (scanner.hasNextInt()) {
                workplaceNumber = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (startTime.isBlank()) {
            System.out.println("Введите новую дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }
        bookingService.updateBookingWorkplace(bookingId, workplaceNumber, login, startTime);
        userMenu();
    }

    public void deleteBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int bookingId = 0;
        System.out.println("Введите BookingId для удаления");
        while (bookingId == 0) {
            if (scanner.hasNextInt()) {
                bookingId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        bookingService.deleteBookingWorkplaceById(bookingId);
        userMenu();
    }

//-----

    public void getListRoomForUser() {
        reservationService.getListRoom();
        userMenu();
    }

    public void getListBookingRoomByDate() {
        Scanner scanner = new Scanner(System.in);
        String bookingDate = "";
        while (bookingDate.isBlank()) {
            System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
            bookingDate = scanner.nextLine();
        }
        bookingService.getListBookingRoomByDate(bookingDate);
        userMenu();

    }

    public void createBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        String login = "";
        String startTime = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала: ");
            roomName = scanner.nextLine();
        }
        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }
        while (startTime.isBlank()) {
            System.out.println("Введите дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }
        bookingService.createBookingRoom(roomName, login, startTime);
        userMenu();
    }

    public void getListBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        String login = "";
        String bookingDate = "";
        while (action.isBlank()) {
            System.out.println("Выберите фильтр: all - показать все, date - на указанную дату, user - по Пользователю ");
            action = scanner.nextLine();
        }
        if (action.equals("all")) {
            login = "";
            bookingDate = "";
        } else if (action.equals("date")) {
            while (bookingDate.isBlank()) {
                System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
                bookingDate = scanner.nextLine();
            }
        } else if (action.equals("user")) {
            while (login.isBlank()) {
                System.out.println("Введите ваше Логин: ");
                login = scanner.nextLine();
            }
        } else {
            System.out.println("Данный пункт отсутствует");
            userMenu();
        }
        bookingService.getListBookingRoom(login, bookingDate);
        userMenu();
    }

    public void updateBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        String login = "";
        int bookingId = 0;
        String startTime = "";

        while (login.isBlank()) {
            System.out.println("Введите ваше Логин: ");
            login = scanner.nextLine();
        }
        while (bookingId == 0) {
            System.out.println("Введите номер бронирования (BookingId) рабочего места");
            if (scanner.hasNextInt()) {
                bookingId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (roomName.isBlank()) {
            System.out.println("Введите новое название Конференц-зала: ");
            roomName = scanner.nextLine();
        }
        while (startTime.isBlank()) {
            System.out.println("Введите новую дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }
        bookingService.updateBookingRoom(bookingId, roomName, login, startTime);
        userMenu();
    }

    public void deleteBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        int bookingId = 0;
        System.out.println("Введите BookingId для удаления");
        while (bookingId == 0) {
            if (scanner.hasNextInt()) {
                bookingId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        bookingService.deleteBookingRoomById(bookingId);
        userMenu();
    }


    //для доступа к меню Админа вход login: admin, password: admin
    public void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuNumber;
        while (true) {
            System.out.println("Личный кабинет Администратора." +
                    "\n Введите номер из меню: " +
                    "\n 1 - Создать рабочее место " +
                    "\n 2 - Посмотреть список рабочих мест " +
                    "\n 3 - Изменить рабочее место " +
                    "\n 4 - Удалить рабочее место  " +
                    "\n 5 - Создать Конференц-зал " +
                    "\n 6 - Посмотреть cписок Конференц-залов " +
                    "\n 7 - Изменить Конференц-зал " +
                    "\n 8 - Удалить Конференц-зал  " +
                    "\n 0 - Выход");
            if (scanner.hasNextInt()) {
                menuNumber = scanner.nextInt();
                switch (menuNumber) {
                    case 1:
                        System.out.println("Создать рабочее место");
                        createWorkplace();
                        break;
                    case 2:
                        System.out.println("Посмотреть список рабочих мест");
                        getListWorkplace();
                        break;
                    case 3:
                        System.out.println("Изменить рабочее место");
                        updateWorkplace();
                        break;
                    case 4:
                        System.out.println("Удалить рабочее место");
                        deleteWorkplace();
                        break;
                    case 5:
                        System.out.println("Создать Конференц-зал");
                        createRoom();
                        break;
                    case 6:
                        System.out.println("Посмотреть cписок Конференц-залов");
                        getListRoom();
                        break;
                    case 7:
                        System.out.println("Изменить Конференц-зал");
                        updateRoom();
                        break;
                    case 8:
                        System.out.println("Удалить Конференц-зал");
                        deleteRoom();
                        break;
                    case 0:
                        System.out.println("Выход");
                        mainMenu();
                        break;
                    default:
                        System.out.println("Данный пункт отсутствует. Попробуйте ввести новый пункт меню");
                }
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }


        }
    }

    //обработка Workplace - Админ
    public void createWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        String description = "";
        while (workplaceNumber == 0) {
            System.out.println("Введите номер рабочего места");
            if (scanner.hasNextInt()) {
                workplaceNumber = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (description.isBlank()) {
            System.out.println("Введите описание рабочего места: ");
            description = scanner.nextLine();
        }
        reservationService.createWorkplace(workplaceNumber, description);
        adminMenu();
    }

    public void getListWorkplace() {
        reservationService.getListWorkplace();
        adminMenu();
    }

    public void updateWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        String description = "";
        while (workplaceNumber == 0) {
            System.out.println("Введите номер рабочего места для внесения изменений");
            if (scanner.hasNextInt()) {
                workplaceNumber = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (description.isBlank()) {
            System.out.println("Введите новое описание рабочего места: ");
            description = scanner.nextLine();
        }
        reservationService.updateWorkplace(workplaceNumber, description);
        adminMenu();
    }

    public void deleteWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        System.out.println("Введите номер рабочего места для удаления");
        while (workplaceNumber == 0) {
            if (scanner.hasNextInt()) {
                workplaceNumber = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        reservationService.deleteWorkplace(workplaceNumber);
        adminMenu();
    }

    //обработка Room - Админ
    public void createRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        String description = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала: ");
            roomName = scanner.nextLine();
        }
        while (description.isBlank()) {
            System.out.println("Введите описание Конференц-зала: ");
            description = scanner.nextLine();
        }
        reservationService.createRoom(roomName, description);
        adminMenu();
    }

    public void getListRoom() {
        reservationService.getListRoom();
        adminMenu();
    }

    public void updateRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        String description = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала: ");
            roomName = scanner.nextLine();
        }
        while (description.isBlank()) {
            System.out.println("Введите новое описание рабочего места: ");
            description = scanner.nextLine();
        }

        reservationService.updateRoom(roomName, description);
        adminMenu();
    }

    public void deleteRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала для удаления: ");
            roomName = scanner.nextLine();
        }
        reservationService.deleteRoom(roomName);
        adminMenu();
    }


}
