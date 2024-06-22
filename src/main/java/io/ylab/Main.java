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
                    "\n 1 - Создать бронирование рабочего места " +
                    "\n 2 - Посмотреть список бронирования рабочих мест " +
                    "\n 3 - Изменить бронирование рабочего место " +
                    "\n 4 - Удалить бронирование рабочего место  " +
                    "\n 5 - Создать бронирование Конференц-зал " +
                    "\n 6 - Посмотреть cписок бронирования Конференц-залов " +
                    "\n 7 - Изменить бронирование Конференц-зал " +
                    "\n 8 - Удалить бронирование Конференц-зал  " +
                    "\n 0 - Выход");
            if (scanner.hasNextInt()) {
                menuNumber = scanner.nextInt();
                switch (menuNumber) {
                    case 1:
                        System.out.println("Создать бронирование рабочего места");
                        createBookingWorkplace();
                        break;
                    case 2:
                        System.out.println("Посмотреть список бронирования рабочих мест");
                        getListBookingWorkplace();
                        break;
                    case 3:
                        System.out.println("Изменить бронирование рабочего место");
                        updateBookingWorkplace();
                        break;
                    case 4:
                        System.out.println("Удалить бронирование рабочего место");
                        deleteBookingWorkplace();
                        break;
                    case 5:
                        System.out.println("Создать бронирование Конференц-зал");
                        createBookingRoom();
                        break;
                    case 6:
                        System.out.println("Посмотреть cписок бронирования Конференц-залов");
                        getListBookingRoom();
                        break;
                    case 7:
                        System.out.println("Изменить бронирование Конференц-зал");
                        updateBookingRoom();
                        break;
                    case 8:
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
        bookingService.getListBookingWorkplace();
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


    public static void createBookingRoom() {
        Scanner scanner = new Scanner(System.in);
        String roomName ="";
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
        String roomName ="";
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
