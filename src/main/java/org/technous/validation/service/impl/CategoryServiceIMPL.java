package org.technous.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technous.validation.dto.CategoryDTO;
import org.technous.validation.exception.ResourceNotFoundException;
import org.technous.validation.model.Category;
import org.technous.validation.repository.CategoryRepository;
import org.technous.validation.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceIMPL implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO categoriestoDto(Category categories) {
        CategoryDTO categotyDTO = new CategoryDTO();
        categotyDTO.setCategorydescription(categories.getCategorydescription());
        categotyDTO.setCategorytitle(categories.getCategorytitle());

        // CategotyDTO c1=this.modelMapper.map(categories, CategotyDTO.class);

        return categotyDTO;
    }
    public Category dtotoCategory(CategoryDTO categotyDTO){

        Category category = new Category();
        category.setCategorytitle(categotyDTO.getCategorytitle());
        category.setCategorydescription(categotyDTO.getCategorydescription());
        //Category c1=this.modelMapper.map(categotyDTO,Categories.class);
        return category;
    }

    @Override
    public CategoryDTO createCategories(CategoryDTO categotyDTO) {
        Category c1=dtotoCategory(categotyDTO);
        Category save= categoryRepository.save(c1);
        return categoriestoDto(save);

    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categotyDTO, Long id) {
        Category c1= categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("categoryid","categoryid",id));
        c1.setCategorytitle(categotyDTO.getCategorytitle());
        c1.setCategorydescription(categotyDTO.getCategorydescription());
        Category save= categoryRepository.save(c1);
        return categoriestoDto(save);
    }

    @Override
    public void deleteCategory(Long cid) {
        Category c1= categoryRepository.findById(cid)
                .orElseThrow(()->new ResourceNotFoundException("categories not found","categoryid",cid));
        this.categoryRepository.delete(c1);
    }

    @Override
    public CategoryDTO getById(Long id) {
        Category c1= categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Categoies not found","categoryid",id));
        return categoriestoDto(c1);
    }

    @Override
    public List<CategoryDTO> getall() {
        List<Category> c1= categoryRepository.findAll();
        List<CategoryDTO> categotyDTOS=c1.stream().map(categories->this.categoriestoDto(categories)).collect(Collectors.toList());
        return categotyDTOS;
    }
}
