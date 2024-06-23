package io.ylab;

import io.ylab.model.*;
import io.ylab.service.BookingService;
import io.ylab.service.ReservationService;
import io.ylab.service.UserService;

import java.util.Scanner;

public class Main {
    static UserService userService = new UserService();
    static ReservationService reservationService = new ReservationService();
    static BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuNumber;
        while (true) {
            System.out.println("Сервис бронирования Ylab." +
                    "\n Введите номер из меню: " +
                    "\n 1 - Регистрация пользователя " +
                    "\n 2 - Войти в систему \n 0 - Выход");

            if (scanner.hasNextInt()) {
                menuNumber = scanner.nextInt();
                if (menuNumber == 1) {
                    System.out.println("Регистрация пользователя");
                    createUser();
                    break;
                } else if (menuNumber == 2) {
                    System.out.println("Войти в систему");
                    loginToService();
                    break;
                } else if (menuNumber == 0) {
                    System.out.println("Выход");
                    break;
                } else {
                    System.out.println("Данный пункт отсутствует. Попробуйте ввести новый пункт меню");
                }
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
    }

    public static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuNumber;
        while (true) {
            System.out.println("Личный кабинет Пользователя." +
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
                        System.out.println("Посмотреть список рабочих мест");
                        getListWorkplaceForUser();
                        break;
                    case 2:
                        System.out.println("Просмотр доступных слотов для бронирования рабочих мест на конкретную дату");
                        getListBookingWorkplaceByDate();
                        break;
                    case 3:
                        System.out.println("Создать бронирование рабочего места");
                        createBookingWorkplace();
                        break;
                    case 4:
                        System.out.println("Посмотреть список бронирования рабочих мест");
                        getListBookingWorkplace();
                        break;
                    case 5:
                        System.out.println("Изменить бронирование рабочего место");
                        updateBookingWorkplace();
                        break;
                    case 6:
                        System.out.println("Удалить бронирование рабочего место");
                        deleteBookingWorkplace();
                        break;
                    case 7:
                        System.out.println("Посмотреть список Конференц-зал");
                        getListRoomForUser();
                        break;
                    case 8:
                        System.out.println("Просмотр доступных слотов для бронирования Конференц-зал на конкретную дату");
                        getListBookingWorkplaceByDate();
                        break;
                    case 9:
                        System.out.println("Создать бронирование Конференц-зал");
                        createBookingRoom();
                        break;
                    case 10:
                        System.out.println("Посмотреть cписок бронирования Конференц-залов");
                        getListBookingRoom();
                        break;
                    case 11:
                        System.out.println("Изменить бронирование Конференц-зал");
                        updateBookingRoom();
                        break;
                    case 12:
                        System.out.println("Удалить бронирование Конференц-зал");
                        deleteBookingRoom();
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


    public static void createUser() {
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

        User user = new User(name, login, password);
        userService.addUser(user);
        mainMenu();
    }

    public static void getListWorkplaceForUser() {
        reservationService.getListWorkplace();
        userMenu();
    }

    public static void getListBookingWorkplaceByDate() {
        Scanner scanner = new Scanner(System.in);

        String bookingDate = "";

        while (bookingDate.isBlank()) {
            System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
            bookingDate = scanner.nextLine();
        }

        bookingService.getListBookingWorkplaceByDate(bookingDate);
        userMenu();

    }

    public static void createBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        int userId = 0;
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
        while (userId == 0) {
            System.out.println("Введите Ваш userId:");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (startTime.isBlank()) {
            System.out.println("Введите дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }
        if (userService.isExistsByUserId(userId) && reservationService.isExistWorkplace(workplaceNumber)) {
            BookingWorkplace bookingWorkplace = new BookingWorkplace(workplaceNumber, userId, startTime);
            bookingService.createBookingWorkplace(bookingWorkplace);
        } else {
            System.out.println("Не соответствуют вводные данные");
        }

        userMenu();
    }

    public static void getListBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        int workplaceNumber = 0;
        int userId = 0;
        String bookingDate = "";
        while (action.isBlank()) {
            System.out.println("Выберите фильтр: all - показать все, date - на указанную дату, user - по Пользователю ");
            action = scanner.nextLine();
        }

        if (action.equals("all")) {
            userId = 0;
            bookingDate = "";
        } else if (action.equals("date")) {
            while (bookingDate.isBlank()) {
                System.out.println("выберите дату в формате: dd.MM.yyyy HH:mm : Пример: 23.06.2024");
                bookingDate = scanner.nextLine();
            }
        } else if (action.equals("user")) {
            while (userId == 0) {
                System.out.println("Введите Ваш userId:");
                if (scanner.hasNextInt()) {
                    userId = scanner.nextInt();
                } else {
                    System.out.println("введите целое число. Попробуйте заново");
                    scanner.next();
                }
            }
        } else {
            System.out.println("Данный пункт отсутствует");
            userMenu();
        }


        bookingService.getListBookingWorkplace(userId,bookingDate);

        userMenu();

    }

    public static void updateBookingWorkplace() {
        Scanner scanner = new Scanner(System.in);
        int workplaceNumber = 0;
        int userId = 0;
        int bookingId = 0;
        String startTime = "";

        while (userId == 0) {
            System.out.println("Введите Ваш userId:");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
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

        if (userService.isExistsByUserId(userId) && reservationService.isExistWorkplace(workplaceNumber) && bookingService.isExistBookingWorkplaceById(bookingId)) {
            BookingWorkplace bookingWorkplace = new BookingWorkplace(workplaceNumber, userId, startTime);
            bookingService.updateBookingWorkplace(bookingId, bookingWorkplace);
        } else {
            System.out.println("Не соответствуют вводные данные");
        }
        userMenu();
    }

    public static void deleteBookingWorkplace() {
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


    public static void getListRoomForUser() {
        reservationService.getListRoom();
        userMenu();
    }

    public static void createBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        int userId = 0;
        String startTime = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала: ");
            roomName = scanner.nextLine();
        }

        while (userId == 0) {
            System.out.println("Введите Ваш userId:");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
        }
        while (startTime.isBlank()) {
            System.out.println("Введите дату и время начала работы в формате: dd.MM.yyyy HH:mm (длительность сессии 59 минут): Пример: 23.06.2024 11:00");
            startTime = scanner.nextLine();
        }
        if (userService.isExistsByUserId(userId) && reservationService.isExistRoom(roomName)) {
            BookingRoom bookingRoom = new BookingRoom(roomName, userId, startTime);
            bookingService.createBookingRoom(bookingRoom);
        } else {
            System.out.println("Не соответствуют вводные данные");
        }

        userMenu();
    }

    public static void getListBookingRoom() {
        bookingService.getListBookingRoom();
        userMenu();
    }

    public static void updateBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        int userId = 0;
        int bookingId = 0;
        String startTime = "";

        while (userId == 0) {
            System.out.println("Введите Ваш userId:");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
            } else {
                System.out.println("введите целое число. Попробуйте заново");
                scanner.next();
            }
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

        if (userService.isExistsByUserId(userId) && reservationService.isExistRoom(roomName) && bookingService.isExistBookingRoomById(bookingId)) {
            BookingRoom bookingRoom = new BookingRoom(roomName, userId, startTime);
            bookingService.updateBookingRoom(bookingId, bookingRoom);
        } else {
            System.out.println("Не соответствуют вводные данные");
        }
        userMenu();
    }

    public static void deleteBookingRoom() {
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
    public static void adminMenu() {
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
    public static void createWorkplace() {
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
        Workplace workplace = new Workplace(workplaceNumber, description);
        reservationService.createWorkplace(workplace);
        adminMenu();
    }

    public static void getListWorkplace() {
        reservationService.getListWorkplace();
        adminMenu();
    }

    public static void updateWorkplace() {
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
        Workplace workplace = new Workplace(workplaceNumber, description);
        reservationService.updateWorkplace(workplace);
        adminMenu();
    }

    public static void deleteWorkplace() {
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
    public static void createRoom() {
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
        Room room = new Room(roomName, description);
        reservationService.createRoom(room);
        adminMenu();
    }

    public static void getListRoom() {
        reservationService.getListRoom();
        adminMenu();
    }

    public static void updateRoom() {
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
        Room room = new Room(roomName, description);
        reservationService.updateRoom(room);
        adminMenu();
    }

    public static void deleteRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName = "";
        while (roomName.isBlank()) {
            System.out.println("Введите название Конференц-зала для удаления: ");
            roomName = scanner.nextLine();
        }
        reservationService.deleteRoom(roomName);
        adminMenu();
    }


    public static void loginToService() {
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


}
