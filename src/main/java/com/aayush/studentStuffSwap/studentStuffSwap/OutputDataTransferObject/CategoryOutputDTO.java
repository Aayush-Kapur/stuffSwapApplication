package com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject;

import com.aayush.studentStuffSwap.studentStuffSwap.model.Category;
import lombok.Data;

@Data
public class CategoryOutputDTO {
    private Long categoryId;
    private String categoryName;

    public CategoryOutputDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
    }
}
