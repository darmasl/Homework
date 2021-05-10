package homework.dagger.repository.category.network

import homework.dagger.common.model.Category
import homework.dagger.repository.model.network.CategoriesResponse

fun mapFromNetwork(input: CategoriesResponse.Category): Category {
    return Category(
        idCategory = input.idCategory,
        strCategory = input.strCategory,
        strCategoryDescription = input.strCategoryDescription,
        strCategoryThumb = input.strCategoryThumb
    )
}