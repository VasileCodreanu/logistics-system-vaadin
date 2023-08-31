package com.cedacri.vaadinlogistics.model;

import com.cedacri.vaadinlogistics.model.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_table" )
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatcher_id", referencedColumnName = "ID")
    private Dispatcher dispatcher;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId because we want the child table row to share the Primary Key with its parent table row meaning that the Primary Key is also a Foreign Key back to the parent table record.
    @JoinColumn(name = "carrier_id", referencedColumnName = "ID")
    private Carrier carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broker_id", referencedColumnName = "ID")
    private Broker broker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "ID")
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "ID")
    private Customer sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", referencedColumnName = "ID")
    private Customer receiver;

    private double amountToBePaid;
    private String commodity;
    @CreationTimestamp
    private LocalDate orderPlacedOn;
    private LocalDate orderDeliveredOn;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        DELIVERED, PROCESSING, CANCELLED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Order order = (Order) o;

        if (Double.compare(amountToBePaid, order.amountToBePaid) != 0) {
            return false;
        }
        if (!Objects.equals(dispatcher, order.dispatcher)) {
            return false;
        }
        if (!Objects.equals(carrier, order.carrier)) {
            return false;
        }
        if (!Objects.equals(broker, order.broker)) {
            return false;
        }
        if (!Objects.equals(vehicle, order.vehicle)) {
            return false;
        }
        if (!Objects.equals(sender, order.sender)) {
            return false;
        }
        if (!Objects.equals(receiver, order.receiver)) {
            return false;
        }
        if (!Objects.equals(commodity, order.commodity)) {
            return false;
        }
        if (!Objects.equals(orderPlacedOn, order.orderPlacedOn)) {
            return false;
        }
        if (!Objects.equals(orderDeliveredOn, order.orderDeliveredOn)) {
            return false;
        }
      return orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (broker != null ? broker.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        temp = Double.doubleToLongBits(amountToBePaid);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (commodity != null ? commodity.hashCode() : 0);
        result = 31 * result + (orderPlacedOn != null ? orderPlacedOn.hashCode() : 0);
        result = 31 * result + (orderDeliveredOn != null ? orderDeliveredOn.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }
}
