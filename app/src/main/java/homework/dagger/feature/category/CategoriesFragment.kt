package homework.dagger.feature.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import homework.dagger.R
import homework.dagger.common.ui.BaseFragment
import homework.dagger.databinding.CategoriesFragmentBinding
import homework.dagger.feature.category.model.CategoryVO
import homework.dagger.feature.common.ui.GridSpaceDecoration

class CategoriesFragment : BaseFragment<CategoryContract.View>(R.layout.categories_fragment),
    CategoryContract.View {
    companion object {
        private val TAG = CategoriesFragment::class.java.canonicalName
    }

    private lateinit var binding: CategoriesFragmentBinding

    private val categoryAdapter: ListDelegationAdapter<List<CategoryVO>> = ListDelegationAdapter(
        categoryCardDelegate(::openCategory)
    )

    override val presenter: CategoryContract.Presenter = CategoryPresenter()

    override fun showProgress() {
        Log.d(TAG, "show progress")
    }

    override fun hideProgress() {
        Log.d(TAG, "hide progress")
    }

    override fun showError(title: String?, message: String?) {
        Log.d(TAG, "show error $title $message")
    }

    override fun showCategories(categories: List<CategoryVO>) {
        this.categoryAdapter.items = categories
        this.categoryAdapter.notifyDataSetChanged()
        Log.d(
            TAG,
            "show categories ${categories.joinToString(separator = "\n") { it.toString() }}"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = CategoriesFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.list.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            if (it.itemDecorationCount == 0) {
                it.addItemDecoration(GridSpaceDecoration(resources.getDimensionPixelSize(R.dimen.category_spacing)))
            }
            it.adapter = this.categoryAdapter
        }
    }

    private fun openCategory(categoryVO: CategoryVO) {
        Log.d(
            CategoriesFragment::class.java.canonicalName,
            "Open category: ${categoryVO.strCategory}"
        )
        presenter.openCategory(categoryVO)
    }
}
