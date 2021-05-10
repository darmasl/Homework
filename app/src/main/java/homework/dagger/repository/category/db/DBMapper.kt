package homework.dagger.repository.category.db

import homework.dagger.common.model.Category
import homework.dagger.repository.model.db.CategoryEntity

fun mapToDB(input: Category): CategoryEntity {
    return CategoryEntity(
        idCategory = input.idCategory ?: "",
        strCategory = input.strCategory,
        strCategoryDescription = input.strCategoryDescription,
        strCategoryThumb = input.strCategoryThumb
    )
}

fun mapFromDB(input: CategoryEntity): Category {
    return Category(
        idCategory = input.idCategory,
        strCategory = input.strCategory,
        strCategoryDescription = input.strCategoryDescription,
        strCategoryThumb = input.strCategoryThumb
    )
}