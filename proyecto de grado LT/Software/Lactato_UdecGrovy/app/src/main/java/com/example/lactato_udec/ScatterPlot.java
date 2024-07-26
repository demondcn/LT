package com.example.lactato_udec;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class ScatterPlot {

    // Método estático para crear un Bitmap con la gráfica de dispersión
    public static Bitmap createScatterPlot(int width, int height, float[] xData, float[] yData) {
        // Crear un Bitmap con el tamaño especificado
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        // Establecer el color de fondo blanco
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);

        // Dibujar el área de la gráfica en un cuadrado
        int padding = 50;
        int plotWidth = width - 2 * padding;
        int plotHeight = height - 2 * padding;
        int plotLeft = padding;
        int plotTop = padding;
        int plotRight = plotLeft + plotWidth;
        int plotBottom = plotTop + plotHeight;

        // Dibujar el borde de la gráfica
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(plotLeft, plotTop, plotRight, plotBottom, paint);

        // Dibujar los ejes
        paint.setStrokeWidth(4);
        canvas.drawLine(plotLeft, plotBottom, plotRight, plotBottom, paint); // Eje X
        canvas.drawLine(plotLeft, plotTop, plotLeft, plotBottom, paint); // Eje Y

        // Dibujar los números de los ejes
        paint.setTextSize(20);
        paint.setStyle(Paint.Style.FILL);
        float xMin = getMin(xData);
        float xMax = getMax(xData);
        float yMin = getMin(yData);
        float yMax = getMax(yData);
        for (int i = 0; i <= 10; i++) {
            // Números del eje X
            float x = plotLeft + i * plotWidth / 10.0f;
            String xLabel = String.format("%.1f", xMin + i * (xMax - xMin) / 10.0f);
            canvas.drawText(xLabel, x - 20, plotBottom + 30, paint);

            // Números del eje Y
            float y = plotBottom - i * plotHeight / 10.0f;
            String yLabel = String.format("%.1f", yMin + i * (yMax - yMin) / 10.0f);
            canvas.drawText(yLabel, plotLeft - 40, y + 10, paint);
        }

        // Configurar el color y estilo para los puntos de datos
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        int pointRadius = 10;

        // Dibujar los puntos de datos con escalado
        for (int i = 0; i < xData.length; i++) {
            float x = scale(xData[i], xMin, xMax, plotLeft, plotRight);
            float y = scale(yData[i], yMin, yMax, plotBottom, plotTop);
            canvas.drawCircle(x, y, pointRadius, paint);
        }

        // Configurar el color y estilo para la línea de tendencia
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        // Dibujar la línea de tendencia
        Path path = new Path();
        for (int i = 0; i < xData.length; i++) {
            float x = scale(xData[i], xMin, xMax, plotLeft, plotRight);
            float y = scale(yData[i], yMin, yMax, plotBottom, plotTop);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        canvas.drawPath(path, paint);

        return bitmap;
    }

    // Método para escalar un valor dentro de un rango
    private static float scale(float value, float min, float max, float newMin, float newMax) {
        return ((value - min) / (max - min)) * (newMax - newMin) + newMin;
    }

    // Métodos para encontrar el valor mínimo y máximo en un array
    private static float getMin(float[] data) {
        float min = data[0];
        for (float v : data) {
            if (v < min) {
                min = v;
            }
        }
        return min;
    }

    private static float getMax(float[] data) {
        float max = data[0];
        for (float v : data) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
