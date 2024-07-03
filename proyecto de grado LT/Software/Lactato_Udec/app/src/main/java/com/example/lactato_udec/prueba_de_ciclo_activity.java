
package com.example.lactato_udec;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
		setContentView(R.layout.prueba_de_cicloergemetro);
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
	public int funcionEtapasIniciales(int n){
		EtapasIniciales[n][0] =Integer.parseInt(Distancia.getText().toString());
		EtapasIniciales[n][1] =Integer.parseInt(Minutos.getText().toString());
		EtapasIniciales[n][2] =Integer.parseInt(Segundos.getText().toString());
		EtapasIniciales[n][3] =Integer.parseInt(Lactato.getText().toString());
		EtapasIniciales[n][4] =Integer.parseInt(FCLPM.getText().toString());
		LimpiarEditext();
        n += 1;
		m += 1;
		String mensaje = m < 5 ? "Etapa "+m+" Aerobica" : m == 5? "Etapa Anaerobica": m == 6? "Etapa Previa a 4 mmol/l" : m ==7? "Etapa Obtencion 4 mmol/l." : "";
		String cambioHintLactamol = (m == 6) || (m==7)? "Lact Mmol/l":"Lactato";
		Lactato.setHint(cambioHintLactamol);
		String cambioHintFclp = (m == 6)? "Tramo para control de ritmo":"FCLPM";
		FCLPM.setHint(cambioHintFclp);
		if (m == 7){
			FCLPM.setVisibility(View.GONE);
		}
		etapa_1_aerobica.setText(mensaje);


		Toast.makeText(this, "el resultado de seleccion es" + selectedEtapa, Toast.LENGTH_LONG).show();
		textotipodesuperficie.setVisibility(View.GONE);
		tartan.setVisibility(View.GONE);
		tartant.setVisibility(View.GONE);
		ARCILLA.setVisibility(View.GONE);
		ARCILLAt.setVisibility(View.GONE);
		ASFALTO.setVisibility(View.GONE);
		ASFALTOt.setVisibility(View.GONE);
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
}
	
	