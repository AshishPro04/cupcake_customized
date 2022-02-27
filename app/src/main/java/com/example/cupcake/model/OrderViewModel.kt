package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00
private const val PRICE_FOR_ITALIAN_TWIST = 3.50


class OrderViewModel: ViewModel() {
    private val _orderQuantity: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val orderQuantity: LiveData<Int> = _orderQuantity

    private val _flavour: MutableLiveData<String> = MutableLiveData("")
    val flavor: LiveData<String> = _flavour

    private val _pickupDate: MutableLiveData<String> = MutableLiveData("")
    val pickupDate: LiveData<String> = _pickupDate

    private val _price: MutableLiveData<Double> = MutableLiveData()
    val price: LiveData<Double> = _price

    private val _pricePerItem : MutableLiveData<Double> = MutableLiveData()
    var pricePerItem: Double  = PRICE_PER_CUPCAKE

    private val _name: MutableLiveData<String> = MutableLiveData()
    var name: LiveData<String> = _name

    val dateOptions = getPickupOptions()

    init {
        resetOrder()
    }

    fun setQuantity(quantity: Int) {
        _orderQuantity.value = quantity
        updatePrice()
    }

    fun setFlavor(flavor: String,) {
        pricePerItem = PRICE_PER_CUPCAKE
        _flavour.value = flavor
        updatePrice()
    }
    fun setPickupDate(date: String) {
        _pickupDate.value = date
        updatePrice()
    }
    fun hasNoFlavorSet(): Boolean {
        return _flavour.value.isNullOrEmpty()
    }
    fun setName(iName: String) {
        _name.value = iName
    }
    fun getPickupOptions(): List<String> {
        val options= mutableListOf<String>()
        val formatter = SimpleDateFormat("EEE MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        _orderQuantity.value = 0
        _flavour.value = ""
        _pickupDate.value = dateOptions[0]
        _price.value = 0.0
    }

    fun addItalianTwist(flavor: String) {
        pricePerItem = PRICE_FOR_ITALIAN_TWIST
        _flavour.value = flavor
        updatePrice()
    }


    private fun updatePrice() {
        var calculatedPrice = (_orderQuantity.value ?: 0) * pricePerItem
        if (dateOptions[0] == _pickupDate.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }
}