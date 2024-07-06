
package com.example.lactato_udec;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class prueba_de_carrera_de_pista_activity extends AppCompatActivity {

	
	private View _bg__prueba_de_carrera_de_pista_ek2;
	private ImageView image_6;
	private TextView test_para_carrera_a_pie;
	private TextView etapa_1_aerobica;
	private TextView llena_tus_datos_correspondientes;
	private View rectangle_1;
	private TextView textotipodesuperficie;
	private View tartan;
	private boolean colorOriginalTartan = true;
	private TextView tartant;
	private View ARCILLA;
	private boolean colorOriginalArcilla = true;
	private TextView ARCILLAt;
	private View ASFALTO;
	private boolean colorOriginalAsfalto = true;
	private TextView ASFALTOt;
	private EditText FCLPM;
	private EditText Lactato;
	private EditText Segundos;
	private EditText Minutos;
	private EditText Distancia;
	private TextView guardar;
	private TextView __ya_definiste_tus_datos_;
	private TextView nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques;
	int[][] EtapasIniciales = new int[7][7];
	private int n = 0;
	private int m = 1;
	private int selectedEtapa = 0;
	private View lastSelectedButton;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.prueba_de_carrera_de_pista);
		textotipodesuperficie = (TextView) findViewById(R.id.textotipodesuperficie);
		_bg__prueba_de_carrera_de_pista_ek2 = (View) findViewById(R.id._bg__prueba_de_carrera_de_pista_ek2);
		image_6 = (ImageView) findViewById(R.id.image_6);
		test_para_carrera_a_pie = (TextView) findViewById(R.id.test_para_carrera_a_pie);
		etapa_1_aerobica = (TextView) findViewById(R.id.etapa_1_aerobica);
		llena_tus_datos_correspondientes = (TextView) findViewById(R.id.llena_tus_datos_correspondientes);
		rectangle_1 = (View) findViewById(R.id.rectangle_1);
		tartan = (View) findViewById(R.id.tartan);
		tartant = (TextView) findViewById(R.id.tartant);
		ARCILLA = (View) findViewById(R.id.ARCILLA);
		ARCILLAt = (TextView) findViewById(R.id.ARCILLAt);
		ASFALTO = (View) findViewById(R.id.ASFALTO);
		ASFALTOt = (TextView) findViewById(R.id.ASFALTOt);
		FCLPM = (EditText) findViewById(R.id.FCLPM);
		Lactato = (EditText) findViewById(R.id.Lactato);
		Segundos = (EditText) findViewById(R.id.Segundos);
		Minutos = (EditText) findViewById(R.id.Minutos);
		Distancia = (EditText) findViewById(R.id.Distancia);
		guardar = (TextView) findViewById(R.id.guardar);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques = (TextView) findViewById(R.id.nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques);
	
		
		//custom code goes here
		findViewById(R.id.tartan).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedEtapa = 1;
				manageButtonSelection(v, lastSelectedButton, colorOriginalTartan, R.drawable.rectangle_2_shape, R.drawable.rectangle_3_shape);
				lastSelectedButton = v;
				colorOriginalTartan = !colorOriginalTartan;
			}
		});
		findViewById(R.id.ARCILLA).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedEtapa = 2;
				manageButtonSelection(v, lastSelectedButton, colorOriginalArcilla, R.drawable.rectangle_2_shape, R.drawable.rectangle_3_shape);
				lastSelectedButton = v;
				colorOriginalArcilla = !colorOriginalArcilla;
			}
		});
		findViewById(R.id.ASFALTO).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedEtapa = 3;
				manageButtonSelection(v, lastSelectedButton, colorOriginalAsfalto, R.drawable.rectangle_2_shape, R.drawable.rectangle_3_shape);
				lastSelectedButton = v;
				colorOriginalAsfalto = !colorOriginalAsfalto;
			}
		});
		guardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				n = funcionEtapasIniciales(n);
			}
		});
	}
	public void LimpiarEditext(){
		Distancia.setText("");
		Minutos.setText("");
		Segundos.setText("");
		Lactato.setText("");
		FCLPM.setText("");
	}
	public int funcionEtapasIniciales(int n) {
		if (m < 8) {
			// Validar entradas
			if (Distancia.getText().toString().isEmpty() ||
					Minutos.getText().toString().isEmpty() ||
					Segundos.getText().toString().isEmpty() ||
					Lactato.getText().toString().isEmpty() ||
					(m < 7 && FCLPM.getText().toString().isEmpty())) {
				Toast.makeText(this, "Por favor, ingrese todos los datos válidos", Toast.LENGTH_SHORT).show();
				return n;
			}

			// Guardar datos
			try {
				EtapasIniciales[n][0] = Integer.parseInt(Distancia.getText().toString());
				EtapasIniciales[n][1] = Integer.parseInt(Minutos.getText().toString());
				EtapasIniciales[n][2] = Integer.parseInt(Segundos.getText().toString());
				EtapasIniciales[n][3] = Integer.parseInt(Lactato.getText().toString());
				EtapasIniciales[n][4] = m < 7 ? Integer.parseInt(FCLPM.getText().toString()) : 0;
			} catch (NumberFormatException e) {
				Toast.makeText(this, "Por favor, ingrese datos válidos", Toast.LENGTH_SHORT).show();
				return n;
			}

			m += 1;
			Toast.makeText(this, "en este momento m = " + m, Toast.LENGTH_SHORT).show();
			LimpiarEditext();

			// Actualizar UI
			String mensaje = m < 5 ? "Etapa " + m + " Aerobica" : m == 5 ? "Etapa Anaerobica" : m == 6 ? "Etapa Previa a 4 mmol/l" : m == 7 ? "Etapa Obtencion 4 mmol/l." : "Etapa Final";
			String cambioHintLactamol = (m == 6) || (m == 7) ? "Lact Mmol/l" : "Lactato";
			String mensajeBoton = m == 7? "Ver Resultados":"Guardar";
			guardar.setText(mensajeBoton);
			Lactato.setHint(cambioHintLactamol);
			String cambioHintFclp = (m == 6) ? "Tramo para control de ritmo" : "FCLPM";
			FCLPM.setHint(cambioHintFclp);

			if (m == 7) {
				FCLPM.setVisibility(View.GONE);
				EtapasIniciales[6][4] = 0; // o 9, según tu lógica original
			}

			if (m == 8){
				// Ocultar campos y mostrar mensaje final
				Distancia.setVisibility(View.GONE);
				Minutos.setVisibility(View.GONE);
				Segundos.setVisibility(View.GONE);
				Lactato.setVisibility(View.GONE);
				llena_tus_datos_correspondientes.setVisibility(View.GONE);
				guardar.setVisibility(View.GONE);
				__ya_definiste_tus_datos_.setVisibility(View.GONE);
				nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques.setVisibility(View.GONE);
			}
			etapa_1_aerobica.setText(mensaje);
			textotipodesuperficie.setVisibility(View.GONE);
			tartan.setVisibility(View.GONE);
			tartant.setVisibility(View.GONE);
			ARCILLA.setVisibility(View.GONE);
			ARCILLAt.setVisibility(View.GONE);
			ASFALTO.setVisibility(View.GONE);
			ASFALTOt.setVisibility(View.GONE);
			n += 1;
		}
		if (m == 8){
			generarPdf();
		}
		return n;
	}



	private void manageButtonSelection(View clickedView, View lastSelectedView, boolean colorOriginal, int backgroundOriginal, int backgroundNoOriginal) {
		// Deseleccionar el último botón seleccionado
		if (lastSelectedView != null && lastSelectedView != clickedView) {
			FuncionBotonesSeleccion(lastSelectedView, false, backgroundOriginal, backgroundNoOriginal);
		}

		// Seleccionar el botón actual
		FuncionBotonesSeleccion(clickedView, !colorOriginal, backgroundOriginal, backgroundNoOriginal);
	}
	public void FuncionBotonesSeleccion(View view, boolean colorOriginal, int backgroundOriginal, int backgroundNoOriginal) {
		if (colorOriginal) {
			view.setBackgroundResource(backgroundNoOriginal);
		} else {
			view.setBackgroundResource(backgroundOriginal);
		}
	}
	public void generarPdf(){
		Toast.makeText(this, "se genero un pdf", Toast.LENGTH_LONG).show();
		// Crear un documento PDF
		PdfDocument pdfDocument = new PdfDocument();
		PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create(); // Tamaño A4
		PdfDocument.Page page = pdfDocument.startPage(pageInfo);

		// Obtén el canvas de la página
		Canvas canvas = page.getCanvas();
		Paint paint = new Paint();

		// Configuración del paint para el texto
		paint.setColor(Color.BLACK);
		paint.setTextSize(12);

		// Dibujar el título
		paint.setTextSize(16);
		paint.setFakeBoldText(true);
		canvas.drawText("TEST ESTÁNDAR DE LACTATO PARA CON CARRERA A PIE", 20, 30, paint);

		// Dibujar los subtítulos y textos
		paint.setTextSize(12);
		paint.setFakeBoldText(false);
		canvas.drawText("NOMBRE EXAMINADO (A):", 20, 60, paint);
		// Puedes añadir el nombre del examinado aquí si tienes un EditText para eso
		canvas.drawText("YO", 200, 60, paint);

		// Ejemplo de cómo dibujar una tabla simple
		int startX = 20;
		int startY = 90;
		int cellHeight = 20;
		int cellWidth = 80;

		paint.setTextSize(10);
		// Dibujar las cabeceras de la tabla
		//canvas.drawText("FASES DEL TEST", startX, startY, paint);
		canvas.drawText("ETAPAS", startX, startY, paint);
		canvas.drawText("DIST., M", startX + cellWidth, startY, paint);
		canvas.drawText("MIN", startX + cellWidth * 2, startY, paint);
		canvas.drawText("SEG", startX + cellWidth * 3, startY, paint);
		canvas.drawText("LACTATO", startX + cellWidth * 4, startY, paint);
		canvas.drawText("FCLPM", startX + cellWidth * 5, startY, paint);

		// Dibujar las líneas de la tabla
		paint.setStyle(Paint.Style.STROKE);
		int numRows = 7; // Número de filas, ajusta según tus datos
		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + cellWidth * 6, startY + i * cellHeight, paint);
		}
		for (int i = 0; i <= 6; i++) {
			canvas.drawLine(startX + i * cellWidth, startY, startX + i * cellWidth, startY + numRows * cellHeight, paint);
		}

		// Añadir datos a la tabla
		for (int i = 0; i < numRows; i++) {
			// Ajustando las posiciones para cada dato de la fila
			canvas.drawText(String.valueOf(i + 1), startX, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][0]), startX + cellWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][1]), startX + cellWidth * 2, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][2]), startX + cellWidth * 3, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][3]), startX + cellWidth * 4, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][4]), startX + cellWidth * 5, startY + (i + 1) * cellHeight, paint);
		}

		// Finaliza la página
		pdfDocument.finishPage(page);

		// Guarda el PDF en el almacenamiento externo
		File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test_lactato.pdf");
		try {
			pdfDocument.writeTo(new FileOutputStream(file));
			Toast.makeText(this, "PDF generado con éxito", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Error al generar el PDF", Toast.LENGTH_SHORT).show();
		}

		// Cierra el documento
		pdfDocument.close();
	}
}
	
	