package ru.geekbrains.car_game.ui.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ru.geekbrains.car_game.R

class CarView : View {

    private var carColor = Color.RED
    private var wheelsColor = Color.BLACK
    private var carRectangle = RectF()
    private var wheel1Rectangle = RectF()
    private var wheel2Rectangle = RectF()
    private var wheel3Rectangle = RectF()
    private var wheel4Rectangle = RectF()
    private lateinit var carPaint: Paint
    private lateinit var wheelsPaint: Paint
    private var carWidth = 0
    private var carHeight = 0


    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initAttr(context, attrs)
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initAttr(context, attrs)
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttr(context, attrs)
        init()
    }

    private fun initAttr(
        context: Context,
        attrs: AttributeSet?
    ) {
        with(context.obtainStyledAttributes(attrs, R.styleable.CarView, 0, 0)) {

            carColor =
                getColor(R.styleable.CarView_car_color, Color.RED)
            wheelsColor =
                getColor(R.styleable.CarView_wheels_color, Color.BLACK)
            recycle()
        }
    }

    private fun init() {
        carPaint = Paint().run {
            color = carColor
            style = Paint.Style.FILL
            this
        }

        wheelsPaint = Paint().run {
            color = wheelsColor
            style = Paint.Style.FILL
            this
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        carWidth = w - paddingLeft - paddingRight
        carHeight = h - paddingTop - paddingBottom

        val wheelWidth = carWidth / 6
        val wheelHeight = carHeight / 6

        carRectangle = RectF(0f, wheelHeight.toFloat(), carWidth.toFloat(),(carHeight - wheelHeight).toFloat())
        wheel1Rectangle = RectF((carWidth/6).toFloat(), 0f, (carWidth - (carWidth/6*4)).toFloat(), wheelHeight.toFloat() )
        wheel2Rectangle = RectF((carWidth/6*4).toFloat(), 0f, (carWidth - (carWidth/6)).toFloat(), wheelHeight.toFloat() )
        wheel3Rectangle = RectF((carWidth/6).toFloat(), (height - wheelHeight).toFloat(), (carWidth - (carWidth/6*4)).toFloat(), carHeight.toFloat() )
        wheel4Rectangle = RectF((carWidth/6*4).toFloat(), (height - wheelHeight).toFloat(), (carWidth - (carWidth/6)).toFloat(), carHeight.toFloat()  )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            drawRect(carRectangle, carPaint)
            drawRect(wheel1Rectangle, wheelsPaint)
            drawRect(wheel2Rectangle, wheelsPaint)
            drawRect(wheel3Rectangle, wheelsPaint)
            drawRect(wheel4Rectangle, wheelsPaint)
        }
    }

}