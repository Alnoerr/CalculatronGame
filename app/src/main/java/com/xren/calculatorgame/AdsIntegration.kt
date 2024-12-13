package com.xren.calculatorgame

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowMetrics
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView

//@Composable
//fun AdMobBanner(modifier: Modifier = Modifier) {
//    AndroidView(
//        modifier = modifier.fillMaxWidth(),
//        factory = { context ->
//            AdView(context).apply {
//                setAdSize(getAdSize(context))
//                adUnitId = "ca-app-pub-3940256099942544/9214589741"
//                loadAd(AdRequest.Builder().build())
//            }
//        }
//    )
//}

@Composable
fun YandexBanner(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            BannerAdView(context).apply {
                setAdUnitId("R-M-13247549-1")
                setAdSize(BannerAdSize.stickySize(context, getAdWidth(context)))
//                setBannerAdEventListener(object: BannerAdEventListener {
//                    override fun onAdLoaded() {
//                        // If this callback occurs after the activity is destroyed, you
//                        // must call destroy and return or you may get a memory leak.
//                        // Note `isDestroyed` is a method on Activity.
//                        val activity = context as Activity
//                        if (activity.isDestroyed) {
//                            activity.findViewById<>() .destroy()
//                            return
//                        }
//                    }
//                    override fun onAdClicked() {}
//
//                    override fun onAdFailedToLoad(error: AdRequestError) {}
//
//                    override fun onImpression(impressionData: ImpressionData?) {}
//
//                    override fun onLeftApplication() {}
//
//                    override fun onReturnedToApplication() {}
//                })
                val request = com.yandex.mobile.ads.common.AdRequest.Builder().build()
                loadAd(request)
            }
        }
    )
}

private fun getAdWidth(context: Context): Int {
    val activity = context as Activity
    val displayMetrics = context.resources.displayMetrics
    val adWidthPixels =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = activity.windowManager.currentWindowMetrics
            windowMetrics.bounds.width()
        } else {
            displayMetrics.widthPixels
        }
    val density = displayMetrics.density
    val adWidth = (adWidthPixels / density).toInt()
    return adWidth
}


//// Get the ad size with screen width.
//private fun getAdSize(context: Context): AdSize {
//    return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, getAdWidth(context))
//}
