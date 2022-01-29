package com.spring.bitlab_spring.controller;

import com.spring.bitlab_spring.entities.Category;
import com.spring.bitlab_spring.entities.Countries;
import com.spring.bitlab_spring.entities.ShopItems;
import com.spring.bitlab_spring.entities.Users;
import com.spring.bitlab_spring.service.ItemService;
import com.spring.bitlab_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(Model model){

          List<ShopItems> items = itemService.getAllItems();
          List<Countries> countries = itemService.getAllCountries();

          model.addAttribute("currUser", userData());

          model.addAttribute("countryList", countries);
          model.addAttribute("itemsList", items);

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("currUser", userData());
        return "about";
    }

    @PostMapping("/addItem")
    public String addItem(
            @RequestParam("item_name") String name,
            @RequestParam("item_price") int price,
            @RequestParam("item_amount") int amount,
            @RequestParam("country_id") Long cnt_id
    ){
        Countries country = itemService.getCountryById(cnt_id);

        if(country!=null){
            ShopItems shopItem = new ShopItems();
            shopItem.setName(name);
            shopItem.setPrice(price);
            shopItem.setAmount(amount);
            shopItem.setCountries(country);

            itemService.mergeItem(shopItem);
        }

        return "redirect:/";
    }

    @PostMapping("/saveItem")
    public String saveItem(
            @RequestParam("item_id") Long id,
            @RequestParam("item_name") String name,
            @RequestParam("item_price") int price,
            @RequestParam("item_amount") int amount,
            @RequestParam("country_id") Long cnt_id
    ){

        ShopItems shopItem = itemService.getById(id);

        if(shopItem != null){

            Countries country = itemService.getCountryById(cnt_id);

            if(country!=null){
                shopItem.setName(name);
                shopItem.setPrice(price);
                shopItem.setAmount(amount);
                shopItem.setCountries(country);
                itemService.mergeItem(shopItem);
            }
        }

        return "redirect:/";
    }

    @PostMapping("/deleteItem")
    public String deleteItem(
            @RequestParam("item_id") Long id
    ){
        ShopItems shopItem = itemService.getById(id);

        if(shopItem != null){
            itemService.deletById(id);
        }

        return "redirect:/";
    }



    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model){

        ShopItems shopItem = itemService.getById(id);
        List<Countries> countries = itemService.getAllCountries();
        List<Category> categories = itemService.getAllCategories();

        model.addAttribute("currUser", userData());
        model.addAttribute("getItem", shopItem);
        model.addAttribute("countryList", countries);
        model.addAttribute("categoryList", categories);



        return "details";

    }

    @PostMapping("/addCategory")
    public String addCategory(
            @RequestParam("cat_id") Long cat_id,
            @RequestParam("item_id") Long item_id
    ){
        Category category = itemService.getCategoryById(cat_id);

        if(category!=null){

            ShopItems item = itemService.getById(item_id);

            if(item!=null){

                Set<Category> categories = item.getCategories();

                if(categories == null){
                   categories = new HashSet<>();
                }

                categories.add(category);

                itemService.mergeItem(item);

                return "redirect:/details/" + item_id;
            }
        }

        return "redirect:/";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(
            @RequestParam("cat_id") Long cat_id,
            @RequestParam("item_id") Long item_id
    ){
        ShopItems item = itemService.getById(item_id);

        if(item != null){
            Category category = itemService.getCategoryById(cat_id);

            if(category != null){

                if(item.getCategories().contains(category)){
                    item.getCategories().remove(category);

                    itemService.mergeItem(item);

                    return "redirect:/details/" + item_id;
                }

            }
        }
        return "redirect:/";
    }

    @GetMapping("/forbidden")
    public String forbidden(){

        return "forbidden";
    }

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("currUser", userData());

        return "login";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){

        model.addAttribute("currUser", userData());

        return "profile";
    }

    private Users userData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            Users user = userService.getUserByEmail(secUser.getUsername());

            return user;
        }

        return null;

    }
}
