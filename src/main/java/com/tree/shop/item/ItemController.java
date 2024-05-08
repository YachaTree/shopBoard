package com.tree.shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /**
     * 상품목록 페이지
     * @param model
     * @return
     */
    @GetMapping("/list")
    String list(Model model) {

        List<Item> result = itemRepository.findAll();
        //Html에 데이터 보내려면 model.addAttribute("데이터이름", 데이터 )
        model.addAttribute("items", result);
        var a = new Item();
        System.out.println(a);
        return "list.html";
    }

    /**
     * 상품추가 페이지
     * @return
     */
    @GetMapping("/write")
    String write() {

        return "write.html";
    }


    /**
     * 상품추가
     * @param title
     * @param price
     * @return
     */
    @PostMapping("/add") // URL 작명시 명사가 좋음
    String addPost(String title, Long price) {
        /*
        @RequestParam(name = "title") 붙이면 되는데 생략가능
         ajax로 데이터전송하면 @RequestBody 써야 출력가능
        */
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

//    @PostMapping("/add")
//    String addPost(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "redirect:/list";
//    }


    /**
     * 상세페이지
     * @param id
     * @param model
     * @return
     */
    @GetMapping ("/detail/{id}") //@PathVariable은 URL 경로의 일부를 메소드의 파라미터로 추출할 때 사용
    String detail(@PathVariable Long id, Model model) {
        //Optional은 해당값이 null값일 수도 있고 아닐수도 있다는 타입이다.
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    /**
     * 상품수정 페이지
     * @param model
     * @param id
     * @return
     */
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

    /**
     * 상품수정
     * @param title
     * @param price
     * @param id
     * @return
     */
    @PostMapping("/edit")
    String editPost(@RequestParam String title,@RequestParam Long price, @RequestParam Long id) {

      itemService.editItem(title, price, id);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("삭제완료");
    }


}
