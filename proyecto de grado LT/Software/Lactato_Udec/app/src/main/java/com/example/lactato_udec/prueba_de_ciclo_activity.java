
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

public class prueba_de_ciclo_activity extends AppCompatActivity {
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
	private EditText FCRPM;
	private EditText FCLPM;
	private EditText Lactato;
	private EditText Segundos;
	private EditText Minutos;
	private EditText Resist_ped;
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
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.prueba_de_cicloergemetro);
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
		FCRPM = (EditText) findViewById(R.id.FCRPM);
		FCLPM = (EditText) findViewById(R.id.FCLPM);
		Lactato = (EditText) findViewById(R.id.Lactato);
		Segundos = (EditText) findViewById(R.id.Segundos);
		Minutos = (EditText) findViewById(R.id.Minutos);
		Resist_ped = (EditText) findViewById(R.id.Resist_ped);
		guardar = (TextView) findViewById(R.id.guardar);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques = (TextView) findViewById(R.id.nota__el_calentamiento_no_puede_ser_de_mas_de_15_min__ni_incluyendo_piques);
	
		
		//custom code goes here

		guardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(prueba_de_ciclo_activity.this)
						.setTitle("Confirmación")
						.setMessage("¿Está seguro de guardar estos datos?")
						.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// Ejecutar la función para guardar los datos
								n = funcionEtapasIniciales(n);

								// Mostrar mensaje de confirmación
								Toast.makeText(prueba_de_ciclo_activity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("No", null) // No hacer nada si se presiona "No"
						.show();
			}
		});
	}
	public void LimpiarEditext(){
		Resist_ped.setText("");
		Minutos.setText("");
		Segundos.setText("");
		Lactato.setText("");
		FCLPM.setText("");
		FCRPM.setText("");
	}
	public int funcionEtapasIniciales(int n) {
		try {
			
				EtapasIniciales[n][0] = Double.parseDouble(Resist_ped.getText().toString());
				EtapasIniciales[n][1] = Double.parseDouble(Minutos.getText().toString());
				EtapasIniciales[n][2] = Double.parseDouble(Segundos.getText().toString());
				EtapasIniciales[n][3] = Double.parseDouble(Lactato.getText().toString());
				EtapasIniciales[n][4] = Double.parseDouble(FCLPM.getText().toString());
				EtapasIniciales[n][5] = Double.parseDouble(FCRPM.getText().toString());

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
		paint.setColor(Color.RED);
		paint.setTextSize(12);

		// Dibujar el título
		paint.setTextSize(16);
		paint.setFakeBoldText(true);
		canvas.drawText("TEST ESTÁNDAR DE LACTATO PARA CICLOERGEMETRO", 20, 30, paint);
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
		canvas.drawText("RES.PED.,KP.", startX + cellWidth, startY, paint);
		canvas.drawText("MIN", startX + cellWidth * 2, startY, paint);
		canvas.drawText("SEG", startX + cellWidth * 3, startY, paint);
		canvas.drawText("LACTATO", startX + cellWidth * 4, startY, paint);
		canvas.drawText("FCLPM", startX + cellWidth * 5, startY, paint);
		canvas.drawText("FCRLP", startX + cellWidth * 6, startY, paint);
		canvas.drawText("POTENCIA", startX + cellWidth * 7, startY, paint);

		int numRows = maxiEtapes; // Número de filas, ajusta según tus datos
		for (int i = 0; i <= numRows; i++) {
			canvas.drawLine(startX, startY + i * cellHeight, startX + cellWidth * 8, startY + i * cellHeight, paint);
		}
		for (int i = 0; i <= 8; i++) {
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
			canvas.drawText(String.valueOf(EtapasIniciales[i][5]), startX + cellWidth * 6, startY + (i + 1) * cellHeight, paint);
			// Calcular y dibujar potencia
			//(FRCRPM*6*ResPedKP)/6.12

			double FRCRPM = EtapasIniciales[i][5];
			double Respedkp = EtapasIniciales[i][0];

			double potencia = (FRCRPM*6*Respedkp)/6.12;
			xDataV[i] = Float.parseFloat(String.format("%.2f", potencia));
			yDataL[i] = Float.parseFloat(String.format("%.2f", EtapasIniciales[i][3]));
			canvas.drawText(String.format("%.2f", potencia), startX + cellWidth * 7, startY + (i + 1) * cellHeight, paint);
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
		


		DecimalFormat df = new DecimalFormat("#0.00");
        //p4 potencia: 
		double PPre = (EtapasIniciales[pre4mol][5]*6*EtapasIniciales[pre4mol][0])/6.12;
		double PPost =  (EtapasIniciales[Moment4mols][5]*6*EtapasIniciales[Moment4mols][0])/6.12;
		double P4p= PPre + (PPost-PPre)*( (4 - EtapasIniciales[pre4mol][3]) /(EtapasIniciales[Moment4mols][3]-EtapasIniciales[pre4mol][3]));
		String P4 = df.format(P4p);

		canvas.drawText("POTENCIA A 4 MMOL/L., WATTS: " + P4, startX, startY, paint);
		startY += cellHeight;
		String TextoResultante = (P4p < 160) ? "BAJA" : (P4p < 220) ? "PROMEDIO, NORMAL PARA FITNESS" : (P4p < 400) ? "ELEVADA, CICLISTA DE NIVEL MEDIO" : "MUY ELEVADA, CICLISTA PROFESIONAL";
		startY += cellHeight;
		canvas.drawText("EVALUACION DE CAPACIDAD AEROBICA :" + TextoResultante, startX, startY, paint);
		//lac4/(min4*60+s4)
		double valorNumerico = (double) EtapasIniciales[momentoAnaerobico][3] / (EtapasIniciales[momentoAnaerobico][1] * 60 + EtapasIniciales[momentoAnaerobico][2]);
		// Formatear el valor a 5 decimales
		DecimalFormat df2 = new DecimalFormat("#.#####");
		String RitmoMaxProdLac = df2.format(valorNumerico);
		startY += cellHeight;
		canvas.drawText("RITMO MAXIMO DE PRODUCCION DE LACTATO (VLaMax.), MMOL/L./S.: "+ RitmoMaxProdLac, startX, startY, paint);
		//Nueva pagina con lo de la grafica
		// Finaliza la página
		pdfDocument.finishPage(page);

		pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 2).create(); // Tamaño A4
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
			 {70.43,	76.58,	83.01,	89.16,	95.30,	100.61,	105.09},                  
			 {1, 1, 1, 1, 1, 1, 1},                                                       
			 {1, 1, 1, 1, 1, 1, 1},                                                      
			 {1, 1, 1, 1, 1, 1, 1}                                                  
	 	};
		// Llenar la fila [1][] 
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[1][i] = datosEntrenamiento[0][i] / datosEntrenamiento[0][0];
		}
		// LLenar la fila [2][]
		datosEntrenamiento[2][0] =EtapasIniciales[momentoAnaerobico][3]*2.63+38.84;
		for (int i = 1; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[2][i] = datosEntrenamiento[2][0] * datosEntrenamiento[1][i];
		}
		// LLenar la fila [3][]
		for (int i = 0; i < datosEntrenamiento[0].length; i++) {
    		datosEntrenamiento[3][i] = P4p*datosEntrenamiento[2][i]/100;
		}

		String[][] primeraTabla = new String[2][7];
		//fila Potencia 
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[0][i] = String.format("%d", (int)(datosEntrenamiento[3][i]));
		}
		//fila % DE V4
		for (int i = 0; i < primeraTabla[0].length; i++) {
    		primeraTabla[1][i] = String.format("%d", (int)(datosEntrenamiento[2][i]));
		}

		paint.setColor(Color.RED);
		canvas.drawText("GUIA DE INTENSIDAD DE ENTRENAMIENTO DE RESISTENCIA (BASADA EN RECOMENDACIÓN DE OLBRECHT):", 10, 25, paint);
		// Table headers
		canvas.drawText("Regen.",150,75, paint);
		canvas.drawText("Cont.Ext.",200,75, paint);
		canvas.drawText("Cont.Int.",250,75, paint);
		canvas.drawText("Tempo.",300,75, paint);
		canvas.drawText("Fartlek.",350,75, paint);
		canvas.drawText("Int.",400,75, paint);
		canvas.drawText("Int.Int.",450,75, paint);
		// Data for athletes training without issues
		canvas.drawText("POTENCIA, WATTS", 10, 100, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[0][0], 150, 100, paint);
		canvas.drawText(primeraTabla[0][1], 200, 100, paint);
		canvas.drawText(primeraTabla[0][2], 250, 100, paint);
		canvas.drawText(primeraTabla[0][3], 300, 100, paint);
		canvas.drawText(primeraTabla[0][4], 350, 100, paint);
		canvas.drawText(primeraTabla[0][5], 400, 100, paint);
		canvas.drawText(primeraTabla[0][6], 450, 100, paint);
		paint.setColor(Color.RED);
		canvas.drawText("% DE V4", 10, 125, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(primeraTabla[1][0], 150, 125, paint);
		canvas.drawText(primeraTabla[1][1], 200, 125, paint);
		canvas.drawText(primeraTabla[1][2], 250, 125, paint);
		canvas.drawText(primeraTabla[1][3], 300, 125, paint);
		canvas.drawText(primeraTabla[1][4], 350, 125, paint);
		canvas.drawText(primeraTabla[1][5], 400, 125, paint);
		canvas.drawText(primeraTabla[1][6], 450, 125, paint);
		paint.setColor(Color.RED);
		canvas.drawText("#INTERVALOS", 10, 150, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("1", 150, 150, paint);
		canvas.drawText("1", 200, 150, paint);
		canvas.drawText("1", 250, 150, paint);
		canvas.drawText("1", 300, 150, paint);
		canvas.drawText("1", 350, 150, paint);
		canvas.drawText("3 a 5", 400, 150, paint);
		canvas.drawText("3 a 5", 450, 150, paint);
		paint.setColor(Color.RED);
		canvas.drawText("MINUTOS / SESION", 10, 175, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("15 A 30", 150, 175, paint);
		canvas.drawText("30 A 90", 200, 175, paint);
		canvas.drawText("30 A 60", 250, 175, paint);
		canvas.drawText("30 A 60", 300, 175, paint);
		canvas.drawText("30 A 60", 350, 175, paint);
		canvas.drawText("5 A 10", 400, 175, paint);
		canvas.drawText("30'' a 3'", 450, 175, paint);
		paint.setColor(Color.RED);
		canvas.drawText("SESIONES/SEMANA", 10, 200, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("2", 150, 200, paint);
		canvas.drawText("3", 200, 200, paint);
		canvas.drawText("2", 250, 200, paint);
		canvas.drawText("1", 300, 200, paint);
		canvas.drawText("1", 350, 200, paint);
		canvas.drawText("2", 400, 200, paint);
		canvas.drawText("2", 450, 200, paint);
		paint.setColor(Color.RED);
		canvas.drawText("COMENTARIOS:", 250, 225, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText("En etapa preparación general del periodo preparatorio:", 10, 250, paint);
		canvas.drawText("En pisteros: énfasis en entrenamiento aeróbico (aumentar potencia a 4 mmol/l.)", 10, 275, paint);
		canvas.drawText("En ruteros: énfasis en entrenamiento anaeróbico (aumentar VLaMax.) para asimilar carga con bajo riesgo de sobreentrenamiento)", 10, 300, paint);
		canvas.drawText("En etapa precompetitiva:", 10, 325, paint);
		canvas.drawText("En pisteros: énfasis en entrenamiento anaeróbico (aumentar VLaMax..)", 10, 350, paint);
		canvas.drawText("En ruteros: énfasis en entrenamiento aeróbico (aumentar potencia a 4 mmol/l.)", 10, 375, paint);
		// Finish the first page
		pdfDocument.finishPage(page);
		// Guardar el documento en el almacenamiento interno
		String fileName = "Informe_Lactato_CicloErgemetro.pdf";
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
	
	
	