package com.alpersevindik.lifesum.viewmodel

import android.content.Context
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpersevindik.lifesum.data.model.Food
import com.alpersevindik.lifesum.data.usecase.GetRandomFoodUseCase
import com.squareup.seismic.ShakeDetector
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val randomFoodUseCase: GetRandomFoodUseCase
) : ViewModel(), ShakeDetector.Listener {

    private val _foodLiveData = MutableLiveData<FoodState<Food>>(FoodState.None)
    val foodLiveData: LiveData<FoodState<Food>> = _foodLiveData

    private val shakeDetector = ShakeDetector(this)

    fun getFoodForFirstRunIfNeeded() {
        if (foodLiveData.value == FoodState.None)
            getRandomFood()
    }

    fun getRandomFood() {
        if (foodLiveData.value is FoodState.Completed || foodLiveData.value == FoodState.None) {
            viewModelScope.launch(Dispatchers.IO) {
                _foodLiveData.postValue(FoodState.Loading)
                val food = randomFoodUseCase.getRandomFood()
                if (food == null)
                    _foodLiveData.postValue(FoodState.Failed)
                else
                    _foodLiveData.postValue(FoodState.Completed(food))
            }
        }
    }

    fun startShakeDetector(context: Context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector.start(sensorManager)
    }

    fun stopShakeDetector() {
        shakeDetector.stop()
    }

    sealed class FoodState<out DATA> {
        object None : FoodState<Nothing>()
        object Loading : FoodState<Nothing>()
        data class Completed<out DATA>(val data: DATA) : FoodState<DATA>()
        object Failed : FoodState<Nothing>()
    }

    override fun hearShake() {
        getRandomFood()
    }
}

