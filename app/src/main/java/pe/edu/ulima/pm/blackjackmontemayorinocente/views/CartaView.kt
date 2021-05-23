package pe.edu.ulima.pm.blackjackmontemayorinocente.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import pe.edu.ulima.pm.blackjackmontemayorinocente.R

class CartaView: View {
    val paint : Paint? = null
    var width : Int? = null
    var height : Int? = null
    val location = IntArray(2)
    var position : Unit? = null
    var xPosition : Int? = null
    var yPosition : Int? = null
    var centreX : Float? = null
    var centreY : Float? = null
    // Creamos un Bitmap a partir del Pingüino
    val img: Bitmap? = BitmapFactory.decodeResource(resources, R.drawable.pinguino)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // Por xml
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = View.MeasureSpec.getSize(widthMeasureSpec)
        height = View.MeasureSpec.getSize(heightMeasureSpec)
        centreX=x + width!!/ 2
        centreY=y + height!! / 2
        /*position = this.getLocationOnScreen(location)
        xPosition = location[0]
        yPosition = location[1]*/
    }

    override fun onDraw(canvas: Canvas) {
        // Escalamos el pingüino y obtenemos el centro del Canvas
        val  resized: Bitmap = Bitmap.createScaledBitmap(img!!, width!! / 2, width!! / 2, true)


        // Color de Fondo
        canvas.drawRGB(255, 153, 61)
        // Dibujamos el Pingüino
        canvas.drawBitmap(resized, centreX!!/2, centreY!!-width!!/4, paint)
        invalidate()
    }

}