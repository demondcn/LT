
package com.example.lactato_udec;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import java.text.DecimalFormat;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
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

public class prueba_de_natacion_activity extends AppCompatActivity {
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
	private EditText RTC;
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
	private ScatterPlot scatterPlot;
	//agregage de textViewHints
	private TextView hintTextView;
	private TextView hintTextViewFCLPM;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.prueba_de_natacion);
		scatterPlot = new ScatterPlot(this);
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
		FCLPM = (EditText) findViewById(R.id.FCLPM);
		Lactato = (EditText) findViewById(R.id.Lactato);
		Segundos = (EditText) findViewById(R.id.Segundos);
		Minutos = (EditText) findViewById(R.id.Minutos);
		Distancia = (EditText) findViewById(R.id.Distancia);
		RTC = (EditText) findViewById(R.id.RTC);
		guardar = (TextView) findViewById(R.id.guardar);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques = (TextView) findViewById(R.id.nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques);
		RTC.setVisibility(View.GONE);
		hintTextView = (TextView) findViewById(R.id.hintTextView);
		hintTextViewFCLPM = (TextView) findViewById(R.id.hintTextViewFCLPM);
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

		guardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(prueba_de_natacion_activity.this)
						.setTitle("Confirmación")
						.setMessage("¿Está seguro de guardar estos datos?")
						.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// Ejecutar la función para guardar los datos
								n = funcionEtapasIniciales(n);

								// Mostrar mensaje de confirmación
								Toast.makeText(prueba_de_natacion_activity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("No", null) // No hacer nada si se presiona "No"
						.show();
			}
		});
	}
	public void LimpiarEditext(){
		Distancia.setText("");
		Minutos.setText("");
		Segundos.setText("");
		Lactato.setText("");
		FCLPM.setText("");
		RTC.setText("");
	}
	public int funcionEtapasIniciales(int n) {
		//custom code
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
				EtapasIniciales[0][5] = Double.parseDouble(FCLPM.getText().toString());
				EtapasIniciales[0][6] = Double.parseDouble(RTC.getText().toString());
				EtapasIniciales[n][0] = Double.parseDouble(Distancia.getText().toString());
			} else {
				EtapasIniciales[n][4] = Double.parseDouble(FCLPM.getText().toString());
			}

		} catch (NumberFormatException e) {
			Toast.makeText(this, "Por favor, ingrese datos válidos ", Toast.LENGTH_SHORT).show();
			return n;
		}
		if (Aerobico){
			// Guardar datos
			m += 1;
			Distancia.setVisibility(View.GONE);
			hintTextView.setVisibility(View.GONE);
			if (EtapasIniciales[n][3] < 4){
				etapa_1_aerobica.setText("Etapa " + m + " Aerobica");
				Aerobico = true;
			}
			else {
				etapa_1_aerobica.setText("Etapa Anaerobica");
				FCLPM.setTextSize(16);
				FCLPM.setHint("400M.TECN.LIBRE EN COMPETENCIA Min.");
				hintTextViewFCLPM.setVisibility(View.GONE);
				Distancia.setVisibility(View.VISIBLE);
				hintTextView.setVisibility(View.VISIBLE);
				RTC.setVisibility(View.VISIBLE);
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
		paint.setColor(Color.RED);
		paint.setTextSize(12);

		// Dibujar el título
		paint.setTextSize(16);
		paint.setFakeBoldText(true);
		canvas.drawText("TEST ESTÁNDAR DE LACTATO PARA NATACION CONVENCIONAL", 20, 30, paint);
		paint.setColor(Color.BLACK);
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
		canvas.drawText("TEMPERATURA GRADOS C (LACTATE SCOUT)", 20, 180, paint);
		canvas.drawText(userTemperatura+"°", 400, 180, paint);

		canvas.drawText("PERIODO; ETAPA:", 20, 200, paint);
		String txtPeriodo = userPeriodo.equals("1") ? "PERIODO PREPARATORIO": userPeriodo.equals("2") ? "ETAPA PRECOMPETITIVA": userPeriodo.equals("3") ? "ETAPA COMPETITIVA":"valor no existente";
		canvas.drawText(txtPeriodo, 200, 200, paint);
		String txtDeporte = userEvent.equals("1") ? "RESISTENCIA": userEvent.equals("2") ? "PELOTAS": userEvent.equals("3") ? "COMBATE": userEvent.equals("4") ? "VELOC., FZA RAP., ANAEROBICO":"valor no existente";
		canvas.drawText("DEPORTE / EVENTO:", 20, 220, paint);
		canvas.drawText(txtDeporte, 200, 220, paint);
		canvas.drawText("LONGITUD PISCINA, M.:", 20, 240, paint);
		String txtSuperficie = selectedEtapa == 1? "20" : selectedEtapa == 2? "50" : "dato no generado";
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
			String txtOrdenEtapa = i == maxiEtapes-1 ? "Anaerobica" : "Aerobica";
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
		paint.setColor(Color.RED);
		canvas.drawText("RESULTADOS:", startX, startY, paint);
		paint.setColor(Color.BLACK);
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
		canvas.drawText("MUNDIAL", startX + firstColumnWidth + cellWidth, startY, paint);
		canvas.drawText("UNIVERSITARIO", startX + firstColumnWidth + cellWidth * 2, startY , paint);
		startY += cellHeight;

		// Dibujar las líneas de la segunda tabla

		//paint.setStyle(Paint.Style.STROKE);

		int numRowse = 5;
		for (int i = 0; i <= numRowse; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + firstColumnWidth + cellWidth * 3, startY + i * cellHeight, paint);
		}
		canvas.drawLine(startX, startY, startX, startY + numRowse * cellHeight, paint); // Primera línea vertical (izquierda)
		canvas.drawLine(startX + firstColumnWidth, startY, startX + firstColumnWidth, startY + numRowse * cellHeight, paint); // Línea de la primera columna con ancho ajustado
		for (int i = 1; i <= 3; i++) {
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
				{"V4,m/s."                 ,V4   ,1.45              ,1.35},
				{"V4,km/h."                ,V4km    ,5.22 ,4.86},
				{"V4;mph"                  ,V4mph    ,3.24,3.02},
				{"RITMO/KM , DE V4, MIN."  ,RitmonKMMin ,4	           ,4	    },
				{"RITMO/KM , DE V4, S."    ,RitmonKMS    ,36              ,56        }
		};


		// Añadir datos a la segunda tabla
		for (int i = 0; i < numRowse; i++) {
			// Ajustando las posiciones para cada dato de la fila
			canvas.drawText(String.valueOf(resultados[i][0]), startX, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][1]), startX + firstColumnWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][2]), startX + firstColumnWidth + cellWidth, startY + (i + 1) * cellHeight, paint);
			canvas.drawText(String.valueOf(resultados[i][3]), startX + firstColumnWidth + cellWidth * 2, startY + (i + 1) * cellHeight, paint);

		}
		startY += cellHeight * (numRowse + 1); // Espacio adicional después de la tabla
		paint.setFakeBoldText(true);

		// Asumiendo que resultados es un array de arrays de Object
		double valor2 = ((Number) resultados[0][2]).doubleValue();
		double valor3 = ((Number) resultados[0][3]).doubleValue();

		String TextoResultante = V4p > valor2 ? "SUPERIOR A NADADOR DE CLASE MUNDIAL" : V4p > valor3 ? "SUPERIOR A NADADOR NIVEL UNIVERSITARIO" : "POR DEBAJO DE NADADOR NIVEL UNIVERSITARIO";

		canvas.drawText("VALORACION CAP. AEROBICA: " + TextoResultante, startX, startY, paint);
		startY += cellHeight;

		//lac4/(min4*60+s4)
		// Obtener el valor numérico
		double valorNumerico = (double) EtapasIniciales[momentoAnaerobico][3] / (EtapasIniciales[momentoAnaerobico][1] * 60 + EtapasIniciales[momentoAnaerobico][2]);
		// Formatear el valor a 5 decimales
		DecimalFormat df2 = new DecimalFormat("#.#####");
		String RitmoMaxProdLac = df2.format(valorNumerico);
		canvas.drawText("RITMO MAXIMO DE PRODUCCION DE LACTATO (VLaMax.), MMOL/L./S.: "+ RitmoMaxProdLac, startX, startY, paint);
		startY += cellHeight;
		double VelMsp = (0.7676*V4p) + 0.4299;
		String VelMs = df.format(VelMsp);

		canvas.drawText("PRONOSTICO DE RESULTADO EN 400 M. NADO TECNICA LIBRE SEGÚN LA V4 VelMs: "+ VelMs + " Min: "+ EtapasIniciales[0][5] + " Seg: " + EtapasIniciales[0][6], startX, startY, paint);
		// Finaliza la página
		pdfDocument.finishPage(page);
		// Crear la segunda página para la gráfica
		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 2).create(); // Nueva página
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		// Crear la gráfica unica
		Bitmap scatterPlotBitmap = scatterPlot.createScatterPlotUniti(595, 842, xDataV, yDataL);
		// Dibujar la gráfica en el PDF
		canvas.drawBitmap(scatterPlotBitmap, 0, 0, null);
		// Finaliza la página
		pdfDocument.finishPage(page);
		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 3).create(); // Nueva página
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		// Crear la gráfica doble
		Bitmap segundoscatterPlotBitmap = scatterPlot.createScatterPlot(595, 842, userId ,xDataV, yDataL);
		// Dibujar la gráfica en el PDF
		canvas.drawBitmap(segundoscatterPlotBitmap, 0, 0, null);
		// Finaliza la página
		pdfDocument.finishPage(page);

		pageInfo = new PdfDocument.PageInfo.Builder(595, 900, 4).create();
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		
		double [][] Int100M = {
				{0.907, 0.945, 0.973, 0.987, 1.017, 1.033},
				{1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1},
				{100, 100, 100, 100, 100, 100},
				{1, 1, 1, 1, 1, 1}
		};
		//f2
		Int100M[1][0]=V4p*1.050;
		Int100M[1][1]=V4p*1.050;
		Int100M[1][2]=V4p*1.050;
		Int100M[1][3]=V4p*1.050;
		Int100M[1][4]=V4p*1.050;
		Int100M[1][5]=V4p*1.050;
		//f3
		Int100M[2][0]=Int100M[1][0]*Int100M[0][0];
		Int100M[2][1]=Int100M[1][1]*Int100M[0][1];
		Int100M[2][2]=Int100M[1][2]*Int100M[0][2];
		Int100M[2][3]=Int100M[1][3]*Int100M[0][3];
		Int100M[2][4]=Int100M[1][4]*Int100M[0][4];
		Int100M[2][5]=Int100M[1][5]*Int100M[0][5];
		//f5
		Int100M[4][0]=100/Int100M[2][0];
		Int100M[4][1]=100/Int100M[2][1];
		Int100M[4][2]=100/Int100M[2][2];
		Int100M[4][3]=100/Int100M[2][3];
		Int100M[4][4]=100/Int100M[2][4];
		Int100M[4][5]=100/Int100M[2][5];
		//datos para la primera tabla
		double[][] Int100MTabla = new double[3][6];
		String[][] Int100MSTabla = new String[3][6];
		//parte min1
		Int100MTabla[0][0] = Int100M[4][0]/60;
		Int100MTabla[0][1] = Int100M[4][1]/60;
		Int100MTabla[0][2] = Int100M[4][2]/60;
		Int100MTabla[0][3] = Int100M[4][3]/60;
		Int100MTabla[0][4] = Int100M[4][4]/60;
		Int100MTabla[0][5] = Int100M[4][5]/60;

		Int100MSTabla[0][0] = String.format("%d", (int)Int100MTabla[0][0]);
		Int100MSTabla[0][1] = String.format("%d", (int)Int100MTabla[0][1]);
		Int100MSTabla[0][2] = String.format("%d", (int)Int100MTabla[0][2]);
		Int100MSTabla[0][3] = String.format("%d", (int)Int100MTabla[0][3]);
		Int100MSTabla[0][4] = String.format("%d", (int)Int100MTabla[0][4]);
		Int100MSTabla[0][5] = String.format("%d", (int)Int100MTabla[0][5]);
		//parcheo de int
		int[] parchemin1 = new int[7];
		parchemin1[0] = (int) Int100MTabla[0][0];
		parchemin1[1] = (int) Int100MTabla[0][1];
		parchemin1[2] = (int) Int100MTabla[0][2];
		parchemin1[3] = (int) Int100MTabla[0][3];
		parchemin1[4] = (int) Int100MTabla[0][4];
		parchemin1[5] = (int) Int100MTabla[0][5];
		//parte s1
		Int100MTabla[1][0] = (Int100M[4][0]-(parchemin1[0]*60));
		Int100MTabla[1][1] = (Int100M[4][1]-(parchemin1[1]*60));
		Int100MTabla[1][2] = (Int100M[4][2]-(parchemin1[2]*60));
		Int100MTabla[1][3] = (Int100M[4][3]-(parchemin1[3]*60));
		Int100MTabla[1][4] = (Int100M[4][4]-(parchemin1[4]*60));
		Int100MTabla[1][5] = (Int100M[4][5]-(parchemin1[5]*60));
		Int100MSTabla[1][0] = String.format("%d", (int)Int100MTabla[1][0]);
		Int100MSTabla[1][1] = String.format("%d", (int)Int100MTabla[1][1]);
		Int100MSTabla[1][2] = String.format("%d", (int)Int100MTabla[1][2]);
		Int100MSTabla[1][3] = String.format("%d", (int)Int100MTabla[1][3]);
		Int100MSTabla[1][4] = String.format("%d", (int)Int100MTabla[1][4]);
		Int100MSTabla[1][5] = String.format("%d", (int)Int100MTabla[1][5]);
		//parte v4
		Int100MTabla[2][0] = Int100M[0][0]*100;
		Int100MTabla[2][1] = Int100M[0][1]*100;
		Int100MTabla[2][2] = Int100M[0][2]*100;
		Int100MTabla[2][3] = Int100M[0][3]*100;
		Int100MTabla[2][4] = Int100M[0][4]*100;
		Int100MTabla[2][5] = Int100M[0][5]*100;

		Int100MSTabla[2][0] = String.format("%d", (int)Int100MTabla[2][0]);
		Int100MSTabla[2][1] = String.format("%d", (int)Int100MTabla[2][1]);
		Int100MSTabla[2][2] = String.format("%d", (int)Int100MTabla[2][2]);
		Int100MSTabla[2][3] = String.format("%d", (int)Int100MTabla[2][3]);
		Int100MSTabla[2][4] = String.format("%d", (int)Int100MTabla[2][4]);
		Int100MSTabla[2][5] = String.format("%d", (int)Int100MTabla[2][5]);

		double [][] Int200M = {
			{0.9135, 0.9447, 0.9782, 0.9923, 1.0142, 1.0371},
			{1, 1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1, 1},
			{200, 200, 200, 200, 200, 200},
			{1, 1, 1, 1, 1, 1}
		};
		//f2
		Int200M[1][0]=V4p*1.022;
		Int200M[1][1]=V4p*1.022;
		Int200M[1][2]=V4p*1.022;
		Int200M[1][3]=V4p*1.022;
		Int200M[1][4]=V4p*1.022;
		Int200M[1][5]=V4p*1.022;
		//f3
		Int200M[2][0]=Int200M[1][0]*Int200M[0][0];
		Int200M[2][1]=Int200M[1][1]*Int200M[0][1];
		Int200M[2][2]=Int200M[1][2]*Int200M[0][2];
		Int200M[2][3]=Int200M[1][3]*Int200M[0][3];
		Int200M[2][4]=Int200M[1][4]*Int200M[0][4];
		Int200M[2][5]=Int200M[1][5]*Int200M[0][5];
		//f5
		Int200M[4][0]=200/Int200M[2][0];
		Int200M[4][1]=200/Int200M[2][1];
		Int200M[4][2]=200/Int200M[2][2];
		Int200M[4][3]=200/Int200M[2][3];
		Int200M[4][4]=200/Int200M[2][4];
		Int200M[4][5]=200/Int200M[2][5];
		//datos para la primera tabla
		double[][] Int200MTabla = new double[3][6];
		String[][] Int200MSTabla = new String[3][6];
		//parte min1
		Int200MTabla[0][0] = Int200M[4][0]/60;
		Int200MTabla[0][1] = Int200M[4][1]/60;
		Int200MTabla[0][2] = Int200M[4][2]/60;
		Int200MTabla[0][3] = Int200M[4][3]/60;
		Int200MTabla[0][4] = Int200M[4][4]/60;
		Int200MTabla[0][5] = Int200M[4][5]/60;

		Int200MSTabla[0][0] = String.format("%d", (int)Int200MTabla[0][0]);
		Int200MSTabla[0][1] = String.format("%d", (int)Int200MTabla[0][1]);
		Int200MSTabla[0][2] = String.format("%d", (int)Int200MTabla[0][2]);
		Int200MSTabla[0][3] = String.format("%d", (int)Int200MTabla[0][3]);
		Int200MSTabla[0][4] = String.format("%d", (int)Int200MTabla[0][4]);
		Int200MSTabla[0][5] = String.format("%d", (int)Int200MTabla[0][5]);
		//parcheo de int
		int[] parchemin2 = new int[7];
		parchemin2[0] = (int) Int200MTabla[0][0];
		parchemin2[1] = (int) Int200MTabla[0][1];
		parchemin2[2] = (int) Int200MTabla[0][2];
		parchemin2[3] = (int) Int200MTabla[0][3];
		parchemin2[4] = (int) Int200MTabla[0][4];
		parchemin2[5] = (int) Int200MTabla[0][5];
		//parte s1
		Int200MTabla[1][0] = (Int200M[4][0]-(parchemin2[0]*60));
		Int200MTabla[1][1] = (Int200M[4][1]-(parchemin2[1]*60));
		Int200MTabla[1][2] = (Int200M[4][2]-(parchemin2[2]*60));
		Int200MTabla[1][3] = (Int200M[4][3]-(parchemin2[3]*60));
		Int200MTabla[1][4] = (Int200M[4][4]-(parchemin2[4]*60));
		Int200MTabla[1][5] = (Int200M[4][5]-(parchemin2[5]*60));
		Int200MSTabla[1][0] = String.format("%d", (int)Int200MTabla[1][0]);
		Int200MSTabla[1][1] = String.format("%d", (int)Int200MTabla[1][1]);
		Int200MSTabla[1][2] = String.format("%d", (int)Int200MTabla[1][2]);
		Int200MSTabla[1][3] = String.format("%d", (int)Int200MTabla[1][3]);
		Int200MSTabla[1][4] = String.format("%d", (int)Int200MTabla[1][4]);
		Int200MSTabla[1][5] = String.format("%d", (int)Int200MTabla[1][5]);
		//parte v4
		Int200MTabla[2][0] = Int200M[0][0]*100;
		Int200MTabla[2][1] = Int200M[0][1]*100;
		Int200MTabla[2][2] = Int200M[0][2]*100;
		Int200MTabla[2][3] = Int200M[0][3]*100;
		Int200MTabla[2][4] = Int200M[0][4]*100;
		Int200MTabla[2][5] = Int200M[0][5]*100;

		Int200MSTabla[2][0] = String.format("%d", (int)Int200MTabla[2][0]);
		Int200MSTabla[2][1] = String.format("%d", (int)Int200MTabla[2][1]);
		Int200MSTabla[2][2] = String.format("%d", (int)Int200MTabla[2][2]);
		Int200MSTabla[2][3] = String.format("%d", (int)Int200MTabla[2][3]);
		Int200MSTabla[2][4] = String.format("%d", (int)Int200MTabla[2][4]);
		Int200MSTabla[2][5] = String.format("%d", (int)Int200MTabla[2][5]);
		double [][] Int400M = {
				{0.9216, 0.9527, 0.9826, 0.9930, 1.0144, 1.0330},
				{1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1},
				{400, 400, 400, 400, 400, 400},
				{1, 1, 1, 1, 1, 1}
		};
		//f2
		Int400M[1][0]=V4p;
		Int400M[1][1]=V4p;
		Int400M[1][2]=V4p;
		Int400M[1][3]=V4p;
		Int400M[1][4]=V4p;
		Int400M[1][5]=V4p;
		//f3
		Int400M[2][0]=Int400M[1][0]*Int400M[0][0];
		Int400M[2][1]=Int400M[1][1]*Int400M[0][1];
		Int400M[2][2]=Int400M[1][2]*Int400M[0][2];
		Int400M[2][3]=Int400M[1][3]*Int400M[0][3];
		Int400M[2][4]=Int400M[1][4]*Int400M[0][4];
		Int400M[2][5]=Int400M[1][5]*Int400M[0][5];
		//f5
		Int400M[4][0]=400/Int400M[2][0];
		Int400M[4][1]=400/Int400M[2][1];
		Int400M[4][2]=400/Int400M[2][2];
		Int400M[4][3]=400/Int400M[2][3];
		Int400M[4][4]=400/Int400M[2][4];
		Int400M[4][5]=400/Int400M[2][5];
		//datos para la primera tabla
		double[][] Int400MTabla = new double[3][6];
		String[][] Int400MSTabla = new String[3][6];
		//parte min1
		Int400MTabla[0][0] = Int400M[4][0]/60;
		Int400MTabla[0][1] = Int400M[4][1]/60;
		Int400MTabla[0][2] = Int400M[4][2]/60;
		Int400MTabla[0][3] = Int400M[4][3]/60;
		Int400MTabla[0][4] = Int400M[4][4]/60;
		Int400MTabla[0][5] = Int400M[4][5]/60;

		Int400MSTabla[0][0] = String.format("%d", (int)Int400MTabla[0][0]);
		Int400MSTabla[0][1] = String.format("%d", (int)Int400MTabla[0][1]);
		Int400MSTabla[0][2] = String.format("%d", (int)Int400MTabla[0][2]);
		Int400MSTabla[0][3] = String.format("%d", (int)Int400MTabla[0][3]);
		Int400MSTabla[0][4] = String.format("%d", (int)Int400MTabla[0][4]);
		Int400MSTabla[0][5] = String.format("%d", (int)Int400MTabla[0][5]);
		//parcheo de int
		int[] parchemin4 = new int[7];
		parchemin4[0] = (int) Int400MTabla[0][0];
		parchemin4[1] = (int) Int400MTabla[0][1];
		parchemin4[2] = (int) Int400MTabla[0][2];
		parchemin4[3] = (int) Int400MTabla[0][3];
		parchemin4[4] = (int) Int400MTabla[0][4];
		parchemin4[5] = (int) Int400MTabla[0][5];
		//parte s1
		Int400MTabla[1][0] = (Int400M[4][0]-(parchemin4[0]*60));
		Int400MTabla[1][1] = (Int400M[4][1]-(parchemin4[1]*60));
		Int400MTabla[1][2] = (Int400M[4][2]-(parchemin4[2]*60));
		Int400MTabla[1][3] = (Int400M[4][3]-(parchemin4[3]*60));
		Int400MTabla[1][4] = (Int400M[4][4]-(parchemin4[4]*60));
		Int400MTabla[1][5] = (Int400M[4][5]-(parchemin4[5]*60));
		Int400MSTabla[1][0] = String.format("%d", (int)Int400MTabla[1][0]);
		Int400MSTabla[1][1] = String.format("%d", (int)Int400MTabla[1][1]);
		Int400MSTabla[1][2] = String.format("%d", (int)Int400MTabla[1][2]);
		Int400MSTabla[1][3] = String.format("%d", (int)Int400MTabla[1][3]);
		Int400MSTabla[1][4] = String.format("%d", (int)Int400MTabla[1][4]);
		Int400MSTabla[1][5] = String.format("%d", (int)Int400MTabla[1][5]);
		//parte v4
		Int400MTabla[2][0] = Int400M[0][0]*100;
		Int400MTabla[2][1] = Int400M[0][1]*100;
		Int400MTabla[2][2] = Int400M[0][2]*100;
		Int400MTabla[2][3] = Int400M[0][3]*100;
		Int400MTabla[2][4] = Int400M[0][4]*100;
		Int400MTabla[2][5] = Int400M[0][5]*100;

		Int400MSTabla[2][0] = String.format("%d", (int)Int400MTabla[2][0]);
		Int400MSTabla[2][1] = String.format("%d", (int)Int400MTabla[2][1]);
		Int400MSTabla[2][2] = String.format("%d", (int)Int400MTabla[2][2]);
		Int400MSTabla[2][3] = String.format("%d", (int)Int400MTabla[2][3]);
		Int400MSTabla[2][4] = String.format("%d", (int)Int400MTabla[2][4]);
		Int400MSTabla[2][5] = String.format("%d", (int)Int400MTabla[2][5]);
		double [][] ContRitmo100M = {
				{0.9009, 0.9365, 0.9618, 0.9885, 1.0168, 1.0467},
				{1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1},
				{100, 100, 100, 100, 100, 100},
				{1, 1, 1, 1, 1, 1}
		};
		//f2
		ContRitmo100M[1][1]=V4p*0.991;
		ContRitmo100M[1][0]=V4p*0.991;
		ContRitmo100M[1][2]=V4p*0.991;
		ContRitmo100M[1][3]=V4p*0.991;
		ContRitmo100M[1][4]=V4p*0.991;
		ContRitmo100M[1][5]=V4p*0.991;
		//f3
		ContRitmo100M[2][0]=ContRitmo100M[1][0]*ContRitmo100M[0][0];
		ContRitmo100M[2][1]=ContRitmo100M[1][1]*ContRitmo100M[0][1];
		ContRitmo100M[2][2]=ContRitmo100M[1][2]*ContRitmo100M[0][2];
		ContRitmo100M[2][3]=ContRitmo100M[1][3]*ContRitmo100M[0][3];
		ContRitmo100M[2][4]=ContRitmo100M[1][4]*ContRitmo100M[0][4];
		ContRitmo100M[2][5]=ContRitmo100M[1][5]*ContRitmo100M[0][5];
		//f5
		ContRitmo100M[4][0]=100/ContRitmo100M[2][0];
		ContRitmo100M[4][1]=100/ContRitmo100M[2][1];
		ContRitmo100M[4][2]=100/ContRitmo100M[2][2];
		ContRitmo100M[4][3]=100/ContRitmo100M[2][3];
		ContRitmo100M[4][4]=100/ContRitmo100M[2][4];
		ContRitmo100M[4][5]=100/ContRitmo100M[2][5];
		//datos para la primera tabla
		double[][] ContRitmo100MTabla = new double[3][6];
		String[][] ContRitmo100MSTabla = new String[3][6];
		//parte min1
		ContRitmo100MTabla[0][0] = ContRitmo100M[4][0]/60;
		ContRitmo100MTabla[0][1] = ContRitmo100M[4][1]/60;
		ContRitmo100MTabla[0][2] = ContRitmo100M[4][2]/60;
		ContRitmo100MTabla[0][3] = ContRitmo100M[4][3]/60;
		ContRitmo100MTabla[0][4] = ContRitmo100M[4][4]/60;
		ContRitmo100MTabla[0][5] = ContRitmo100M[4][5]/60;

		ContRitmo100MSTabla[0][0] = String.format("%d", (int)ContRitmo100MTabla[0][0]);
		ContRitmo100MSTabla[0][1] = String.format("%d", (int)ContRitmo100MTabla[0][1]);
		ContRitmo100MSTabla[0][2] = String.format("%d", (int)ContRitmo100MTabla[0][2]);
		ContRitmo100MSTabla[0][3] = String.format("%d", (int)ContRitmo100MTabla[0][3]);
		ContRitmo100MSTabla[0][4] = String.format("%d", (int)ContRitmo100MTabla[0][4]);
		ContRitmo100MSTabla[0][5] = String.format("%d", (int)ContRitmo100MTabla[0][5]);
		//parcheo de int
		int[] parcheminContRitmo100M = new int[7];
		parcheminContRitmo100M[0] = (int) ContRitmo100MTabla[0][0];
		parcheminContRitmo100M[1] = (int) ContRitmo100MTabla[0][1];
		parcheminContRitmo100M[2] = (int) ContRitmo100MTabla[0][2];
		parcheminContRitmo100M[3] = (int) ContRitmo100MTabla[0][3];
		parcheminContRitmo100M[4] = (int) ContRitmo100MTabla[0][4];
		parcheminContRitmo100M[5] = (int) ContRitmo100MTabla[0][5];
		//parte s1
		ContRitmo100MTabla[1][0] = (ContRitmo100M[4][0]-(parchemin1[0]*60));
		ContRitmo100MTabla[1][1] = (ContRitmo100M[4][1]-(parchemin1[1]*60));
		ContRitmo100MTabla[1][2] = (ContRitmo100M[4][2]-(parchemin1[2]*60));
		ContRitmo100MTabla[1][3] = (ContRitmo100M[4][3]-(parchemin1[3]*60));
		ContRitmo100MTabla[1][4] = (ContRitmo100M[4][4]-(parchemin1[4]*60));
		ContRitmo100MTabla[1][5] = (ContRitmo100M[4][5]-(parchemin1[5]*60));
		ContRitmo100MSTabla[1][0] = String.format("%d", (int)ContRitmo100MTabla[1][0]);
		ContRitmo100MSTabla[1][1] = String.format("%d", (int)ContRitmo100MTabla[1][1]);
		ContRitmo100MSTabla[1][2] = String.format("%d", (int)ContRitmo100MTabla[1][2]);
		ContRitmo100MSTabla[1][3] = String.format("%d", (int)ContRitmo100MTabla[1][3]);
		ContRitmo100MSTabla[1][4] = String.format("%d", (int)ContRitmo100MTabla[1][4]);
		ContRitmo100MSTabla[1][5] = String.format("%d", (int)ContRitmo100MTabla[1][5]);
		//parte v4
		ContRitmo100MTabla[2][0] = ContRitmo100M[0][0]*100;
		ContRitmo100MTabla[2][1] = ContRitmo100M[0][1]*100;
		ContRitmo100MTabla[2][2] = ContRitmo100M[0][2]*100;
		ContRitmo100MTabla[2][3] = ContRitmo100M[0][3]*100;
		ContRitmo100MTabla[2][4] = ContRitmo100M[0][4]*100;
		ContRitmo100MTabla[2][5] = ContRitmo100M[0][5]*100;

		ContRitmo100MSTabla[2][0] = String.format("%d", (int)ContRitmo100MTabla[2][0]);
		ContRitmo100MSTabla[2][1] = String.format("%d", (int)ContRitmo100MTabla[2][1]);
		ContRitmo100MSTabla[2][2] = String.format("%d", (int)ContRitmo100MTabla[2][2]);
		ContRitmo100MSTabla[2][3] = String.format("%d", (int)ContRitmo100MTabla[2][3]);
		ContRitmo100MSTabla[2][4] = String.format("%d", (int)ContRitmo100MTabla[2][4]);
		ContRitmo100MSTabla[2][5] = String.format("%d", (int)ContRitmo100MTabla[2][5]);

		
		
		// Title
		paint.setColor(Color.RED);
		canvas.drawText("GUIA DE INTENSIDAD POR TIEMPO DE NADO PARA ENTRENAMIENTO DE RESISTENCIA", 10, 25, paint);
		paint.setColor(Color.BLACK);
		// Aerobic and Anaerobic labels
		canvas.drawText("(BASADO EN RECOMENDACIONES DE J. OLBRECHT. THE SCIENCIE OF WINNING. 2000)", 10, 50, paint);
		// Section for athletes training without issues
		paint.setColor(Color.BLUE);
		canvas.drawText("AERÓBICO",150,75, paint);
		canvas.drawText("ANAERÓBICO",325,75, paint);


		// Table headers
		canvas.drawText("LAC 1",100,100, paint);
		canvas.drawText("LAC 2",200,100, paint);
		canvas.drawText("LAC 3.",250,100, paint);
		canvas.drawText("LAC 4",300,100, paint);
		canvas.drawText("LAC 5",350,100, paint);
		canvas.drawText("REG.",100,125, paint);
		canvas.drawText("AER.INTM.",140,125, paint);
		canvas.drawText("AER.INT.",250,125, paint);
		canvas.drawText("ENT.ANAER.",300,125, paint);
		paint.setColor(Color.RED);

		canvas.drawText("TIEMPO EN INTERVALOS SERIES DE 100 M.", 150, 150, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Min.", 10, 175, paint);
		paint.setColor(Color.BLACK);
		//datos Min
		canvas.drawText(Int100MSTabla[0][0], 100, 175, paint);
		canvas.drawText(Int100MSTabla[0][1], 150, 175, paint);
		canvas.drawText(Int100MSTabla[0][2], 200, 175, paint);
		canvas.drawText(Int100MSTabla[0][3], 250, 175, paint);
		canvas.drawText(Int100MSTabla[0][4], 300, 175, paint);
		canvas.drawText(Int100MSTabla[0][5], 350, 175, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("S.", 10, 200, paint);
		paint.setColor(Color.BLACK);
		//datos S
		canvas.drawText(Int100MSTabla[1][0], 100, 200, paint);
		canvas.drawText(Int100MSTabla[1][1], 150, 200, paint);
		canvas.drawText(Int100MSTabla[1][2], 200, 200, paint);
		canvas.drawText(Int100MSTabla[1][3], 250, 200, paint);
		canvas.drawText(Int100MSTabla[1][4], 300, 200, paint);
		canvas.drawText(Int100MSTabla[1][5], 350, 200, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("%deV4", 10, 225, paint);
		paint.setColor(Color.BLACK);
		//datos V4
		canvas.drawText(Int100MSTabla[2][0], 100, 225, paint);
		canvas.drawText(Int100MSTabla[2][1], 150, 225, paint);
		canvas.drawText(Int100MSTabla[2][2], 200, 225, paint);
		canvas.drawText(Int100MSTabla[2][3], 250, 225, paint);
		canvas.drawText(Int100MSTabla[2][4], 300, 225, paint);
		canvas.drawText(Int100MSTabla[2][5], 350, 225, paint);
		paint.setColor(Color.RED);
		canvas.drawText("TIEMPO EN INTERVALOS SERIES DE 200 M.", 150, 250, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Min.", 10, 275, paint);
		paint.setColor(Color.BLACK);
		//datos Min
		canvas.drawText(Int200MSTabla[0][0], 100, 275, paint);
		canvas.drawText(Int200MSTabla[0][1], 150, 275, paint);
		canvas.drawText(Int200MSTabla[0][2], 200, 275, paint);
		canvas.drawText(Int200MSTabla[0][3], 250, 275, paint);
		canvas.drawText(Int200MSTabla[0][4], 300, 275, paint);
		canvas.drawText(Int200MSTabla[0][5], 350, 275, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("S.", 10, 300, paint);
		paint.setColor(Color.BLACK);
		//datos S
		canvas.drawText(Int200MSTabla[1][0], 100, 300, paint);
		canvas.drawText(Int200MSTabla[1][1], 150, 300, paint);
		canvas.drawText(Int200MSTabla[1][2], 200, 300, paint);
		canvas.drawText(Int200MSTabla[1][3], 250, 300, paint);
		canvas.drawText(Int200MSTabla[1][4], 300, 300, paint);
		canvas.drawText(Int200MSTabla[1][5], 350, 300, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("%deV4", 10, 325, paint);
		paint.setColor(Color.BLACK);
		//datos V4
		canvas.drawText(Int200MSTabla[2][0], 100, 325, paint);
		canvas.drawText(Int200MSTabla[2][1], 150, 325, paint);
		canvas.drawText(Int200MSTabla[2][2], 200, 325, paint);
		canvas.drawText(Int200MSTabla[2][3], 250, 325, paint);
		canvas.drawText(Int200MSTabla[2][4], 300, 325, paint);
		canvas.drawText(Int200MSTabla[2][5], 350, 325, paint);
		paint.setColor(Color.RED);
		canvas.drawText("TIEMPO EN INTERVALOS SERIES DE 400 M.", 150, 350, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Min.", 10, 375, paint);
		paint.setColor(Color.BLACK);
		//datos Min
		canvas.drawText(Int400MSTabla[0][0], 100, 375, paint);
		canvas.drawText(Int400MSTabla[0][1], 150, 375, paint);
		canvas.drawText(Int400MSTabla[0][2], 200, 375, paint);
		canvas.drawText(Int400MSTabla[0][3], 250, 375, paint);
		canvas.drawText(Int400MSTabla[0][4], 300, 375, paint);
		canvas.drawText(Int400MSTabla[0][5], 350, 375, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("S.", 10, 400, paint);
		paint.setColor(Color.BLACK);
		//datos S
		canvas.drawText(Int400MSTabla[1][0], 100, 400, paint);
		canvas.drawText(Int400MSTabla[1][1], 150, 400, paint);
		canvas.drawText(Int400MSTabla[1][2], 200, 400, paint);
		canvas.drawText(Int400MSTabla[1][3], 250, 400, paint);
		canvas.drawText(Int400MSTabla[1][4], 300, 400, paint);
		canvas.drawText(Int400MSTabla[1][5], 350, 400, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("%deV4", 10, 425, paint);
		paint.setColor(Color.BLACK);
		//datos V4
		canvas.drawText(Int400MSTabla[2][0], 100, 425, paint);
		canvas.drawText(Int400MSTabla[2][1], 150, 425, paint);
		canvas.drawText(Int400MSTabla[2][2], 200, 425, paint);
		canvas.drawText(Int400MSTabla[2][3], 250, 425, paint);
		canvas.drawText(Int400MSTabla[2][4], 300, 425, paint);
		canvas.drawText(Int400MSTabla[2][5], 350, 425, paint);
		paint.setColor(Color.RED);
		canvas.drawText("TIEMPO EN NADO CONTINUO - RITMO PROMEDIO POR 100 M.", 150, 450, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Min.", 10, 475, paint);
		paint.setColor(Color.BLACK);
		//datos Min
		canvas.drawText(ContRitmo100MSTabla[0][0], 100, 475, paint);
		canvas.drawText(ContRitmo100MSTabla[0][1], 150, 475, paint);
		canvas.drawText(ContRitmo100MSTabla[0][2], 200, 475, paint);
		canvas.drawText(ContRitmo100MSTabla[0][3], 250, 475, paint);
		canvas.drawText(ContRitmo100MSTabla[0][4], 300, 475, paint);
		canvas.drawText(ContRitmo100MSTabla[0][5], 350, 475, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("S.", 10, 500, paint);
		paint.setColor(Color.BLACK);
		//datos S
		canvas.drawText(ContRitmo100MSTabla[1][0], 100, 500, paint);
		canvas.drawText(ContRitmo100MSTabla[1][1], 150, 500, paint);
		canvas.drawText(ContRitmo100MSTabla[1][2], 200, 500, paint);
		canvas.drawText(ContRitmo100MSTabla[1][3], 250, 500, paint);
		canvas.drawText(ContRitmo100MSTabla[1][4], 300, 500, paint);
		canvas.drawText(ContRitmo100MSTabla[1][5], 350, 500, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("%deV4", 10, 525, paint);
		paint.setColor(Color.BLACK);
		//datos V4
		canvas.drawText(ContRitmo100MSTabla[2][0], 100, 525, paint);
		canvas.drawText(ContRitmo100MSTabla[2][1], 150, 525, paint);
		canvas.drawText(ContRitmo100MSTabla[2][2], 200, 525, paint);
		canvas.drawText(ContRitmo100MSTabla[2][3], 250, 525, paint);
		canvas.drawText(ContRitmo100MSTabla[2][4], 300, 525, paint);
		canvas.drawText(ContRitmo100MSTabla[2][5], 350, 525, paint);
		// Comments section
		paint.setColor(Color.RED);
		canvas.drawText("COMENTARIOS:", 250, 550, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("* Del periodo preparatorio a la etapa precompetitiva, en deportes de resistencia aerobia debe enfatizarse en entrenamientos", 10, 575, paint);
		canvas.drawText("continuo extensivo y cercano al umbral láctico para elevar la V4 o umbral láctico (aunque esto deprime el ritmo máximo de producción", 10, 600, paint);
		canvas.drawText("de lactato)", 10, 625, paint);

		canvas.drawText("* Del periodo preparatorio a la etapa precompetitiva, en deportes cuya competencia dura menos de 8 minutos debe enfatizarse en", 10, 650, paint);
		canvas.drawText("entrenamientos de sprints y de intervalos de elevada intensidad para elevar la ritmo máximo de producción de lactato (aunque esto", 10, 675, paint);
		canvas.drawText("disminuye la V4 o umbral láctico)", 10, 700, paint);

		// Títulos y subtítulos
		paint.setColor(Color.RED);
		canvas.drawText("NIVEL DE CAPACIDAD AEROBICA CON VISTA A ALCANZAR DETERMINADAS METAS:", 10, 725, paint);
		canvas.drawText("RESULTADO QUE SE BUSCAR LOGRAR EN COMPETENCIA DE NATACION EN 400 M. TECNICA LIBRE:", 10, 750, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("TIEMPO, MIN.:", 150, 760, paint);
		canvas.drawText("TIEMPO, S.:", 150, 770, paint);
		canvas.drawText("VELOCIDAD, M/S.:", 150, 780, paint);
		paint.setColor(Color.BLACK);
		// Valores de tiempo y velocidad
		canvas.drawText(String.valueOf(EtapasIniciales[0][5]), 250, 760, paint);
		canvas.drawText(String.valueOf(EtapasIniciales[0][6]), 250, 770, paint);
		float velocidadCompetencia = (float) (400/(EtapasIniciales[0][5]*60+EtapasIniciales[0][6]));
		canvas.drawText(String.format("%.2f", velocidadCompetencia), 250, 780, paint);
		paint.setColor(Color.RED);
		canvas.drawText("PARA LOGRAR EL ANTERIOR TIEMPO SE REQUIERE POSEER UNA V4 (UMBRA LÁCTICO) EN 400 M. TECNICA LIBRE, M/S. DE:", 10, 800, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("VELOCIDAD, M/S.:", 150, 810, paint);
		canvas.drawText("QUE REPRESENTA UN TIEMPO EN 400 M. DE:", 10, 820, paint);
		canvas.drawText("MIN.:", 250, 820, paint);
		canvas.drawText("S.:", 250, 830, paint);
		paint.setColor(Color.BLACK);
		float velocidadCompetencia2 = (float) (1.3*velocidadCompetencia-0.556);
		canvas.drawText(String.format("%.2f", velocidadCompetencia2), 250, 810, paint);
		int MinCopeti = (int)((400/velocidadCompetencia2)/60);
		canvas.drawText(String.valueOf(MinCopeti), 275, 820, paint);
		int SegCompeti = (int) ((400/velocidadCompetencia2)-(MinCopeti*60));
		canvas.drawText(String.valueOf(SegCompeti), 275, 830, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("DIAGNOSTICO (AL COMPARAR LA V4 REAL CON LA QUE DEBE POSEER) SE CONCLUYE QUE:", 10, 850, paint);
		paint.setColor(Color.BLUE);
		String defici = (V4p >= velocidadCompetencia2) ? "NO EXISTE DEFICIT DE CAPACIDAD AEROBICA" : "EXISTE DEFICIT DE CAPACIDAD AEROBICA";
		canvas.drawText(defici, 10, 870, paint);
		// Finish the first page
		pdfDocument.finishPage(page);
		// Guarda el PDF en el almacenamiento externo
		String fileName = "Informe_Lactato_Natacion.pdf";
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