package org.example.mosike_project.services.CategoryServiceImpl;

import org.example.mosike_project.dto.SubCategoryDTO;
import org.example.mosike_project.entities.SubCategory;
import org.example.mosike_project.mapper.SubCategoryMapper;
import org.example.mosike_project.model.SubCategoryModel;
import org.example.mosike_project.repositories.SubCategoryRepository;
import org.example.mosike_project.services.ISubcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImpl implements ISubcategory {
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private SubCategoryMapper subCategoryMapper;
    @Override
    public int save(SubCategory subCategory) {
        try {
            subCategoryRepository.save(subCategory);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<SubCategoryDTO> getAll() {
        List<SubCategoryDTO> list = subCategoryRepository.findAll().stream().map(e->subCategoryMapper.toDto(e)).toList();

        return list;
    }

    @Override
    public void update(SubCategoryModel subCategoryModel) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryModel.getId()).get();
        subCategory.setName(subCategoryModel.getName());
        subCategoryRepository.save(subCategory);
    }

    @Override
    public int deleteById(long id) {
        try {
            subCategoryRepository.deleteById(id);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public Page<SubCategoryDTO> findAllPage(Pageable pageable) {
        return null;
    }
}
