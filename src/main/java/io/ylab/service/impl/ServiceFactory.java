package io.ylab.service.impl;

import io.ylab.service.BookingService;
import io.ylab.service.ReservationService;
import io.ylab.service.UserService;

/**
 * ServiceFactory, предоставляющая экземпляры сервисов для работы пользователями, бронированием и резервированием.
 */
public class ServiceFactory {
    private final UserService userService;
    private final ReservationService reservationService;
    private final BookingService bookingService;

    public ServiceFactory() {
        this.userService = new UserServiceImpl();
        this.reservationService = new ReservationServiceImpl();
        this.bookingService = new BookingServiceImpl(userService, reservationService);
    }

    public UserService getUserService() {
        return userService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }
}


