package com.skifer.epam_internship_android_checkunov.presentation.feature.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.skifer.epam_internship_android_checkunov.App
import com.skifer.epam_internship_android_checkunov.R
import com.skifer.epam_internship_android_checkunov.databinding.FragmentMealDetailsBinding
import com.skifer.epam_internship_android_checkunov.di.ComponentProvider
import com.skifer.epam_internship_android_checkunov.presentation.feature.ViewHolderAdapter
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.di.DaggerDetailsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.di.DetailsComponent
import com.skifer.epam_internship_android_checkunov.presentation.feature.details.viewmodel.MealDetailsViewModel
import com.skifer.epam_internship_android_checkunov.presentation.feature.host.view.showStatusBar
import com.skifer.epam_internship_android_checkunov.presentation.model.IngredientModel
import com.skifer.epam_internship_android_checkunov.presentation.model.MealModel
import javax.inject.Inject

/**
 * Displays detailed information about selected dish in main fragment
 */
class MealDetailsFragment : Fragment(R.layout.fragment_meal_details),
    ComponentProvider<DetailsComponent> {

    private lateinit var binding: FragmentMealDetailsBinding

    @Inject
    lateinit var viewModel: MealDetailsViewModel

    override val component: DetailsComponent by lazy {
        DaggerDetailsComponent.factory().create(App.instance.appComponent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().showStatusBar(false)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().showStatusBar(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        observeMeal()
    }

    private fun observeMeal() {
        val id = arguments?.getInt(MEAL_ID_INTENT) ?: error(
            getString(R.string.error_wrong_id)
        )
        viewModel.run {
            loadData(id)
            error.observe(viewLifecycleOwner) {
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
                findNavController().popBackStack()
            }
            meal.observe(viewLifecycleOwner) {
                bindData(it)
            }
        }
    }

    /**
     * Binding data with view components
     * @param meal [MealModel] to bindData with components
     */
    private fun bindData(meal: MealModel?) =
        binding.apply {
            meal?.let {
                view?.let { view ->
                    Glide.with(view)
                        .load(
                            it.strMealThumb
                                ?: R.drawable.heheboi
                        )
                        .into(detailMealImage)
                }
                toolbarMealDetails.setNavigationOnClickListener {
                    findNavController().popBackStack()
                }

                bottomSheet.apply {
                    detailTitle.text =
                        it.strMeal
                            ?: getString(R.string.empty)
                    cuisine.text =
                        it.strArea
                            ?: getString(R.string.empty)
                    instructions.text =
                        it.strInstructions
                            ?: getString(R.string.empty_instructions)
                    if (it.tags != null) {
                        tagList.adapter =
                            ViewHolderAdapter<String>()
                                .setList(it.tags)
                    } else {
                        tagList.isVisible = false
                    }
                    if (it.ingredientModels != null) {
                        ingredientsList.adapter =
                            ViewHolderAdapter<IngredientModel>()
                                .setList(it.ingredientModels)
                    } else {
                        ingredientsList.isVisible = false
                    }

                    if (it.strYoutube != null) {
                        youTube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(it.strYoutube, 0F)
                                youTubePlayer.pause()
                            }
                        })
                    } else youTube.isVisible = false
                }
            }
        }

    companion object {
        const val MEAL_ID_INTENT = "MEAL_ID_INTENT"

        /**
         * Should be called instead instead of just instantiating the class
         * @param dishId [MealModel] id containing information to be displayed on the screen
         */
        fun newInstance(dishId: Int) = MealDetailsFragment().apply {
            arguments = bundleOf(MEAL_ID_INTENT to dishId)
        }
    }
}