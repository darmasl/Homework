package homework.dagger.feature.category.categorydetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import homework.dagger.App
import homework.dagger.R
import homework.dagger.common.ui.BaseFragment
import homework.dagger.databinding.CategoryDetailsFragmentBinding
import homework.dagger.feature.common.ui.GridSpaceDecoration
import homework.dagger.feature.meal.mealOverviewCardDelegate
import homework.dagger.feature.meal.model.MealVO
import javax.inject.Inject

class CategoryDetailsFragment :
    BaseFragment<CategoryDetailsContract.View>(R.layout.category_details_fragment),
    CategoryDetailsContract.View {
    companion object {
        private val TAG = CategoryDetailsFragment::class.java.canonicalName
    }

    @Inject
    override lateinit var presenter: CategoryDetailsContract.Presenter

    private lateinit var binding: CategoryDetailsFragmentBinding

    private val mealsAdapter = ListDelegationAdapter(
        mealOverviewCardDelegate(::onOpenMeal)
    )

    private val args: CategoryDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.app.appComponent.inject(this)
        binding = CategoryDetailsFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        presenter.setCategory(args.category)
        setupRecyclerView()
        setupSwipeToRefresh()
    }

    override fun showProgress() = with(binding) {
        if (!swipeToRefresh.isRefreshing) {
            swipeToRefresh.isRefreshing = true
        }
    }

    override fun hideProgress() = with(binding) {
        if (swipeToRefresh.isRefreshing) {
            swipeToRefresh.isRefreshing = false
        }
    }

    override fun showError(title: String?, message: String?) {
        Log.d(TAG, "show error $title $message")
    }

    override fun showMeals(meals: List<MealVO>) {
        this.mealsAdapter.items = meals
        this.mealsAdapter.notifyDataSetChanged()
    }

    override fun showCategoryDescription(description: String?) = with(binding) {
        categoryDescription.text = description
        categoryDescription.isVisible = !description.isNullOrEmpty()
    }

    private fun setupRecyclerView() {
        binding.list.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            if (it.itemDecorationCount == 0) {
                it.addItemDecoration(GridSpaceDecoration(resources.getDimensionPixelSize(R.dimen.category_spacing)))
            }
            it.adapter = this.mealsAdapter
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private fun onOpenMeal(meal: MealVO) {

    }
}