package com.example.helloglide2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    val cryptocurrencies = listOf(
        Pair("Bitcoin", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Bitcoin_logo.svg/200px-Bitcoin_logo.svg.png"),
        Pair("Ethereum", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Ethereum_logo_2014.svg/200px-Ethereum_logo_2014.svg.png"),
        Pair("Litecoin", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/6_Full_Logo_S-2.png/320px-6_Full_Logo_S-2.png"),
        Pair("Bitcoin Cash", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Bitcoin_Cash.png/287px-Bitcoin_Cash.png"),
        Pair("Monero", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Monero-Logo.svg/200px-Monero-Logo.svg.png"),
        Pair("Ripple", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Ripple_logo.svg/200px-Ripple_logo.svg.png"),
        Pair("Zcash", "https://upload.wikimedia.org/wikipedia/en/thumb/9/99/Zcash_logo_2019.svg/320px-Zcash_logo_2019.svg.png"),
        Pair("Stellar", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Stellar_Symbol.png/284px-Stellar_Symbol.png"),
        Pair("Tether", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Tether_Logo.svg/200px-Tether_Logo.svg.png"),
        Pair("Dash", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Dash_logo_2018_rgb_for_screens.png/320px-Dash_logo_2018_rgb_for_screens.png")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CryptocurrencyAdapter(cryptocurrencies)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Integration with RecyclerView
        val imageWidth = 50
        val imageHeight = 80
        val sizeProvider = FixedPreloadSizeProvider<Any>(imageWidth, imageHeight)
        val modelProvider = ImagePreloadModelProvider(cryptocurrencies, this, imageWidth, imageHeight)
        val preloader = RecyclerViewPreloader(Glide.with(this), modelProvider, sizeProvider, 3)

        recyclerView.addOnScrollListener(preloader)
    }
}
