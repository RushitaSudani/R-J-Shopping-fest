package org.technous.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.technous.validation.dto.CategoryDTO;
import org.technous.validation.service.CategoryService;
import org.technous.validation.util.ApiResponse;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categotyDTO) {
        CategoryDTO c1 = categoryService.createCategories(categotyDTO);
        return new ResponseEntity<CategoryDTO>(c1, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{cid}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categotyDTO, @PathVariable("cid") Long cid) {
        CategoryDTO c1 = categoryService.updateCategory(categotyDTO, cid);
        return new ResponseEntity<>(c1, HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> c1 = categoryService.getall();
        return c1;
    }

    @GetMapping("/getById/{cid}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("cid") Long cid) {
        CategoryDTO c1 = categoryService.getById(cid);
        return new ResponseEntity<CategoryDTO>(c1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{cid}")
    public ApiResponse deleteCategory(@PathVariable("cid") Long cid) {
        categoryService.deleteCategory(cid);
        return new ApiResponse("Category deleted", true);
    }

}
