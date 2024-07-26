
package com.example.lactato_udec;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.text.DecimalFormat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
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
	private int n = 0;
	private int m = 1;
	double[][] EtapasIniciales = new double[100][100];

	private int selectedEtapa = 0;
	private View lastSelectedButton;
	private int userId;
	private DatabaseHelper db;
	private String userName;
	private String userFecha;
	private String userEdad;
	private String userPeso;
	private String userTemperatura;
	private String userGenero;
	private String userPeriodo;
	private String userEvent;
	private int pre4mol;
	private int Moment4mols;
	private int maxiEtapes;
	private int momentoAnaerobico;
	private boolean Anaerobica = false;
	private boolean Aerobico = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.prueba_de_carrera_de_pista);
		db = new DatabaseHelper(this);
		Intent intent = getIntent();
		if (intent != null && intent.hasExtra("userId")) {
			userId = intent.getIntExtra("userId", -1);
			userName = db.getDatos(userId,1);
			userFecha = db.getDatos(userId,2);
            userEdad = db.getDatos(userId,3);
			userPeso = db.getDatos(userId,4);
			userTemperatura = db.getDatos(userId,5);
			userGenero = db.getDatos(userId,6);
			userPeriodo = db.getDatos(userId,7);
			userEvent = db.getDatos(userId,8);
		} else {
			userId = -1; // Asigna un valor por defecto o maneja el caso según tu lógica
			userName = "Usuario desconocido";
		}

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
		try {
				if (m < 2) {
					EtapasIniciales[n][0] = Double.parseDouble(Distancia.getText().toString());
				} else {
					EtapasIniciales[n][0] = EtapasIniciales[0][0];
				}
				EtapasIniciales[n][1] = Double.parseDouble(Minutos.getText().toString());
				EtapasIniciales[n][2] = Double.parseDouble(Segundos.getText().toString());
				EtapasIniciales[n][3] = Double.parseDouble(Lactato.getText().toString());
				if (Anaerobica){
					EtapasIniciales[n][4] = EtapasIniciales[n-1][4];
				} else {
					EtapasIniciales[n][4] = Double.parseDouble(FCLPM.getText().toString());
				}

		} catch (NumberFormatException e) {
			Toast.makeText(this, "Por favor, ingrese datos válidos " + EtapasIniciales[n][4], Toast.LENGTH_SHORT).show();
			return n;
		}
		if (Aerobico){
			// Guardar datos
			m += 1;
			Distancia.setVisibility(View.GONE);
			if (EtapasIniciales[n][3] < 4){
				etapa_1_aerobica.setText("Etapa " + m + " Aerobica");
				Aerobico = true;
			}
			else {
				etapa_1_aerobica.setText("Etapa Anaerobica");
				FCLPM.setVisibility(View.GONE);
				Anaerobica = true;
				Aerobico = false;
			}

		} else {
			//guardardatosAna
			momentoAnaerobico = n;
			Moment4mols = n-1;
			pre4mol = Moment4mols -1;
			maxiEtapes = m;
			generarPdf();
			finish();
		}
		textotipodesuperficie.setVisibility(View.GONE);
		tartan.setVisibility(View.GONE);
		tartant.setVisibility(View.GONE);
		ARCILLA.setVisibility(View.GONE);
		ARCILLAt.setVisibility(View.GONE);
		ASFALTO.setVisibility(View.GONE);
		ASFALTOt.setVisibility(View.GONE);
		n += 1;
		LimpiarEditext();
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
		canvas.drawText(userName, 200, 60, paint);
		canvas.drawText("FECHA MEDICIÓN (DD/MM/AA):", 20, 80, paint);
		canvas.drawText(userFecha, 200, 80, paint);
		canvas.drawText("GÉNERO (M=1/F=2):", 20, 100, paint);
		canvas.drawText(userGenero, 200, 100, paint);
		canvas.drawText("PESO CORP., KG.:", 20, 120, paint);
		canvas.drawText(userPeso, 200, 120, paint);
		canvas.drawText("EDAD, AÑOS:", 20, 140, paint);
		canvas.drawText(userEdad, 200, 140, paint);

		canvas.drawText("NOTA: EL CALENTAMIENTO NO PUEDE SER DE MÁS DE 15 MIN. NI INCLUYENDO PIQUES", 20, 160, paint);
		canvas.drawText("TEMPERATURA GRADOS C (LACTATE SCOUT)", 20, 180, paint);
		canvas.drawText(userTemperatura+"°", 400, 180, paint);

		canvas.drawText("PERIODO; ETAPA:", 20, 200, paint);
		String txtPeriodo = userPeriodo.equals("1") ? "PERIODO PREPARATORIO": userPeriodo.equals("2") ? "ETAPA PRECOMPETITIVA": userPeriodo.equals("3") ? "ETAPA COMPETITIVA":"valor no existente";
		canvas.drawText(txtPeriodo, 200, 200, paint);
		String txtDeporte = userEvent.equals("1") ? "RESISTENCIA": userEvent.equals("2") ? "PELOTAS": userEvent.equals("3") ? "COMBATE": userEvent.equals("4") ? "VELOC., FZA RAP., ANAEROBICO":"valor no existente";
		canvas.drawText("DEPORTE / EVENTO:", 20, 220, paint);
		canvas.drawText(txtDeporte, 200, 220, paint);
		canvas.drawText("TIPO DE SUPERFICIE:", 20, 240, paint);
		String txtSuperficie = selectedEtapa == 1? "TARTAN" : selectedEtapa == 2? "ARCILLa" : selectedEtapa == 3? "ASFALTO":"dato no generado";
		canvas.drawText(txtSuperficie, 200, 240, paint);

		// Dibujar las cabeceras de la tabla
		int startX = 20;
		int startY = 270;
		int cellHeight = 20;
		int cellWidth = 60;

		paint.setTextSize(10);
		paint.setFakeBoldText(true);
		canvas.drawText("ETAPAS", startX, startY, paint);
		canvas.drawText("DIST., M", startX + cellWidth, startY, paint);
		canvas.drawText("MIN", startX + cellWidth * 2, startY, paint);
		canvas.drawText("SEG", startX + cellWidth * 3, startY, paint);
		canvas.drawText("LACTATO", startX + cellWidth * 4, startY, paint);
		canvas.drawText("FCLPM", startX + cellWidth * 5, startY, paint);
		canvas.drawText("VELOC.M/S", startX + cellWidth * 6, startY, paint);

		// Dibujar las líneas de la tabla

		//paint.setStyle(Paint.Style.STROKE);
		int numRows = maxiEtapes; // Número de filas, ajusta según tus datos
		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + cellWidth * 7, startY + i * cellHeight, paint);
		}
		for (int i = 0; i <= 7; i++) {
			canvas.drawLine(startX + i * cellWidth, startY, startX + i * cellWidth, startY + numRows * cellHeight, paint);
		}
		float[] xDataV = new float[numRows];
		float[] yDataL = new float[numRows];
		// Añadir datos a la tabla
		for (int i = 0; i < numRows; i++) {
			// Ajustando las posiciones para cada dato de la fila
			String txtOrdenEtapa = i == maxiEtapes-1 ? "Anaerovica" : "Aerobica";
			canvas.drawText(String.valueOf(txtOrdenEtapa), startX, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][0]), startX + cellWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][1]), startX + cellWidth * 2, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][2]), startX + cellWidth * 3, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][3]), startX + cellWidth * 4, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(EtapasIniciales[i][4]), startX + cellWidth * 5, startY + (i + 1) * cellHeight, paint);
			// Calcular y dibujar velocidad
			double distancia = EtapasIniciales[i][0];
			double minutos = EtapasIniciales[i][1];
			double segundos = EtapasIniciales[i][2];
			double velocidad = distancia / (minutos * 60 + segundos);
			xDataV[i] = Float.parseFloat(String.format("%.2f", velocidad));
			yDataL[i] = Float.parseFloat(String.format("%.2f", EtapasIniciales[i][3]));
			canvas.drawText(String.format("%.2f", velocidad), startX + cellWidth * 6, startY + (i + 1) * cellHeight, paint);
		}

		startY += (numRows + 2) * cellHeight; // Dejar espacio después de la tabla

		// Cabecera de resultados
		paint.setFakeBoldText(true);
		canvas.drawText("RESULTADOS:", startX, startY, paint);
		startY += cellHeight;
		paint.setFakeBoldText(false);
		canvas.drawText("UMBRAL LACTICO (VELOCIDAD Y RITMO A 4 MMOL/L):", startX, startY, paint);
		startY += cellHeight * 2; // Espacio adicional después del título de "UMBRAL LACTICO"

		// Definir nuevo ancho para la primera columna de la segunda tabla
		int firstColumnWidth = 140; // Ancho más amplio para la primera columna

		// Cabeceras de la segunda tabla
		paint.setFakeBoldText(true);
		canvas.drawText("", startX, startY, paint);
		canvas.drawText("ACTUAL", startX + firstColumnWidth, startY, paint);
		canvas.drawText("MARATON", startX + firstColumnWidth + cellWidth, startY, paint);

		// Ajustar cabeceras en dos líneas
		canvas.drawText("TRIATLETA", startX + firstColumnWidth + cellWidth * 2, startY - 5, paint); // Primera línea
		canvas.drawText("ELITE", startX + firstColumnWidth + cellWidth * 2, startY + 10, paint); // Segunda línea
		canvas.drawText("TRIATLETA", startX + firstColumnWidth + cellWidth * 3, startY - 5, paint); // Primera línea
		canvas.drawText("NO ELITE", startX + firstColumnWidth + cellWidth * 3, startY + 10, paint); // Segunda línea
		canvas.drawText("CORREDOR", startX + firstColumnWidth + cellWidth * 4, startY - 5, paint); // Primera línea
		canvas.drawText("RECREATIVO", startX + firstColumnWidth + cellWidth * 4, startY + 10, paint); // Segunda línea

		startY += cellHeight;

		// Dibujar las líneas de la segunda tabla

		//paint.setStyle(Paint.Style.STROKE);

		int numRowse = 5;
		for (int i = 0; i <= numRowse; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + firstColumnWidth + cellWidth * 5, startY + i * cellHeight, paint);
		}
		canvas.drawLine(startX, startY, startX, startY + numRowse * cellHeight, paint); // Primera línea vertical (izquierda)
		canvas.drawLine(startX + firstColumnWidth, startY, startX + firstColumnWidth, startY + numRowse * cellHeight, paint); // Línea de la primera columna con ancho ajustado
		for (int i = 1; i <= 5; i++) {
			canvas.drawLine(startX + firstColumnWidth + i * cellWidth, startY, startX + firstColumnWidth + i * cellWidth, startY + numRowse * cellHeight, paint);
		}
		//matecalculos
		double VPre = EtapasIniciales[pre4mol][0] / (EtapasIniciales[pre4mol][1] * 60 + EtapasIniciales[pre4mol][2]);
		double VPost =  EtapasIniciales[Moment4mols][0] / (EtapasIniciales[Moment4mols][1] * 60 + EtapasIniciales[Moment4mols][2]);
		double V4p= VPre + (VPost-VPre)*( (4 - EtapasIniciales[pre4mol][3]) /(EtapasIniciales[Moment4mols][3]-EtapasIniciales[pre4mol][3]));
		// Formatear V4 a dos decimales
		double V4kmp = V4p*3.6;
		double V4mphp = V4p*2.2374;
		double supRitmo = 1000/V4p;
		int RitmonKMMin = (int) (supRitmo/60);
		double RitmonKMSp = supRitmo - (RitmonKMMin*60);
		DecimalFormat df = new DecimalFormat("#0.00");
		String V4 = df.format(V4p);
		String V4km = df.format(V4kmp);
		String V4mph = df.format(V4mphp);
		String RitmonKMS = df.format(RitmonKMSp);


		Object[][] resultados = {
				{"V4,m/s."                 ,V4   ,6              ,5.5        ,4.4     ,4.15},
				{"V4,km/h."                ,V4km    ,21.60          ,19.80      ,15.84   ,14.94},
				{"V4;mph"                  ,V4mph    ,13.42          ,12.31      ,9.84    ,9.29},
				{"RITMO/KM , DE V4, MIN."  ,RitmonKMMin    ,2	           ,3	       ,3	    ,4},
				{"RITMO/KM , DE V4, S."    ,RitmonKMS    ,3              ,4          ,5       ,6}
		};


		// Añadir datos a la segunda tabla
		for (int i = 0; i < numRowse; i++) {
			// Ajustando las posiciones para cada dato de la fila
			canvas.drawText(String.valueOf(resultados[i][0]), startX, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][1]), startX + firstColumnWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][2]), startX + firstColumnWidth + cellWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][3]), startX + firstColumnWidth + cellWidth * 2, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][4]), startX + firstColumnWidth + cellWidth * 3, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][5]), startX + firstColumnWidth + cellWidth * 4, startY + (i + 1) * cellHeight, paint);
		}
		startY += cellHeight * (numRowse + 1); // Espacio adicional después de la tabla
		paint.setFakeBoldText(true);

		// Asumiendo que resultados es un array de arrays de Object
		double valor2 = ((Number) resultados[0][2]).doubleValue();
		double valor3 = ((Number) resultados[0][3]).doubleValue();
		double valor4 = ((Number) resultados[0][4]).doubleValue();
		double valor5 = ((Number) resultados[0][5]).doubleValue();
		String TextoResultante = V4p > valor2 ? "SUPERIOR A MARATONISTA" : V4p > valor3 ? "SUPERIOR A TRIATLETA ELITE" : V4p > valor4 ? "SUPERIOR A TRIATLETA NO ELITE" : V4p > valor5 ? "SUPERIOR A CORREDOR RECREATIVO" : "POR DEBAJO DE CORREDOR RECREATIVO";

		canvas.drawText("VALORACION CAP. AEROBICA: " + TextoResultante, startX, startY, paint);
		startY += cellHeight;

		//lac4/(min4*60+s4)
		// Obtener el valor numérico
		double valorNumerico = (double) EtapasIniciales[momentoAnaerobico][3] / (EtapasIniciales[momentoAnaerobico][1] * 60 + EtapasIniciales[momentoAnaerobico][2]);
		// Formatear el valor a 5 decimales
		DecimalFormat df2 = new DecimalFormat("#.#####");
		String RitmoMaxProdLac = df2.format(valorNumerico);
		canvas.drawText("RITMO MAXIMO DE PRODUCCION DE LACTATO (VLaMax.), MMOL/L./S.: "+ RitmoMaxProdLac, startX, startY, paint);
		// Finaliza la página
		pdfDocument.finishPage(page);
        //donde iria la grafica

		// Crear la segunda página para la gráfica
		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 2).create(); // Nueva página
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		// Crear la gráfica
		Bitmap scatterPlotBitmap = ScatterPlot.createScatterPlot(595, 842, xDataV, yDataL);
		// Dibujar la gráfica en el PDF
		canvas.drawBitmap(scatterPlotBitmap, 0, 0, null);
		// Finaliza la página
		pdfDocument.finishPage(page);

		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 3).create();
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		// Start drawing content

		// Title
		canvas.drawText("PRESCRIPCIÓN DE INTENSIDAD DE ENTRENAMIENTO DE RESISTENCIA (BASADA EN RECOMENDACIÓN DE OLBRECHT):", 10, 25, paint);

		// Aerobic and Anaerobic labels
		canvas.drawText("TRAMO PARA CONTROL DE RITMO, M. =", 10, 50, paint);
		canvas.drawText("400", 300, 50, paint);
		canvas.drawText("AERÓBICO", 400, 50, paint);
		canvas.drawText("ANAERÓBICO", 500, 50, paint);

		// Section for athletes training without issues
		canvas.drawText("PARA ATLETAS QUE ESTÁN ENTRENANDO SIN PROBLEMAS", 10, 75, paint);

		// Table headers
		canvas.drawText("DISTANCIA, M.", 10, 100, paint);
		canvas.drawText("MIN.", 100, 100, paint);
		canvas.drawText("S.", 150, 100, paint);
		canvas.drawText("% de V4", 200, 100, paint);

		// Data for athletes training without issues
		canvas.drawText("REGEN.", 10, 125, paint);
		canvas.drawText("400", 100, 125, paint);
		canvas.drawText("120", 150, 125, paint);
		canvas.drawText("86", 200, 125, paint);

		canvas.drawText("CONT.EXT.", 10, 150, paint);
		canvas.drawText("2000", 100, 150, paint);
		canvas.drawText("480", 150, 150, paint);
		canvas.drawText("91", 200, 150, paint);

		canvas.drawText("CONT.INT.", 10, 175, paint);
		canvas.drawText("800", 100, 175, paint);
		canvas.drawText("216", 150, 175, paint);
		canvas.drawText("107", 200, 175, paint);

		canvas.drawText("TEMPO", 10, 200, paint);
		canvas.drawText("400", 100, 200, paint);
		canvas.drawText("108", 150, 200, paint);
		canvas.drawText("119", 200, 200, paint);

		canvas.drawText("FARTLEK", 10, 225, paint);
		canvas.drawText("200", 100, 225, paint);
		canvas.drawText("54", 150, 225, paint);
		canvas.drawText("119", 200, 225, paint);

		canvas.drawText("INT.", 10, 250, paint);
		canvas.drawText("200", 100, 250, paint);
		canvas.drawText("50", 150, 250, paint);
		canvas.drawText("119", 200, 250, paint);

		canvas.drawText("INT. INT.", 10, 275, paint);
		canvas.drawText("100", 100, 275, paint);
		canvas.drawText("21", 150, 275, paint);
		canvas.drawText("124", 200, 275, paint);

		// Section for athletes who have been injured or not training
		canvas.drawText("PARA ATLETAS QUE HAN ESTADO LESIONADOS O SIN ENTRENAR", 10, 300, paint);

		// Data for athletes who have been injured or not training
		canvas.drawText("REGEN.", 10, 325, paint);
		canvas.drawText("400", 100, 325, paint);
		canvas.drawText("132", 150, 325, paint);
		canvas.drawText("83", 200, 325, paint);

		canvas.drawText("CONT.EXT.", 10, 350, paint);
		canvas.drawText("1600", 100, 350, paint);
		canvas.drawText("528", 150, 350, paint);
		canvas.drawText("90", 200, 350, paint);

		canvas.drawText("CONT.INT.", 10, 375, paint);
		canvas.drawText("600", 100, 375, paint);
		canvas.drawText("204", 150, 375, paint);
		canvas.drawText("97", 200, 375, paint);

		canvas.drawText("TEMPO", 10, 400, paint);
		canvas.drawText("400", 100, 400, paint);
		canvas.drawText("124", 150, 400, paint);
		canvas.drawText("106", 200, 400, paint);

		canvas.drawText("FARTLEK", 10, 425, paint);
		canvas.drawText("400", 100, 425, paint);
		canvas.drawText("124", 150, 425, paint);
		canvas.drawText("106", 200, 425, paint);

		canvas.drawText("INT.", 10, 450, paint);
		canvas.drawText("200", 100, 450, paint);
		canvas.drawText("62", 150, 450, paint);
		canvas.drawText("116", 200, 450, paint);

		canvas.drawText("INT. INT.", 10, 475, paint);
		canvas.drawText("100", 100, 475, paint);
		canvas.drawText("21", 150, 475, paint);
		canvas.drawText("121", 200, 475, paint);

		// Comments section
		canvas.drawText("COMENTARIOS:", 10, 500, paint);
		canvas.drawText("* Del periodo preparatorio a la etapa precompetitiva, en deportes de resistencia aerobia debe enfatizarse en entrenamientos", 10, 525, paint);
		canvas.drawText("continuo extensivo y cercano al umbral láctico para elevar la V4 o umbral láctico (aunque esto deprime el ritmo máximo de producción", 10, 550, paint);
		canvas.drawText("de lactato)", 10, 575, paint);

		canvas.drawText("* Del periodo preparatorio a la etapa precompetitiva, en deportes cuya competencia dura menos de 8 minutos debe enfatizarse en", 10, 600, paint);
		canvas.drawText("entrenamientos de sprints y de intervalos de elevada intensidad para elevar la ritmo máximo de producción de lactato (aunque esto", 10, 625, paint);
		canvas.drawText("disminuye la V4 o umbral láctico)", 10, 650, paint);

		// Finish the first page
		pdfDocument.finishPage(page);

		// Guarda el PDF en el almacenamiento externo
		String fileName = "Informe_Lactato_CarreraDePista.pdf";
		File filePath = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
		try {
			pdfDocument.writeTo(new FileOutputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Error al guardar el PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
			return;
		}

		pdfDocument.close();
		Toast.makeText(this, "PDF guardado en " + filePath.getAbsolutePath(), Toast.LENGTH_LONG).show();

		// Mostrar el PDF automáticamente
		Uri pdfUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", filePath);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(pdfUri, "application/pdf");
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "No hay aplicación para ver PDF instalada", Toast.LENGTH_LONG).show();
		}
	}


}
	
	