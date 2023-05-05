package rk.shops.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddShopDto {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String link;
    private String category;
    private String description;
}
