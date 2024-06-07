package com.dayeeen.herbalyze.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.dayeeen.herbalyze.R

class CustomButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    private var txtColor: Int = 0
    private var enabledBackground: Drawable
    private var disabledBackground: Drawable

    init {
        // Memuat atribut warna teks dari XML layout
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton)
        txtColor = typedArray.getColor(R.styleable.CustomButton_android_textColor, ContextCompat.getColor(context, android.R.color.background_light))
        typedArray.recycle()

        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button) as Drawable
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disable) as Drawable
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        background = if(isEnabled) enabledBackground else disabledBackground
        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
    }
}
