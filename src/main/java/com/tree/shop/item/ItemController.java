package com.tree.shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping("/list")
    String list(Model model) {

        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        var a = new Item();
        System.out.println(a);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {


        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Long price) {
       itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping ("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id) {
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }

    }

    @PostMapping("/edit")
    String editPost(@RequestParam String title,@RequestParam Long price, @RequestParam Long id) {

        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);


        itemRepository.save(item);
        return "redirect:/list";
    }

//    @DeleteMapping("/delete")
//    String deletePost() {
//
//        itemRepository.delete();
//    }


}
