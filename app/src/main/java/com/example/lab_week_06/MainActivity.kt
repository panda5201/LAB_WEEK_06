package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.appcompat.app.AlertDialog
import com.example.lab_week_06.model.CatAdapter
import com.example.lab_week_06.model.GlideImageLoader
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender
import com.example.lab_week_06.model.CatBreed

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        // Glide is used here to load the images
        // Here we are passing the onClickListener function to the Adapter
        CatAdapter(layoutInflater, GlideImageLoader(this), object :
            CatAdapter.OnClickListener {
            // When this is triggered, the pop up dialog will be shown
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // Tambahkan ItemTouchHelper di sini (setelah adapter siap)
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Misal: hapus item saat diswipe
                val position = viewHolder.adapterPosition
                // (Kamu bisa buat fungsi delete di adapter)
                // catAdapter.removeAt(position)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

        catAdapter.setData(
            listOf(
                CatModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/7dj.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/egv.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/bar.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.ExoticShorthair,
                    "Shadow",
                    "Always hides in the dark corner but loves treats.",
                    "https://cdn2.thecatapi.com/images/MTY3ODIyMQ.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.BalineseJavanese,
                    "Luna",
                    "Graceful and loves to sleep near sunlight.",
                    "https://cdn2.thecatapi.com/images/9r9.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.AmericanCurl,
                    "Leo",
                    "The king of the house, but afraid of cucumbers.",
                    "https://cdn2.thecatapi.com/images/c8h.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Cleo",
                    "Always curious about new guests.",
                    "https://cdn2.thecatapi.com/images/2oo.jpg"
                ),
                CatModel(
                    Gender.Male,
                    CatBreed.AmericanCurl,
                    "Tom",
                    "Lazy but very photogenic.",
                    "https://cdn2.thecatapi.com/images/bpc.jpg"
                ),
                CatModel(
                    Gender.Unknown,
                    CatBreed.BalineseJavanese,
                    "Mochi",
                    "Loves to chase laser lights.",
                    "https://cdn2.thecatapi.com/images/8v5.jpg"
                ),
                CatModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Nala",
                    "Friendly and loves belly rubs.",
                    "https://cdn2.thecatapi.com/images/dcl.jpg"
                )
            )
        )

    }

    // This will create a pop up dialog when one of the items from the recycler view is clicked.
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
