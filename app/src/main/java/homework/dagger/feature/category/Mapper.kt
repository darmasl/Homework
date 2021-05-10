package homework.dagger.feature.category

import homework.dagger.common.model.Category
import homework.dagger.feature.category.model.CategoryVO

fun mapToVO(input: Category): CategoryVO {
    return CategoryVO(
        idCategory = input.idCategory,
        strCategory = input.strCategory,
        strCategoryDescription = input.strCategoryDescription,
        strCategoryThumb = input.strCategoryThumb
    )
}

fun mapFromVO(input: CategoryVO): Category {
    return Category(
        idCategory = input.idCategory,
        strCategory = input.strCategory,
        strCategoryDescription = input.strCategoryDescription,
        strCategoryThumb = input.strCategoryThumb
    )
}