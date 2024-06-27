package ylab;

import io.ylab.model.BookingRoom;
import io.ylab.model.BookingWorkplace;
import io.ylab.model.Room;
import io.ylab.model.Workplace;
import io.ylab.service.ReservationService;
import io.ylab.service.UserService;
import io.ylab.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static io.ylab.utils.Utils.formatter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    BookingServiceImpl bookingService;

    @Mock
    ReservationService reservationService;

    @Mock
    UserService userService;

    Map<String, Room> roomList;
    Map<Integer, Workplace> workplaceList;

    @BeforeEach
    void setUp() {
        roomList = new TreeMap<>();
        String roomName = "Room1";
        String description = "Description1";
        Room room = new Room(roomName, description);
        room.setId(1);
        roomList.put("Room1", room);

        workplaceList = new TreeMap<>();
        int workplaceNumber = 1;
        String descriptionW = "DescriptionWorkplace1";
        Workplace workplace = new Workplace(workplaceNumber, descriptionW);
        workplace.setId(1);
        workplaceList.put(workplaceNumber, workplace);
    }

    @Test
    @DisplayName("Создание бронирования рабочего места")
    public void testCreateBookingWorkplace() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);
        when(reservationService.getListWorkplace()).thenReturn(workplaceList);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);

        List<BookingWorkplace> bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0).getUserLogin()).isEqualTo(login);
        assertThat(bookings.get(0).getWorkplaceNumber()).isEqualTo(workplaceNumber);
        assertThat(bookings.get(0).getStartTime()).isEqualTo(LocalDateTime.parse(startTime, formatter));
    }

    @Test
    @DisplayName("Обновление бронирования рабочего места")
    public void testUpdateBookingWorkplace() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);
        when(reservationService.getListWorkplace()).thenReturn(workplaceList);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);
        List<BookingWorkplace> bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        String newStartTime = "23.06.2024 11:00";
        bookingService.updateBookingWorkplace(bookingId, workplaceNumber, login, newStartTime);

        bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0).getStartTime()).isEqualTo(LocalDateTime.parse(newStartTime, formatter));
    }

    @Test
    @DisplayName("Удаление бронирования рабочего места")
    public void testDeleteBookingWorkplace() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);
        when(reservationService.getListWorkplace()).thenReturn(workplaceList);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);
        List<BookingWorkplace> bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        bookingService.deleteBookingWorkplaceById(bookingId);

        bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        assertThat(bookings).isEmpty();
    }


    @Test
    @DisplayName("Создание бронирования конференц-зала")
    public void testCreateBookingRoom() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);
        when(reservationService.getListRoom()).thenReturn(roomList);

        bookingService.createBookingRoom(roomName, login, startTime);

        List<BookingRoom> bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0).getUserLogin()).isEqualTo(login);
        assertThat(bookings.get(0).getRoomName()).isEqualTo(roomName);
        assertThat(bookings.get(0).getStartTime()).isEqualTo(LocalDateTime.parse(startTime, formatter));
    }

    @Test
    @DisplayName("Обновление бронирования конференц-зала")
    public void testUpdateBookingRoom() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);
        when(reservationService.getListRoom()).thenReturn(roomList);

        bookingService.createBookingRoom(roomName, login, startTime);
        List<BookingRoom> bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        String newStartTime = "23.06.2024 11:00";
        bookingService.updateBookingRoom(bookingId, roomName, login, newStartTime);

        bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        assertThat(bookings).hasSize(1);
        assertThat(bookings.get(0).getStartTime()).isEqualTo(LocalDateTime.parse(newStartTime, formatter));
    }


    @Test
    @DisplayName("Удаление бронирования конференц-зала")
    public void testDeleteBookingRoom() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(anyString())).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);
        when(reservationService.getListRoom()).thenReturn(roomList);

        bookingService.createBookingRoom(roomName, login, startTime);
        List<BookingRoom> bookings = bookingService.getListBookingRoomByDate("23.06.2024");

        int bookingId = bookings.get(0).getBookingId();
        bookingService.deleteBookingRoomById(bookingId);

        bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        assertThat(bookings).isEmpty();
    }

    @Test
    @DisplayName("Проверка свободного времени рабочего места")
    public void testIsWorkplaceTimeFree() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);

        boolean isFree = bookingService.isWorkplaceTimeFree(workplaceNumber, "23.06.2024 11:00");
        assertThat(isFree).isTrue();

        isFree = bookingService.isWorkplaceTimeFree(workplaceNumber, startTime);
        assertThat(isFree).isFalse();
    }

    @Test
    @DisplayName("Проверка свободного времени конференц-зала")
    public void testIsRoomTimeFree() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);

        bookingService.createBookingRoom(roomName, login, startTime);

        boolean isFree = bookingService.isRoomTimeFree(roomName, "23.06.2024 11:00");
        assertThat(isFree).isTrue();

        isFree = bookingService.isRoomTimeFree(roomName, startTime);
        assertThat(isFree).isFalse();
    }

    @Test
    @DisplayName("Проверка существования бронирования рабочего места по ID")
    public void testIsExistBookingWorkplaceById() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);
        when(reservationService.getListWorkplace()).thenReturn(workplaceList);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);
        List<BookingWorkplace> bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        boolean exists = bookingService.isExistBookingWorkplaceById(bookingId);

        assertThat(exists).isTrue();

        exists = bookingService.isExistBookingWorkplaceById(999);
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Проверка существования бронирования конференц-зала по ID")
    public void testIsExistBookingRoomById() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);
        when(reservationService.getListRoom()).thenReturn(roomList);

        bookingService.createBookingRoom(roomName, login, startTime);
        List<BookingRoom> bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        boolean exists = bookingService.isExistBookingRoomById(bookingId);
        assertThat(exists).isTrue();

        exists = bookingService.isExistBookingRoomById(999);
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Получение бронирования рабочего места по ID")
    public void testGetBookingWorkplace() {
        int workplaceNumber = 1;
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistWorkplace(workplaceNumber)).thenReturn(true);
        when(reservationService.getListWorkplace()).thenReturn(workplaceList);

        bookingService.createBookingWorkplace(workplaceNumber, login, startTime);
        List<BookingWorkplace> bookings = bookingService.getListBookingWorkplaceByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        BookingWorkplace booking = bookingService.getBookingWorkplace(bookingId);
        assertThat(booking).isNotNull();
        assertThat(booking.getBookingId()).isEqualTo(bookingId);

        booking = bookingService.getBookingWorkplace(999);
        assertThat(booking).isNull();
    }


    @Test
    @DisplayName("Получение бронирования конференц-зала по ID")
    public void testGetBookingRoom() {
        String roomName = "Room1";
        String login = "user1";
        String startTime = "23.06.2024 10:00";

        when(userService.isExistsLogin(login)).thenReturn(true);
        when(reservationService.isExistRoom(roomName)).thenReturn(true);
        when(reservationService.getListRoom()).thenReturn(roomList);

        bookingService.createBookingRoom(roomName, login, startTime);
        List<BookingRoom> bookings = bookingService.getListBookingRoomByDate("23.06.2024");
        int bookingId = bookings.get(0).getBookingId();

        BookingRoom booking = bookingService.getBookingRoom(bookingId);
        assertThat(booking).isNotNull();
        assertThat(booking.getBookingId()).isEqualTo(bookingId);

        booking = bookingService.getBookingRoom(999);
        assertThat(booking).isNull();
    }
}


