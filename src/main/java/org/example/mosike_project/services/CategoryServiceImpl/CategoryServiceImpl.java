package org.example.mosike_project.services.CategoryServiceImpl;


import org.example.mosike_project.dto.CategoryDTO;
import org.example.mosike_project.entities.Category;
import org.example.mosike_project.mapper.CategoryMapper;
import org.example.mosike_project.model.CategoryModel;
import org.example.mosike_project.repositories.CategoryRepository;
import org.example.mosike_project.services.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategory {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> categoryDTOS =categoryRepository.findAll().stream().map(e->categoryMapper.toDto(e)).toList();
        return categoryDTOS;
    }

    @Override
    public int save(CategoryModel categoryModel) {
        try {
            Category category = new Category();
            category.setName(categoryModel.getName());
            category.setStatus(categoryModel.getStatus());
            categoryRepository.save(category);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        Category category =categoryRepository.findById(id).get();
        try {
            if (category!=null){
                category.getSubCategories().clear();
                categoryRepository.save(category);
            }
            categoryRepository.deleteById(id);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void update(CategoryModel categoryModel) {
        Category category = categoryRepository.findById(categoryModel.getId()).get();
        category.setName(categoryModel.getName());
        category.setStatus(category.getStatus());
        categoryRepository.save(category);
    }

    @Override
    public Page<CategoryDTO> findAllPage(Pageable pageable) {
        Page<CategoryDTO> page = categoryRepository.findAll(pageable).map(e->categoryMapper.toDto(e));
        return page;
    }
}
