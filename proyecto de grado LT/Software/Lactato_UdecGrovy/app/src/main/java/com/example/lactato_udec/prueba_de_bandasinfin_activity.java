
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

public class prueba_de_bandasinfin_activity extends AppCompatActivity {
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
	private EditText INC;
	private EditText FCLPM;
	private EditText Lactato;
	private EditText Segundos;
	private EditText Minutos;
	private EditText VKMH;
	private TextView guardar;
	private TextView __ya_definiste_tus_datos_;
	private TextView nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques;
	private int n = 0;
	private int m = 1;
	private int selectedEtapa = 0;
	double[][] EtapasIniciales = new double[100][100];
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
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.prueba_de_banda_sin_fin);
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

		_bg__prueba_de_carrera_de_pista_ek2 = (View) findViewById(R.id._bg__prueba_de_carrera_de_pista_ek2);
		image_6 = (ImageView) findViewById(R.id.image_6);
		test_para_carrera_a_pie = (TextView) findViewById(R.id.test_para_carrera_a_pie);
		etapa_1_aerobica = (TextView) findViewById(R.id.etapa_1_aerobica);
		llena_tus_datos_correspondientes = (TextView) findViewById(R.id.llena_tus_datos_correspondientes);
		rectangle_1 = (View) findViewById(R.id.rectangle_1);
		FCLPM = (EditText) findViewById(R.id.FCLPM);
		Lactato = (EditText) findViewById(R.id.Lactato);
		Segundos = (EditText) findViewById(R.id.Segundos);
		Minutos = (EditText) findViewById(R.id.Minutos);
		INC = (EditText) findViewById(R.id.INC);
		VKMH = (EditText) findViewById(R.id.VKMH);
		guardar = (TextView) findViewById(R.id.guardar);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques = (TextView) findViewById(R.id.nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques);
	
		
		//custom code goes here

		guardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				n = funcionEtapasIniciales(n);
			}
		});
	}
	public void LimpiarEditext(){
		Minutos.setText("");
		Segundos.setText("");
		Lactato.setText("");
		FCLPM.setText("");
		INC.setText("");
		VKMH.setText("");
	}
	public int funcionEtapasIniciales(int n) {
		try {
			
				EtapasIniciales[n][0] = Double.parseDouble(VKMH.getText().toString());
				EtapasIniciales[n][1] = Double.parseDouble(Minutos.getText().toString());
				EtapasIniciales[n][2] = Double.parseDouble(Segundos.getText().toString());
				EtapasIniciales[n][3] = Double.parseDouble(Lactato.getText().toString());
				EtapasIniciales[n][4] = Double.parseDouble(FCLPM.getText().toString());
				EtapasIniciales[n][5] = Double.parseDouble(INC.getText().toString());

		} catch (NumberFormatException e) {
			Toast.makeText(this, "Por favor, ingrese datos válidos ", Toast.LENGTH_SHORT).show();
			return n;
		}
		if (Aerobico){
			// Guardar datos
			m += 1;
			if (EtapasIniciales[n][3] < 4){
				etapa_1_aerobica.setText("Etapa " + m + " Aerobica");
				Aerobico = true;
			}
			else {
				etapa_1_aerobica.setText("Etapa Anaerobica");
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
		n += 1;
		LimpiarEditext();
		return n;
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
		canvas.drawText("TEST ESTÁNDAR DE LACTATO PARA CANOTAJE", 20, 30, paint);

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

		// Dibujar las cabeceras de la tabla
		int startX = 20;
		int startY = 270;
		int cellHeight = 20;
		int cellWidth = 60;

		paint.setTextSize(10);
		paint.setFakeBoldText(true);
		canvas.drawText("ETAPAS", startX, startY, paint);
		canvas.drawText("VKM/H", startX + cellWidth, startY, paint);
		canvas.drawText("MIN", startX + cellWidth * 2, startY, paint);
		canvas.drawText("SEG", startX + cellWidth * 3, startY, paint);
		canvas.drawText("LACTATO", startX + cellWidth * 4, startY, paint);
		canvas.drawText("FCLPM", startX + cellWidth * 5, startY, paint);
		canvas.drawText("INC.,%", startX + cellWidth * 6, startY, paint);
		canvas.drawText("VO2 EST.,%", startX + cellWidth * 7, startY, paint);
		canvas.drawText("VELOC.EQUI.,%", startX + cellWidth * 8, startY, paint);

		// Dibujar las líneas de la tabla

		//paint.setStyle(Paint.Style.STROKE);
		int numRows = maxiEtapes; // Número de filas, ajusta según tus datos
		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + cellWidth * 9, startY + i * cellHeight, paint);
		}
		for (int i = 0; i <= 9; i++) {
			canvas.drawLine(startX + i * cellWidth, startY, startX + i * cellWidth, startY + numRows * cellHeight, paint);
		}
		float[] xDataV = new float[numRows];
		float[] yDataL = new float[numRows];
		//hacer calculos

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
			canvas.drawText(String.valueOf(EtapasIniciales[i][5]), startX + cellWidth * 6, startY + (i + 1) * cellHeight, paint);
			// Calcular y dibujar velocidad
			// Asumiendo que EtapasIniciales[i][0] es E15 y EtapasIniciales[i][5] es F15
			double E15 = EtapasIniciales[i][0];
			double F15 = EtapasIniciales[i][5];
			double VO2 = (E15 <= 6) 
    			? (E15 * 1000 / 60 * 0.1 + E15 * 1000 / 60 * 1.8 * F15 / 100 + 3.5) 
    			: (E15 * 1000 / 60 * 0.2 + E15 * 1000 / 60 * 0.9 * F15 / 100 + 3.5);
			EtapasIniciales[i][6] = VO2;
			canvas.drawText(String.format("%.2f", VO2), startX + cellWidth * 7, startY + (i + 1) * cellHeight, paint);
			double VOEQui = (VO2 <= 13.5) 
    			? ((VO2 - 3.5) / 0.1) * 60 / 1000 
    			: ((VO2 - 3.5) / 0.2) * 60 / 1000;
			EtapasIniciales[i][7] = VOEQui;
				xDataV[i] = Float.parseFloat(String.format("%.2f", VOEQui));
				yDataL[i] = Float.parseFloat(String.format("%.2f", EtapasIniciales[i][3]));
			canvas.drawText(String.format("%.3f", VOEQui), startX + cellWidth * 8, startY + (i + 1) * cellHeight, paint);
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
		
		int numRowse = 4;
		for (int i = 0; i <= numRowse; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + firstColumnWidth + cellWidth * 5, startY + i * cellHeight, paint);
		}
		canvas.drawLine(startX, startY, startX, startY + numRowse * cellHeight, paint); // Primera línea vertical (izquierda)
		canvas.drawLine(startX + firstColumnWidth, startY, startX + firstColumnWidth, startY + numRowse * cellHeight, paint); // Línea de la primera columna con ancho ajustado
		for (int i = 1; i <= 5; i++) {
			canvas.drawLine(startX + firstColumnWidth + i * cellWidth, startY, startX + firstColumnWidth + i * cellWidth, startY + numRowse * cellHeight, paint);
		}
		//matecalculos
		double VPre = EtapasIniciales[pre4mol][7];
		double VPost =  EtapasIniciales[Moment4mols][7];
		double V4p= VPre + (VPost-VPre)*( (4 - EtapasIniciales[pre4mol][3]) /(EtapasIniciales[Moment4mols][3]-EtapasIniciales[pre4mol][3]));


		// Formatear V4 a dos decimales
		double V4mphp = V4p*1.609;
		double supRitmo = 1000/(V4p*1000/3600);
		int RitmonKMMin = (int) (supRitmo/60);
		double RitmonKMSp = supRitmo - (RitmonKMMin*60);
		DecimalFormat df = new DecimalFormat("#0.00");
		String V4 = df.format(V4p);
		String V4mph = df.format(V4mphp);
		String RitmonKMS = df.format(RitmonKMSp);
		
		Object[][] resultados = {
				{"V4,km/h."                 ,V4   ,21.60,	19.80,	15.84,	14.94},
				{"V4;mph."                ,V4mph    ,13.42,	12.31,	9.84,	9.29},
				{"RITMO/KM , DE V4, MIN."    ,RitmonKMMin   ,2,	3,	3,	4},
				{"RITMO/KM , DE V4, S."  ,   RitmonKMS ,47,	2,	47,	1}
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
		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 4).create();
		page = pdfDocument.startPage(pageInfo);
		canvas = page.getCanvas();
		double[][] datosEntrenamiento = {
			//[f][C]
			 {0,1.099,	1.18,	1.25,	1.39,	1.39,	1.44},                  
			 {1, 1, 1, 1, 1, 1, 1},                                                       
			 {1, 1, 1, 1, 1, 1, 1},                                                      
			 {1, 1, 1, 1, 1, 1, 1},
			 {1, 1, 1, 1, 1, 1, 1},                                                 
			 {1, 1, 1, 1, 1, 1, 1},                                                  
			 {1, 1, 1, 1, 1, 1, 1}                                                  
	 	};
		//fila 2 o % de V4 Pre int
		datosEntrenamiento[1][0] =EtapasIniciales[momentoAnaerobico][3]*1.672+60.7;
		for (int i = 1; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[1][i] = datosEntrenamiento[1][0] * datosEntrenamiento[0][i];
		}
		//fila 3 o (VEL., KM/H.)
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[2][i] = (V4p*datosEntrenamiento[1][i])/100;
		}
		//fila 4 o (VEL. , MPH)
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[3][i] = datosEntrenamiento[2][i]/1.609;
		}
		//fila 5 o (VEL., M/S.)
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[4][i] = (datosEntrenamiento[2][i]*1000)/3600;
		}
		//fila 6 elgi
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[5][i] = 1000/datosEntrenamiento[4][i];
		}
		//fila 7 pre int RITMO/KM., MIN
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[6][i] = datosEntrenamiento[5][i]/60;
		}
		String[][] primeraTabla = new String[6][7];
		//fila VEL., KM/H.
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[0][i] = String.format("%.2f", (datosEntrenamiento[2][i]));
		}
		//fila VEL. , MPH
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[1][i] = String.format("%.2f", (datosEntrenamiento[3][i]));
		}
		//fila VEL., M/S.
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[2][i] = String.format("%.2f", (datosEntrenamiento[4][i]));
		}
		//fila %v4
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[3][i] = String.format("%d", (int)(datosEntrenamiento[1][i]));
		}
		//fila RITMO/KM., MIN
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[4][i] = String.format("%d", (int)(datosEntrenamiento[6][i]));
		}
		int [] parcheo = new int[7];
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		parcheo[i] = (int)(datosEntrenamiento[6][i]);
		}
		//fila RITMO/KM., S
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[5][i] = String.format("%d", (int)(datosEntrenamiento[5][i]-(parcheo[i]*60)));
		}
		paint.setColor(Color.RED);
		canvas.drawText("GUIA DE INTENSIDAD DE ENTRENAMIENTO DE RESISTENCIA (BASADA EN RECOMENDACIÓN DE OLBRECHT):", 10, 25, paint);
		paint.setColor(Color.BLUE);
		canvas.drawText("Regen.",150,50, paint);
		canvas.drawText("Cont.Ext.",200,50, paint);
		canvas.drawText("Cont.Int.",250,50, paint);
		canvas.drawText("Tempo.",300,50, paint);
		canvas.drawText("Fartlek.",350,50, paint);
		canvas.drawText("Int.",400,50, paint);
		canvas.drawText("Int.Int.",450,50, paint);
		paint.setColor(Color.RED);
		canvas.drawText("VEL., KM/H.", 10, 75, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[0][0], 150, 75, paint);
		canvas.drawText(primeraTabla[0][1], 200, 75, paint);
		canvas.drawText(primeraTabla[0][2], 250, 75, paint);
		canvas.drawText(primeraTabla[0][3], 300, 75, paint);
		canvas.drawText(primeraTabla[0][4], 350, 75, paint);
		canvas.drawText(primeraTabla[0][5], 400, 75, paint);
		canvas.drawText(primeraTabla[0][6], 450, 75, paint);
		paint.setColor(Color.RED);
		canvas.drawText("VEL.,MPH", 10, 100, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[1][0], 150, 100, paint);
		canvas.drawText(primeraTabla[1][1], 200, 100, paint);
		canvas.drawText(primeraTabla[1][2], 250, 100, paint);
		canvas.drawText(primeraTabla[1][3], 300, 100, paint);
		canvas.drawText(primeraTabla[1][4], 350, 100, paint);
		canvas.drawText(primeraTabla[1][5], 400, 100, paint);
		canvas.drawText(primeraTabla[1][6], 450, 100, paint);
		paint.setColor(Color.RED);
		canvas.drawText("VEL., M/S.", 10, 125, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[2][0], 150, 125, paint);
		canvas.drawText(primeraTabla[2][1], 200, 125, paint);
		canvas.drawText(primeraTabla[2][2], 250, 125, paint);
		canvas.drawText(primeraTabla[2][3], 300, 125, paint);
		canvas.drawText(primeraTabla[2][4], 350, 125, paint);
		canvas.drawText(primeraTabla[2][5], 400, 125, paint);
		canvas.drawText(primeraTabla[2][6], 450, 125, paint);
		paint.setColor(Color.RED);
		canvas.drawText("% de V4.", 10, 150, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[3][0], 150, 150, paint);
		canvas.drawText(primeraTabla[3][1], 200, 150, paint);
		canvas.drawText(primeraTabla[3][2], 250, 150, paint);
		canvas.drawText(primeraTabla[3][3], 300, 150, paint);
		canvas.drawText(primeraTabla[3][4], 350, 150, paint);
		canvas.drawText(primeraTabla[3][5], 400, 150, paint);
		canvas.drawText(primeraTabla[3][6], 450, 150, paint);
		paint.setColor(Color.RED);
		canvas.drawText("RITMO/KM., MIN", 10, 175, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[4][0], 150, 175, paint);
		canvas.drawText(primeraTabla[4][1], 200, 175, paint);
		canvas.drawText(primeraTabla[4][2], 250, 175, paint);
		canvas.drawText(primeraTabla[4][3], 300, 175, paint);
		canvas.drawText(primeraTabla[4][4], 350, 175, paint);
		canvas.drawText(primeraTabla[4][5], 400, 175, paint);
		canvas.drawText(primeraTabla[4][6], 450, 175, paint);
		paint.setColor(Color.RED);
		canvas.drawText("RITMO/KM., S", 10, 200, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[5][0], 150, 200, paint);
		canvas.drawText(primeraTabla[5][1], 200, 200, paint);
		canvas.drawText(primeraTabla[5][2], 250, 200, paint);
		canvas.drawText(primeraTabla[5][3], 300, 200, paint);
		canvas.drawText(primeraTabla[5][4], 350, 200, paint);
		canvas.drawText(primeraTabla[5][5], 400, 200, paint);
		canvas.drawText(primeraTabla[5][6], 450, 200, paint);
		paint.setColor(Color.RED);
		canvas.drawText("#INTERVALOS", 10, 225, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("1", 150, 225, paint);
		canvas.drawText("1", 200, 225, paint);
		canvas.drawText("1", 250, 225, paint);
		canvas.drawText("1", 300, 225, paint);
		canvas.drawText("1", 350, 225, paint);
		canvas.drawText("3 a 5", 400, 225, paint);
		canvas.drawText("3 a 5", 450, 225, paint);
		paint.setColor(Color.RED);
		canvas.drawText("MINUTOS / SESION", 10, 250, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("15 A 30", 150, 250, paint);
		canvas.drawText("30 A 90", 200, 250, paint);
		canvas.drawText("30 A 60", 250, 250, paint);
		canvas.drawText("30 A 60", 300, 250, paint);
		canvas.drawText("30 A 60", 350, 250, paint);
		canvas.drawText("5 A 10", 400, 250, paint);
		canvas.drawText("30'' a 3'", 450, 250, paint);
		paint.setColor(Color.RED);
		canvas.drawText("SESIONES/SEMANA", 10, 275, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("2", 150, 275, paint);
		canvas.drawText("3", 200, 275, paint);
		canvas.drawText("2", 250, 275, paint);
		canvas.drawText("1", 300, 275, paint);
		canvas.drawText("1", 350, 275, paint);
		canvas.drawText("2", 400, 275, paint);
		canvas.drawText("2", 450, 275, paint);
		paint.setColor(Color.RED);
		canvas.drawText("COMENTARIOS:", 250, 300, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("Del periodo preparatorio a la etapa precompetitiva, en deportes de resistencia aerobia debe enfatizarse en entrenamientos continuo extensivo y cerca al umbral láctico para elevar la V4 o umbral láctico (aunque esto deprime la velocidad máxima de producción de lactato)", 250, 325, paint);
		canvas.drawText("Del periodo preparatorio a la etapa precompetitiva, en deportes cuya competencia dura menos de 8 minutos debe enfatizarse en entrenamientos de sprints y de intervalos de elevada intensidad para elevar la velocidad máxima de producción de lactato (aunque esto disminuye la V4 o umbral láctico)", 250, 350, paint);
		// Finish the first page
		pdfDocument.finishPage(page);

		// Guardar el documento en el almacenamiento interno
		String fileName = "Informe_Lactato_BandaSinFin.pdf";
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
	
	