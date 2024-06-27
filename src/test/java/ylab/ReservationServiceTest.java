package ylab;

import io.ylab.service.ReservationService;
import io.ylab.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationServiceImpl();
    }

    @Test
    @DisplayName("Создание рабочего места")
    public void testCreateWorkplace() {
        int workplaceNumber = 1;
        String description = "Description1";

        reservationService.createWorkplace(workplaceNumber, description);

        assertThat(reservationService.getListWorkplace()).containsKey(workplaceNumber);
        assertThat(reservationService.getListWorkplace().get(workplaceNumber).getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Обновление рабочего места")
    public void testUpdateWorkplace() {
        int workplaceNumber = 1;
        String description = "Description1";

        reservationService.createWorkplace(workplaceNumber, description);
        String newDescription = "NewDescription1";
        reservationService.updateWorkplace(workplaceNumber, newDescription);

        assertThat(reservationService.getListWorkplace().get(workplaceNumber).getDescription()).isEqualTo(newDescription);
    }

    @Test
    @DisplayName("Удаление рабочего места")
    public void testDeleteWorkplace() {
        int workplaceNumber = 1;
        String description = "Description1";

        reservationService.createWorkplace(workplaceNumber, description);
        reservationService.deleteWorkplace(workplaceNumber);

        assertThat(reservationService.getListWorkplace()).doesNotContainKey(workplaceNumber);
    }

    @Test
    @DisplayName("Создание конференц-зала")
    public void testCreateRoom() {
        String roomName = "Room1";
        String description = "Description1";

        reservationService.createRoom(roomName, description);

        assertThat(reservationService.getListRoom()).containsKey(roomName);
        assertThat(reservationService.getListRoom().get(roomName).getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Обновление конференц-зала")
    public void testUpdateRoom() {
        String roomName = "Room1";
        String description = "Description1";

        reservationService.createRoom(roomName, description);
        String newDescription = "NewDescription1";
        reservationService.updateRoom(roomName, newDescription);

        assertThat(reservationService.getListRoom().get(roomName).getDescription()).isEqualTo(newDescription);
    }

    @Test
    @DisplayName("Удаление конференц-зала")
    public void testDeleteRoom() {
        String roomName = "Room1";
        String description = "Description1";

        reservationService.createRoom(roomName, description);
        reservationService.deleteRoom(roomName);

        assertThat(reservationService.getListRoom()).doesNotContainKey(roomName);
    }

    @Test
    @DisplayName("Проверка существования рабочего места")
    public void testIsExistWorkplace() {
        int workplaceNumber = 1;
        String description = "Description1";

        reservationService.createWorkplace(workplaceNumber, description);
        boolean exists = reservationService.isExistWorkplace(workplaceNumber);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Проверка отсутствия рабочего места")
    public void testIsNotExistWorkplace() {
        boolean exists = reservationService.isExistWorkplace(999);

        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Проверка существования конференц-зала")
    public void testIsExistRoom() {
        String roomName = "Room1";
        String description = "Description1";

        reservationService.createRoom(roomName, description);
        boolean exists = reservationService.isExistRoom(roomName);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Проверка отсутствия конференц-зала")
    public void testIsNotExistRoom() {
        boolean exists = reservationService.isExistRoom("Room2");

        assertThat(exists).isFalse();
    }
}


