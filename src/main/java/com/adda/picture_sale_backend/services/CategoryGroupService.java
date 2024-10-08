//package com.adda.picture_sale_backend.services;
//
//import com.adda.picture_sale_backend.database.SequenceGeneratorService;
//import com.adda.picture_sale_backend.entities.Category;
//import com.adda.picture_sale_backend.entities.CategoryGroup;
//import com.adda.picture_sale_backend.repositories.CategoryGroupRepository;
//import com.adda.picture_sale_backend.utils.DateUtils;
//
//import com.mongodb.client.result.UpdateResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.Aggregation;
//import org.springframework.data.mongodb.core.aggregation.AggregationResults;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoryGroupService {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Autowired
//    CategoryGroupRepository categoryGroupRepository;
//
//    @Autowired
//    SequenceGeneratorService sequenceGeneratorService;
//
//    public CategoryGroup insert(CategoryGroup transactionGroup){
//        Long id = sequenceGeneratorService.generateSequence(CategoryGroup.SEQUENCE_NAME);
//        transactionGroup.setId(id);
//        transactionGroup.setCreatedDate(DateUtils.getCurrentDate());
//        transactionGroup.setStatus(1);
//        return categoryGroupRepository.insert(transactionGroup);
//    }
//
//    public CategoryGroup update(CategoryGroup group){
//        Optional<CategoryGroup> optionalTransactionGroup = categoryGroupRepository.findById(group.getId());
//        if(optionalTransactionGroup.isPresent()){
//            CategoryGroup update = optionalTransactionGroup.get();
//            update.setCode(group.getCode());
//            update.setName(group.getName());
//            update.setIcon(group.getIcon());
//            update.setDesc(group.getDesc());
//            return categoryGroupRepository.save(update);
//        }
//        return  null;
//    }
//
//    public CategoryGroup findById(Long id){
//        return categoryGroupRepository.findById(id).orElse(null);
//    }
//
//    public CategoryGroup delete(Long id){
//        Optional<CategoryGroup> optionalTransactionGroup = categoryGroupRepository.findById(id);
//        if(optionalTransactionGroup.isPresent()){
//            CategoryGroup update = optionalTransactionGroup.get();
//            update.setStatus(0);
//            return categoryGroupRepository.save(update);
//        }
//        return  null;
//    }
//
//    public List<CategoryGroup> findAll()
//    {
//        return categoryGroupRepository.findAll();
//    }
//
//
//    public List<Category> getAllCategoriesInAllGroups() {
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.unwind("categoryList"),
//                Aggregation.replaceRoot("categoryList")
//        );
//
//        AggregationResults<Category> results = mongoTemplate.aggregate(aggregation, "categoryGroup", Category.class);
//        return results.getMappedResults();
//    }
//
////    public List<CategoryGroup> findByUserId(Long userID){
////        return transactionGroupRepository.findAllByUserID(userID);
////    }
//
//    public CategoryGroup addCategory(Long id, Category category){
//        Optional<CategoryGroup> categoryGroupOptional = categoryGroupRepository.findById(id);
//        if (categoryGroupOptional.isPresent()) {
//            CategoryGroup categoryGroupFound = null;
//            categoryGroupFound = categoryGroupOptional.get();
//            Long categoryID = sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME);
//            category.setId(categoryID);
//            category.setActive(true);
//            category.setCreatedDate(DateUtils.getCurrentDate());
//            category.setGroupName(categoryGroupFound.getName());
//            category.setGroupID(categoryGroupFound.getId());
//            List<Category> categoryList = new ArrayList<>();
//            if(categoryGroupFound.getCategoryList()!= null)
//            {
//                categoryList = categoryGroupFound.getCategoryList();
//            }
//            categoryList.add(category);
//            categoryGroupFound.setCategoryList(categoryList);
//            return categoryGroupRepository.save(categoryGroupFound);
//        }
//        return null;
//    }
//
//
//    public CategoryGroup deleteCategory(Long idGroup, Long categoryID){
//        Optional<CategoryGroup> categoryGroupOptional = categoryGroupRepository.findById(idGroup);
//        if (categoryGroupOptional.isPresent()) {
//            CategoryGroup categoryGroupFound = null;
//
//            categoryGroupFound = categoryGroupOptional.get();
//
//            List<Category> categoryList = categoryGroupFound.getCategoryList();
//            int index = 0;
//            for(Category item: categoryList){
//                if(categoryID == item.getId()){
//                    break;
//                }
//                index++;
//            }
//            categoryList.get(index).setActive(false);
//            categoryGroupFound.setCategoryList(categoryList);
//            return categoryGroupRepository.save(categoryGroupFound);
//        }
//        return null;
//    }
//
//    public Category findCategoryById(Long categoryId) {
//        List<Category> listAll = new ArrayList<>();
//        listAll = getAllCategoriesInAllGroups();
//
//        List<Category> filteredCategories = listAll.stream()
//                .filter(category -> category.getId().equals(categoryId) )
//                .toList();
//
//        if (!filteredCategories.isEmpty()) {
//            return filteredCategories.get(0);
//        }
//        return  null;
//    }
//
//    public List<Category> findCategoryByGroupId(Long groupId) {
//        List<Category> listAll = new ArrayList<>();
//        listAll = getAllCategoriesInAllGroups();
//
//        List<Category> filteredCategories = new ArrayList<>();
//        filteredCategories = listAll.stream()
//                .filter(category -> category.getGroupID().equals(groupId) )
//                .toList();
//        return  filteredCategories;
//    }
//
//    public Category getCategoryById(Long categoryId) {
//
//        List<Category> listAll = getAllCategoriesInAllGroups();
//        for(Category item: listAll){
//            if(item.getId() == categoryId){
//                return item;
//            }
//        }
//        return  null;
////        Query query = new Query(Criteria.where("categoryList.id").is(categoryId));
////        return mongoTemplate.findOne(query, Category.class, "CategoryGroup");
//    }
//
//    public CategoryGroup updateCategory(Long categoryId, Category category) {
//        Query query = new Query(Criteria.where("categoryList.id").is(categoryId));
//        Update update = new Update().set("categoryList.$", category);
//        UpdateResult result = mongoTemplate.updateFirst(query, update, CategoryGroup.class);
//        if (result.getModifiedCount() > 0) {
//            // Document updated successfully
//            return mongoTemplate.findOne(query, CategoryGroup.class);
//        } else {
//            // No document matched the query
//            return null;
//        }
//
//    }
//
//}
