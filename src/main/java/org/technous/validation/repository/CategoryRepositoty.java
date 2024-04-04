package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Category;

@Repository
public interface CategoryRepositoty extends JpaRepository<Category,Long> {

    public Category findByName(String name);

//    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.perentCategory.name = :parentCategoryName")
//    public List<Category> findByNameAndPerent(@Param("name") String name,
//                                              @Param("parentCategoryName") String parentCategoryName);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.perentCategory.name = :parentCategoryName")
    public Category findByNameAndPerent(@Param("name") String name,
                                        @Param("parentCategoryName") String parentCategoryName);
}
