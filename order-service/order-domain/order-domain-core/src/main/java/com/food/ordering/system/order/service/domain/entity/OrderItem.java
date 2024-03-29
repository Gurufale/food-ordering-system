package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {

    private OrderId orderId;
    private final Product product;

    private final int quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        orderId = builder.orderId;
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subtotal = builder.subtotal;
    }

    public static Builder builder() {
        return new Builder();
    }


    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;
    }

    public void initializeOrderItem(OrderId orderId,OrderItemId orderItemId) {
        this.orderId = orderId;
        super.setId(orderItemId);
    }

    boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subtotal);
    }

    public static final class Builder {
        private OrderItemId orderItemId;
        private OrderId orderId;
        private  Product product;
        private  int quantity;
        private  Money price;
        private  Money subtotal;

        private Builder() {

        }

        public Builder(Product product, int quantity, Money price, Money subtotal) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.subtotal = subtotal;
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder product(Product product) {
            product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            quantity = quantity;
            return this;
        }

        public Builder subTotal(Money subtotal) {
            subtotal = subtotal;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }

        public Builder price(Money money) {
            price = money;
            return this;
        }
    }
}
