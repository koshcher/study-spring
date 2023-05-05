package rk.shops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rk.shops.dtos.AddShopDto;
import rk.shops.models.Shop;
import rk.shops.repositories.ShopRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopsController {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/")
    public String shopList(Model model,
        @RequestParam @Nullable String name,
        @RequestParam @Nullable String category,
        @RequestParam @Nullable String address
    ) {
        Shop.ShopBuilder shopBuilder = Shop.builder();

        if(name != null && !name.isEmpty()) shopBuilder.name(name);
        if(category != null && !category.isEmpty()) shopBuilder.category(category);
        if(address != null && !address.isEmpty()) shopBuilder.address(address);

        Example<Shop> shopExample = Example.of(shopBuilder.build());
        List<Shop> shops = shopRepository.findAll(shopExample);
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/{id}")
    public String getShop(Model model, @PathVariable Long id) {
        Optional<Shop> shop = shopRepository.getShopById(id);

        if(!shop.isPresent()) return "shop-not-found";

        model.addAttribute("shop", shop.get());
        return "shop";
    }

    @GetMapping("/add")
    public String getAdd() { return "add"; }


    @GetMapping("/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        Optional<Shop> shop = shopRepository.getShopById(id);
        if(!shop.isPresent()) return "shop-not-found";
        model.addAttribute("shop", shop.get());
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Shop> shop = shopRepository.getShopById(id);
        shop.ifPresent(value -> shopRepository.delete(value));
        return "redirect:/";
    }

    @PostMapping("/add")
    public String postAdd(AddShopDto shopDto) {
        Shop shop = Shop.builder()
                .name(shopDto.getName())
                .address(shopDto.getAddress())
                .phone(shopDto.getPhone())
                .email(shopDto.getEmail())
                .link(shopDto.getLink())
                .category(shopDto.getCategory())
                .description(shopDto.getDescription())
                .build();

        shopRepository.save(shop);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String postAdd(Shop shop) {

        Optional<Shop> maybeDbShop = shopRepository.getShopById(shop.getId());
        if(!maybeDbShop.isPresent()) return "redirect:/";

        Shop dbShop = maybeDbShop.get();
        dbShop.setName(shop.getName());
        dbShop.setAddress(shop.getAddress());
        dbShop.setPhone(shop.getPhone());
        dbShop.setEmail(shop.getEmail());
        dbShop.setLink(shop.getLink());
        dbShop.setCategory(shop.getCategory());
        dbShop.setDescription(shop.getDescription());

        shopRepository.save(dbShop);
        return "redirect:/";
    }
}
