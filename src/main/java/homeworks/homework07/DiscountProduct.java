package homeworks.homework07;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {
    private double discount;
    private LocalDate discountExpiryDate;
    private boolean isDiscountPermanent;

    // Конструктор для скидки с конкретной датой окончания:
    public DiscountProduct(String name, double price, double discount, LocalDate discountExpiryDate) {
        super(name, price);
        validateDiscount(discount);
        Objects.requireNonNull(discountExpiryDate, "Дата окончания скидки не может быть null.");
        this.discount = discount;
        this.discountExpiryDate = discountExpiryDate;
        this.isDiscountPermanent = false;
    }

    // Конструктор для бессрочной скидки (на случай, если вдруг такая будет):
    public DiscountProduct(String name, double price, double discount) {
        super(name, price);
        validateDiscount(discount);
        this.discount = discount;
        this.discountExpiryDate = null;
        this.isDiscountPermanent = true;
    }

    // Валидация скидки:
    private void validateDiscount(double discount) {
        if (discount <= 0) {
            throw new IllegalArgumentException("Скидка должна быть больше 0.");
        }
        if (discount >= super.getPrice()) {
            throw new IllegalArgumentException("Скидка не может быть больше или равна стоимости продукта.");
        }
    }

    // Геттеры:
    public double getDiscount() {
        return discount;
    }

    public LocalDate getDiscountExpiryDate() {
        return discountExpiryDate;
    }

    public boolean isDiscountPermanent() {
        return isDiscountPermanent;
    }

    // Получение актуальной цены с учетом скидки:
    @Override
    public double getPrice() {
        if (!isDiscountPermanent && discountExpiryDate != null && LocalDate.now().isAfter(discountExpiryDate)) {
            return super.getPrice(); // Срок скидки истек
        }

        double discountedPrice = super.getPrice() - discount;
        if (discountedPrice <= 0) {
            throw new IllegalStateException("Цена со скидкой не может быть 0 или отрицательной.");
        }
        return discountedPrice;
    }

    // Переопределение toString для отображения информации о скидке:
    @Override
    public String toString() {
        if (isDiscountPermanent) {
            return super.toString() + " (скидка: " + discount + ", бессрочная)";
        } else if (discountExpiryDate != null && LocalDate.now().isAfter(discountExpiryDate)) {
            return super.toString() + " (скидка истекла)";
        } else {
            return super.toString() + " (скидка: " + discount + ", действует до: " + discountExpiryDate + ")";
        }
    }
}