package com.example.hotelmanagementsy.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate checkin;
    private LocalDate checkout;
    private String roomType;
    private String status;
    private int numberOfRooms;
    private BigDecimal price; // Price per room
    private BigDecimal totalPrice; // Total price for the reservation
    private boolean isVip; // Flag to indicate VIP status

    // Constructors, getters, and setters
    // (omitted for brevity)

    public boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(boolean vip) {
        isVip = vip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public long getNumberOfRooms() {
        return numberOfRooms;
    }

    public BigDecimal getPricePerRoom() {
        return price;
    }

    public void setPricePerRoom(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = BigDecimal.valueOf(totalPrice);
    }
}
