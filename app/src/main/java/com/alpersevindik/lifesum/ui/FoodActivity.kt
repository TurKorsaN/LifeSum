package com.alpersevindik.lifesum.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import com.alpersevindik.lifesum.R
import com.alpersevindik.lifesum.data.model.Food
import com.alpersevindik.lifesum.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodActivity : AppCompatActivity() {

    private val foodViewModel: FoodViewModel by viewModels()

    private val title by lazy { findViewById<TextView>(R.id.title) }
    private val calorieValue by lazy { findViewById<TextView>(R.id.calorieValue) }

    private val carbsLayout by lazy { findViewById<ViewGroup>(R.id.carbsLayout) }
    private val carbsTitle by lazy { carbsLayout.findViewById<TextView>(R.id.title) }
    private val carbsValue by lazy { carbsLayout.findViewById<TextView>(R.id.value) }

    private val proteinLayout by lazy { findViewById<ViewGroup>(R.id.proteinLayout) }
    private val proteinTitle by lazy { proteinLayout.findViewById<TextView>(R.id.title) }
    private val proteinValue by lazy { proteinLayout.findViewById<TextView>(R.id.value) }

    private val fatLayout by lazy { findViewById<ViewGroup>(R.id.fatLayout) }
    private val fatTitle by lazy { fatLayout.findViewById<TextView>(R.id.title) }
    private val fatValue by lazy { fatLayout.findViewById<TextView>(R.id.value) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        initViews()
        initData()
        foodViewModel.startShakeDetector(this)
    }

    private fun initViews() {
        carbsTitle.setText(R.string.carbs)
        proteinTitle.setText(R.string.protein)
        fatTitle.setText(R.string.fat)
    }

    private fun initData() {
        foodViewModel.foodLiveData.observe(this) { state ->
            when (state) {
                is FoodViewModel.FoodState.Completed -> displayFoodValues(state.data)
                FoodViewModel.FoodState.Failed -> showError()
                FoodViewModel.FoodState.Loading -> showLoading()
            }
        }

        foodViewModel.getFoodForFirstRunIfNeeded()
    }

    @SuppressLint("SetTextI18n")
    private fun displayFoodValues(food: Food) {
        title.text = food.title
        calorieValue.text = food.calories.toString()
        carbsValue.text = "${food.carbs}%"
        proteinValue.text = "${food.protein}%"
        fatValue.text = "${food.fat}%"
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }

    private fun showError() {
    }

    override fun onDestroy() {
        super.onDestroy()
        foodViewModel.stopShakeDetector()
    }
}