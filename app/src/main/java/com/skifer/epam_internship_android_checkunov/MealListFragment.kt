package com.skifer.epam_internship_android_checkunov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skifer.epam_internship_android_checkunov.food_types.FoodType
import com.skifer.epam_internship_android_checkunov.food_types.KitchenCountry
import com.skifer.epam_internship_android_checkunov.list_adapter.Adapter
import com.skifer.epam_internship_android_checkunov.model.MealModel

class MealListFragment : Fragment(R.layout.fragment_meal_list), Adapter.onItemListener {

    lateinit var dishListView: RecyclerView
    lateinit var dishes: List<MealModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dishes = someDishes()
        dishListView = view.findViewById(R.id.dishListView)
        dishListView.layoutManager = LinearLayoutManager(context)
        dishListView.adapter = Adapter(dishes, this)
    }

    fun someDishes(): List<MealModel> {
        val dishes = mutableListOf<MealModel>()
        dishes.add(
                MealModel(
                        id = 0,
                        name = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
                        type = FoodType.MEAT,
                        country = KitchenCountry.EAST,
                        picture = R.drawable.soy_glazed_meatloaves
                )
        )
        dishes.add(
                MealModel(
                        id = 1,
                        name = "Steak Diane",
                        type = FoodType.MEAT,
                        country = KitchenCountry.USA,
                        picture = R.drawable.steak_diane
                )
        )
        dishes.add(
                MealModel(
                        id = 2,
                        name = "Nice and hot spicy meat",
                        type = FoodType.MEAT,
                        country = KitchenCountry.JAMAICAN,
                        picture = R.drawable.heheboi
                )
        )
        return dishes
    }

    override fun onItemClick(meal: MealModel) {
        requireActivity().supportFragmentManager
            .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            .replace(
                R.id.containerHost,
                MealDetailsFragment.
                newInstance(meal)
            )
            .addToBackStack(null)
            .commit()
    }

}