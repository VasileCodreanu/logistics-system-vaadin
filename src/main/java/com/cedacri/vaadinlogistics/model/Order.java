package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "order_table" )
public class Order extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatcher_id", referencedColumnName = "ID")
    private Dispatcher dispatcher;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId because we want the child table row to share the Primary Key with its parent table row meaning that the Primary Key is also a Foreign Key back to the parent table record.
    @JoinColumn(name = "carrier_id", referencedColumnName = "ID")
    private Carrier carrier;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "id")
    private Broker broker;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "ID")
    private Vehicle vehicle;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "ID")
    private Customer sender;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "ID")
    private Customer receiver;

    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    private BigDecimal amountToBePaid;

    @NotNull
    private String commodity;
    @CreationTimestamp
    private LocalDate orderPlacedOn;
    private LocalDate orderDeliveredOn;
    private OrderStatus orderStatus;

    public Order() {
        this.orderStatus = OrderStatus.PROCESSING;
    }

    public enum OrderStatus {
        DELIVERED, PROCESSING, CANCELLED
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(dispatcher, order.dispatcher) && Objects.equals(carrier, order.carrier) && Objects.equals(broker, order.broker) && Objects.equals(vehicle, order.vehicle) && Objects.equals(sender, order.sender) && Objects.equals(receiver, order.receiver) && Objects.equals(amountToBePaid, order.amountToBePaid) && Objects.equals(commodity, order.commodity) && Objects.equals(orderPlacedOn, order.orderPlacedOn) && Objects.equals(orderDeliveredOn, order.orderDeliveredOn) && orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dispatcher, carrier, broker, vehicle, sender, receiver, amountToBePaid, commodity, orderPlacedOn, orderDeliveredOn, orderStatus);
    }
}
