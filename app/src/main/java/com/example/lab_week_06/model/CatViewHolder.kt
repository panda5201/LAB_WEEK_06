package com.example.lab_week_06.model

import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.R

private val FEMALE_SYMBOL = "\u2640"
private val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: CatAdapter.OnClickListener
) : RecyclerView.ViewHolder(containerView) {

    private val catBiographyView: TextView by lazy {
        containerView.findViewById<TextView>(R.id.cat_biography)
    }
    private val catBreedView: TextView by lazy {
        containerView.findViewById<TextView>(R.id.cat_breed)
    }
    private val catGenderView: TextView by lazy {
        containerView.findViewById<TextView>(R.id.cat_gender)
    }
    private val catNameView: TextView by lazy {
        containerView.findViewById<TextView>(R.id.cat_name)
    }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById<ImageView>(R.id.cat_photo)
    }

    fun bindData(cat: CatModel) {
        // Klik item → panggil callback di adapter
        containerView.setOnClickListener {
            onClickListener.onItemClick(cat)
        }

        // Tampilkan data kucing
        imageLoader.loadImage(cat.imageUrl, catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
            else -> "Unknown"
        }
        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }
}
