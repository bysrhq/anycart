/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.controller;

import com.github.bysrhq.anycart.domain.Brand;
import com.github.bysrhq.anycart.domain.Category;
import com.github.bysrhq.anycart.domain.Color;
import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.domain.Size;
import com.github.bysrhq.anycart.service.BrandService;
import com.github.bysrhq.anycart.service.CategoryService;
import com.github.bysrhq.anycart.service.ColorService;
import com.github.bysrhq.anycart.service.ItemService;
import com.github.bysrhq.anycart.service.SizeService;
import java.io.File;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bysrhq
 */
@Controller
@RequestMapping(value = "/item")
@SessionAttributes("item")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    
    @ModelAttribute("sizes")
    public List<Size> populateSizes() {
        return sizeService.getSizes();
    }
    
    @ModelAttribute("colors")
    public List<Color> populateColors() {
        return colorService.getColors();
    }
    
    @ModelAttribute("brands")
    public List<Brand> populateBrands() {
        return brandService.getBrands();
    }
    
    @ModelAttribute("categories")
    public List<Category> populateCategories() {
        return categoryService.getCategories();
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String showAddItemForm(Item item) {
        
        return "itemForm";
    }
    
    @RequestMapping(value = "add", params = "save", method = RequestMethod.POST)
    public String processAddItemForm(SessionStatus session, MultipartFile file, @Valid Item item, BindingResult errors) {
        if (errors.hasErrors() || file.isEmpty())
            return "itemForm";
        
        itemService.addItem(item, file);
        
        session.setComplete();
        
        return "redirect:/item";
    }
    
    @RequestMapping(value = "add", params = "cancel", method = RequestMethod.POST)
    public String processCancelAddItemForm(SessionStatus session) {
        session.setComplete();
        
        return "redirect:/item";
    }
    
    @RequestMapping(params = "!search", method = RequestMethod.GET)
    public String showItemList(
            @RequestParam(defaultValue = "0") int min,
            @RequestParam(defaultValue = "15") int count,
            @RequestParam(defaultValue = "") String query,
            Model model) {
        model.addAttribute("min", min);
        model.addAttribute("count", count);
        model.addAttribute("query", query);
        model.addAttribute("items", itemService.getItems(min, count, query));
        
        return "itemList";
    }
    
    @RequestMapping(params = "search", method = RequestMethod.GET)
    public String showItemListBySearch(
            @RequestParam(defaultValue = "0") int min,
            @RequestParam(defaultValue = "15") int count,
            @RequestParam(defaultValue = "") String query,
            RedirectAttributes model) {
        model.addAttribute("min", min);
        model.addAttribute("count", count);
        model.addAttribute("query", "name " + query);
        model.addFlashAttribute("items", itemService.getItems(min, count, "name " + query));
        
        return "redirect:/item";
    }
    
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String showEditItemForm(@RequestParam String id, Model model) {
        model.addAttribute("item", itemService.getItem(id));
        
        return "itemForm";
    }
    
    @RequestMapping(value = "edit", params = "save", method = RequestMethod.POST)
    public String processEditItemForm(SessionStatus session, MultipartFile file, @Valid Item item, BindingResult errors) {
        if (errors.hasErrors())
            return "itemForm";
        
        itemService.editItem(item, file);
        
        session.setComplete();
        
        return "redirect:/item";
    }
    
    @RequestMapping(value = "edit", params = "cancel", method = RequestMethod.POST)
    public String processCancelEditItemForm(SessionStatus session) {
        session.setComplete();
        
        return "redirect:/item";
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String processDeleteItem(@RequestParam String id) {
        itemService.deleteItem(id);
        
        return "redirect:/item";
    }
    
    @PostConstruct
    public void prepareImageLocation() {
        File imgDirectory = new File("c:/image");
        if (!imgDirectory.exists())
            imgDirectory.mkdir();
    }
}
