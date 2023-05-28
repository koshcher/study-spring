package rk.hiberent.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalSaving {
    private Long id;
    private Long clientId;
    private Long apartmentId;
    private LocalDate startDate;
    private LocalDate endDate;
}
