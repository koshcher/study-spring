package rk.hiberent.dtos;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ApartmentSaving {
    @Nullable
    private Long id;
    private Long landlordId;
    private int price;
    private String district;
    private int roomCount;
}
