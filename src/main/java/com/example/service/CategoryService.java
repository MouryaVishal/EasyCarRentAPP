package com.example.service;

import com.example.exception.categoryException.CategoryNotFoundException;
import com.example.exception.categoryException.CategoryAlreadyFoundException;
import com.example.model.Category;
import com.example.repo.CategoryRepository;
import com.example.request.CategoryRequest;
import com.example.service.servicesInterface.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

//! All operation related to category service is done
@Service
public class CategoryService implements CategoryServiceInterface {
    @Autowired
    private CategoryRepository categoryRepository;

//    * adding new category in DB by providing CategoryRequest object
    public ResponseEntity<Object> addCategory(CategoryRequest request){
        String categoryName= request.getCategoryName();
        Optional<Category> category=categoryRepository.findByCategoryName(categoryName);
        if(category.isPresent()){
            throw new CategoryAlreadyFoundException(categoryName);
        }
        Category newCategory=new Category();
        newCategory.setCategoryName(categoryName);
        categoryRepository.save(newCategory);
        return new ResponseEntity<>(newCategory,HttpStatus.OK);
    }
//* fetching all category in the space;
    public Iterable<Category> allCategory(){
        return categoryRepository.findAll();
    }

//    * deleting coupon from space by providing its Id, if it is not in used;
    public String deleteById(Long id){
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Deleted SuccessFully!!";
        };
        return "Delete Fail. No Such id found!!";
    }


//    * updating category by providing category ID and CategoryRequest object;
    public ResponseEntity<Object> updateById(Long id, CategoryRequest categoryDetails) {
        String categoryName= categoryDetails.getCategoryName();
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Optional<Category> checkingCategoryPresent=categoryRepository.findByCategoryName(categoryName);
            if(checkingCategoryPresent.isPresent()){
                return new ResponseEntity<>(categoryName+" is already present in the space...",HttpStatus.CONFLICT);
            }
            optionalCategory.get().setCategoryName(categoryName);
            categoryRepository.save(optionalCategory.get());
            return new ResponseEntity<>(optionalCategory, HttpStatus.OK);
        } else {
            throw new CategoryNotFoundException();
        }
    }


}
