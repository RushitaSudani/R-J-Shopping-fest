package org.technous.validation.service;


import org.technous.validation.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO createCategories(CategoryDTO categotyDTO);
    public CategoryDTO updateCategory(CategoryDTO categotyDTO,Long id);
    public void deleteCategory(Long cid);
    public CategoryDTO getById(Long id);
    public List<CategoryDTO> getall();
}
