package com.fransiska.drakor.data
import com.fransiska.drakor.R
import com.fransiska.drakor.model.Drama

object DramaDatasource {
    fun loadPopularDramas(): List<Drama> {
        return listOf(
            Drama(R.string.film_title_1, R.string.film_description_1, R.drawable.image1),
            Drama(R.string.film_title_2, R.string.film_description_2, R.drawable.image2),
            Drama(R.string.film_title_3, R.string.film_description_3, R.drawable.image3),
            Drama(R.string.film_title_4, R.string.film_description_4, R.drawable.image4)
        )
    }
}
