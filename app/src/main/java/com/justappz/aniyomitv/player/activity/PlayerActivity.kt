package com.justappz.aniyomitv.player.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import com.justappz.aniyomitv.constants.IntentKeys
import com.justappz.aniyomitv.databinding.ActivityPlayerBinding
import com.justappz.aniyomitv.domain.model.streams.StreamsDomain
import com.justappz.aniyomitv.utils.UrlUtils
import com.justappz.aniyomitv.utils.toObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerActivity : AppCompatActivity() {

    //region variables
    private lateinit var binding: ActivityPlayerBinding
    private lateinit var activity: Activity
    private var player: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private var streams: List<StreamsDomain> = arrayListOf()
    private var currentStream: StreamsDomain? = null
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        activity = this
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        val jsonString = intent.getStringExtra(IntentKeys.STREAM_LIST)
        streams = jsonString?.toObject() ?: emptyList()

        playerView = binding.playerView

        init()
    }
    //endregion

    //region onStop
    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
    //endregion

    //region init
    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        currentStream = UrlUtils.getMatchedStream(streams, "mp4upload")
        currentStream?.let {
            val webView = binding.webView
            webView.settings.javaScriptEnabled = true
            webView.settings.domStorageEnabled = true

            webView.webViewClient = object : WebViewClient() {

                // List of ad URL patterns to block
                private val adPatterns = listOf(
                    "ads.", "doubleclick.net", "adservice", "googlesyndication",
                    "banner", "tracker", "analytics"
                )

                override fun shouldInterceptRequest(
                    view: WebView?, request: WebResourceRequest?
                ): WebResourceResponse? {

                    val url = request?.url.toString()

                    // Block ad URLs
                    if (adPatterns.any { url.contains(it, ignoreCase = true) }) {
                        Log.d("PlayerActivity", "Blocked ad URL: $url")
                        // Return empty response to block it
                        return WebResourceResponse("text/plain", "utf-8", null)
                    }

                    if (url.endsWith(".m3u8") || url.endsWith(".mp4")) {
                        val headers = request?.requestHeaders ?: emptyMap()
                        Log.d("PlayerActivity", "URL: $url")
                        Log.d("PlayerActivity", "Headers: $headers")

                        // Play in ExoPlayer
                        playWithExoPlayer(url, headers)

                        // Stop/cancel WebView
                        view?.post {
                            view.stopLoading()
                            view.visibility = View.GONE
                        }
                        // Otherwise, block/decline everything
                        return WebResourceResponse(
                            "text/plain",      // MIME type doesn't matter
                            "utf-8",
                            null               // null input stream = no content
                        )
                    }
                    return super.shouldInterceptRequest(view, request)
                }
            }
            webView.loadUrl(it.sourceUrl ?: "")
        }
    }
    //endregion

    //region playWithExoPlayer
    @androidx.annotation.OptIn(UnstableApi::class)
    private fun playWithExoPlayer(url: String, headers: Map<String, String>) {
        // Create a DataSource.Factory with custom headers
        val dataSourceFactory = DefaultHttpDataSource.Factory()
            .setDefaultRequestProperties(headers)

        // Create a MediaItem
        val mediaItem = MediaItem.fromUri(url)

        // Initialize ExoPlayer
        val exoPlayer = ExoPlayer.Builder(this)
            .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory))
            .build()

        // UI operations must be on the main thread
        runOnUiThread {
            // Bind to PlayerView
            binding.playerView.player = exoPlayer

            // Set media and start playback
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.play()

            binding.playerView.visibility = View.VISIBLE
            binding.progressbarLoading.visibility = View.GONE
        }
    }
    //endregion
}