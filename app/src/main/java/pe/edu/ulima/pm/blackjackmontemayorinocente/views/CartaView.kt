package pe.edu.ulima.pm.blackjackmontemayorinocente.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import pe.edu.ulima.pm.blackjackmontemayorinocente.R

interface OnCardClickListener {
    fun onClick(view: CartaView)
}

class CartaView: View {
    var number : Int? = null
    var paint : Paint? = null
    var width : Int? = null
    var height : Int? = null
    var centreX : Float? = null
    var centreY : Float? = null
    private var listener : OnCardClickListener? = null
    // Creamos un Bitmap a partir del Pingüino
    val img: Bitmap? = BitmapFactory.decodeResource(resources, R.drawable.doge)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // Por xml
        paint = Paint()
    }

    fun setOnCardClickListener(listener: OnCardClickListener?) {
        this.listener = listener
    }

    // Si se toca el View, se llama a la función onClick()
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.listener?.onClick(this) // [ULFaceView : View] -> [OnFaceClickListener]
        return super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Obtenemos las medidas y centro del View
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = View.MeasureSpec.getSize(widthMeasureSpec)
        height = View.MeasureSpec.getSize(heightMeasureSpec)
        centreX=x + width!!/ 2
        centreY=y + height!! / 2
    }

    override fun onDraw(canvas: Canvas) {



        // Si se clickeó, se dibuja el número
        if (number != null) {
            // Color de Fondo
            canvas.drawRGB(255, 102, 102)
            paint!!.color= Color.BLACK
            paint!!.textSize = 55f
            paint!!.style = Paint.Style.FILL
            canvas.drawText(number.toString(), centreX!! - 20, centreY!! + 5, paint!!)
            // Borde de Carta
            val rectf = Rect()
            this.getLocalVisibleRect(rectf)
            paint!!.style = Paint.Style.STROKE
            paint!!.strokeWidth = 5f
            canvas.drawRect(rectf,paint!!)
        }
        // Sino, dibujamos el Pingüino
        else {
            // Color de Fondo
            canvas.drawRGB(255, 153, 61)
            // Escalamos el Pingüino
            val  resized: Bitmap = Bitmap.createScaledBitmap(img!!, width!! / 2 + width!! / 4, width!! / 2 + width!! / 4, true)
            canvas.drawBitmap(resized, centreX!!/2-centreX!!/4, centreY!! - width!! / 3, paint)
        }
    }

}