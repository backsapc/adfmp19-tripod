package com.example.tripod

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_image_save.*
import android.view.MotionEvent
import android.view.GestureDetector
import androidx.core.view.GestureDetectorCompat


class ImageSaveActivity : AppCompatActivity(),GestureDetector.OnGestureListener {

    private lateinit var bitmap: Bitmap
    private val SWIPE_THRESHOLD = 100
    private val SWIPE_VELOCITY_THRESHOLD = 100

    var gDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_save)

        this.bitmap = intent.getParcelableExtra("IMAGE")
        this.gDetector = GestureDetectorCompat(this, this)
        imageView.setImageBitmap(bitmap)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.gDetector?.onTouchEvent(event)
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    fun onSwipeRight() {

    }

    fun onSwipeLeft(){

    }

    fun onSwipeTop() {}

    fun onSwipeBottom() {
        onBackPressed()
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent,
                         velocityX: Float, velocityY: Float): Boolean {
        Log.d("TOUCH", "onFling")
        var result = false
        try {
            val diffY = e2.getY() - e1.getY()
            val diffX = e2.getX() - e1.getX()
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight()
                    } else {
                        onSwipeLeft()
                    }
                    result = true
                }
            } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom()
                } else {
                    onSwipeTop()
                }
                result = true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        return result
        return true
    }

    override fun onLongPress(event: MotionEvent) {
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent,
                          distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onShowPress(event: MotionEvent) {
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        return true
    }


}
