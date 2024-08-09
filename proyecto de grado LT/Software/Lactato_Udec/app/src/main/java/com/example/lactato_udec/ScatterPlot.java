package com.example.lactato_udec;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class ScatterPlot {

    private DatabaseHelper dbHelper;
    public ScatterPlot(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public Bitmap createScatterPlotUniti(int width, int height, float[] xData, float[] yData) {
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
        float xMin = 0;
        float xMax = 14;
        float yMin = 0;
        float yMax = 14;
        for (int i = 0; i <= 7; i++) { // 7 intervalos de 2 en 2 de 0 a 14
            // Números del eje X
            float x = plotLeft + i * plotWidth / 7.0f;
            String xLabel = String.format("%.0f", xMin + i * 2);
            canvas.drawText(xLabel, x - 10, plotBottom + 30, paint);

            // Números del eje Y
            float y = plotBottom - i * plotHeight / 7.0f;
            String yLabel = String.format("%.0f", yMin + i * 2);
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
    public Bitmap createScatterPlot(int width, int height, int user_id, float[] xData, float[] yData) {
        saveScatterData(user_id, xData, yData);

        ArrayList<float[]> xDataList = new ArrayList<>();
        ArrayList<float[]> yDataList = new ArrayList<>();

        Cursor cursor = dbHelper.getAllScatterData(user_id);
        while (cursor.moveToNext()) {
            xDataList.add(stringToFloatArray(cursor.getString(0)));
            yDataList.add(stringToFloatArray(cursor.getString(1)));
        }
        cursor.close();

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);

        int padding = 50;
        int plotWidth = width - 2 * padding;
        int plotHeight = height - 2 * padding;
        int plotLeft = padding;
        int plotTop = padding;
        int plotRight = plotLeft + plotWidth;
        int plotBottom = plotTop + plotHeight;

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(plotLeft, plotTop, plotRight, plotBottom, paint);

        paint.setStrokeWidth(4);
        canvas.drawLine(plotLeft, plotBottom, plotRight, plotBottom, paint);
        canvas.drawLine(plotLeft, plotTop, plotLeft, plotBottom, paint);

        paint.setTextSize(20);
        paint.setStyle(Paint.Style.FILL);
        float xMin = 0;
        float xMax = 14;
        float yMin = 0;
        float yMax = 14;
        for (int i = 0; i <= 7; i++) { // 7 intervalos de 2 en 2 de 0 a 14
            // Números del eje X
            float x = plotLeft + i * plotWidth / 7.0f;
            String xLabel = String.format("%.0f", xMin + i * 2);
            canvas.drawText(xLabel, x - 10, plotBottom + 30, paint);

            // Números del eje Y
            float y = plotBottom - i * plotHeight / 7.0f;
            String yLabel = String.format("%.0f", yMin + i * 2);
            canvas.drawText(yLabel, plotLeft - 40, y + 10, paint);
        }

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        int pointRadius = 10;

        for (int j = 0; j < xDataList.size(); j++) {
            float[] xDataArray = xDataList.get(j);
            float[] yDataArray = yDataList.get(j);

            for (int i = 0; i < xDataArray.length; i++) {
                float x = scale(xDataArray[i], xMin, xMax, plotLeft, plotRight);
                float y = scale(yDataArray[i], yMin, yMax, plotBottom, plotTop);
                canvas.drawCircle(x, y, pointRadius, paint);
            }

            paint.setColor(j == xDataList.size() - 1 ? Color.RED : Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);

            Path path = new Path();
            for (int i = 0; i < xDataArray.length; i++) {
                float x = scale(xDataArray[i], xMin, xMax, plotLeft, plotRight);
                float y = scale(yDataArray[i], yMin, yMax, plotBottom, plotTop);
                if (i == 0) {
                    path.moveTo(x, y);
                } else {
                    path.lineTo(x, y);
                }
            }
            canvas.drawPath(path, paint);
        }

        return bitmap;
    }
    private void saveScatterData(int user_id, float[] xData, float[] yData) {
        String xDataString = floatArrayToString(xData);
        String yDataString = floatArrayToString(yData);
        dbHelper.insertScatterData(user_id, xDataString, yDataString);
    }
    private String floatArrayToString(float[] array) {
        StringBuilder sb = new StringBuilder();
        for (float value : array) {
            sb.append(value).append(",");
        }
        return sb.toString();
    }
    private float[] stringToFloatArray(String s) {
        String[] parts = s.split(",");
        float[] array = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Float.parseFloat(parts[i]);
        }
        return array;
    }
    private float scale(float value, float min, float max, float newMin, float newMax) {
        return ((value - min) / (max - min)) * (newMax - newMin) + newMin;
    }
    private float getMin(float[] data) {
        float min = data[0];
        for (float v : data) {
            if (v < min) {
                min = v;
            }
        }
        return min;
    }
    private float getMax(float[] data) {
        float max = data[0];
        for (float v : data) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
