package com.example.lactato_udec;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class definir_datos_deentrada_activity extends AppCompatActivity {
	private boolean colorOriginalPrepSelect = true;
	private boolean colorOriginalPrecomp = true;
	private boolean colorOriginalComp = true;
	private boolean colorOriginalResistencia = true;
	private boolean colorOriginalPelotas = true;
	private boolean colorOriginalCombateSelect = true;
	private boolean colorOriginalVFA = true;
	private View lastSelectedButtonEtapa;
	private View lastSelectedButtonDeporteOEvento;
	private EditText editTextTemperature;
	private EditText editTextWeight;
	private EditText editTextAge;
	private EditText editTextName;
	private View _bg__definir_datos_deentrada_ek2;
	private TextView llena_tus_datos_correspondientes;
	private TextView __ya_definiste_tus_datos_;
	private TextView tu_genero;
	private TextView tu_edad_;
	private TextView tu_peso_;
	private TextView tu_temperatura_;
	private TextView tu_etapa_;
	private TextView deporte_o_evento_;
	private TextView kg_;
	private TextView C;
	private TextView tu_nombre_y_apellido_;
	private ImageView image_5;
	private View rectangle_1;
	private TextView guardar;
	private View rectangle_1_ek1;
	private TextView prepcomp;
	private View rectangle_1_ek2;
	private TextView pelotas;
	private View rectangle_1_ek3;
	private TextView combate;
	private View rectangle_1_ek4;
	private TextView veloc___fza_rap___anaerobico;
	private View rectangle_1_ek5;
	private TextView prep;
	private View rectangle_1_ek6;
	private TextView resistencia;
	private View rectangle_1_ek7;
	private TextView resistencia_ek1;
	private View rectangle_1_ek8;
	private TextView resistencia_ek2;
	private View rectangle_1_ek9;
	private TextView comp;
	private ImageView line_3;
	private ImageView line_4;
	private ImageView line_6;
	private ImageView line_5;
	private ImageView mitad1;
	private ImageView mitad2;
	private View mitad1v;
	private View mitad2v;
	private boolean isMitad1Selected = false;
	private boolean isMitad2Selected = false;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.definir_datos_deentrada);


		_bg__definir_datos_deentrada_ek2 = (View) findViewById(R.id._bg__definir_datos_deentrada_ek2);
		llena_tus_datos_correspondientes = (TextView) findViewById(R.id.llena_tus_datos_correspondientes);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		tu_genero = (TextView) findViewById(R.id.tu_genero);
		tu_edad_ = (TextView) findViewById(R.id.tu_edad_);
		tu_peso_ = (TextView) findViewById(R.id.tu_peso_);
		tu_temperatura_ = (TextView) findViewById(R.id.tu_temperatura_);
		tu_etapa_ = (TextView) findViewById(R.id.tu_etapa_);
		deporte_o_evento_ = (TextView) findViewById(R.id.deporte_o_evento_);
		kg_ = (TextView) findViewById(R.id.kg_);
		C = (TextView) findViewById(R.id.C);
		tu_nombre_y_apellido_ = (TextView) findViewById(R.id.tu_nombre_y_apellido_);
		image_5 = (ImageView) findViewById(R.id.image_5);
		rectangle_1 = (View) findViewById(R.id.rectangle_1);
		guardar = (TextView) findViewById(R.id.guardar);
		rectangle_1_ek1 = (View) findViewById(R.id.rectangle_1_ek1);
		prepcomp = (TextView) findViewById(R.id.prepcomp);
		rectangle_1_ek2 = (View) findViewById(R.id.rectangle_1_ek2);
		pelotas = (TextView) findViewById(R.id.pelotas);
		rectangle_1_ek3 = (View) findViewById(R.id.rectangle_1_ek3);
		combate = (TextView) findViewById(R.id.combate);
		rectangle_1_ek4 = (View) findViewById(R.id.rectangle_1_ek4);
		veloc___fza_rap___anaerobico = (TextView) findViewById(R.id.veloc___fza_rap___anaerobico);
		rectangle_1_ek5 = (View) findViewById(R.id.rectangle_1_ek5);
		prep = (TextView) findViewById(R.id.prep);
		rectangle_1_ek6 = (View) findViewById(R.id.rectangle_1_ek6);
		resistencia = (TextView) findViewById(R.id.resistencia);
		rectangle_1_ek7 = (View) findViewById(R.id.rectangle_1_ek7);
		resistencia_ek1 = (TextView) findViewById(R.id.resistencia_ek1);
		rectangle_1_ek8 = (View) findViewById(R.id.rectangle_1_ek8);
		resistencia_ek2 = (TextView) findViewById(R.id.resistencia_ek2);
		rectangle_1_ek9 = (View) findViewById(R.id.rectangle_1_ek9);
		comp = (TextView) findViewById(R.id.comp);
		line_3 = (ImageView) findViewById(R.id.line_3);
		line_4 = (ImageView) findViewById(R.id.line_4);
		line_6 = (ImageView) findViewById(R.id.line_6);
		line_5 = (ImageView) findViewById(R.id.line_5);
		mitad1 = (ImageView) findViewById(R.id.mitad1);
		mitad2 = (ImageView) findViewById(R.id.mitad2);
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextAge = (EditText) findViewById(R.id.editTextAge);
		editTextWeight = (EditText) findViewById(R.id.editTextWeight);
		editTextTemperature = (EditText) findViewById(R.id.editTextTemperature);
		mitad1v = (View) findViewById(R.id.mitad1v);
		mitad2v = (View) findViewById(R.id.mitad2v);
		//seleccion de genero

		mitad1v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isMitad1Selected) {
					mitad1.setImageResource(R.drawable.mseleccion); // Cambia la imagen de mitad1 seleccionada
					if (isMitad2Selected) {
						mitad2.setImageResource(R.drawable.mitad2); // Restaura la imagen original de mitad2
						isMitad2Selected = false;
					}
					isMitad1Selected = true;
				} else {
					mitad1.setImageResource(R.drawable.mitad1); // Restaura la imagen original de mitad1
					isMitad1Selected = false;
				}
			}
		});

		mitad2v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!isMitad2Selected) {
					mitad2.setImageResource(R.drawable.fseleccion); // Cambia la imagen de mitad2 seleccionada
					if (isMitad1Selected) {
						mitad1.setImageResource(R.drawable.mitad1); // Restaura la imagen original de mitad1
						isMitad1Selected = false;
					}
					isMitad2Selected = true;
				} else {
					mitad2.setImageResource(R.drawable.mitad2); // Restaura la imagen original de mitad2
					isMitad2Selected = false;
				}
			}
		});

		// Asignación de listeners a los botones de grupo Seleccion Etapa
		findViewById(R.id.rectangle_1_ek5).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonEtapa, colorOriginalPrepSelect, R.drawable.rectangle_1_ek5_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonEtapa = v;
				colorOriginalPrepSelect = !colorOriginalPrepSelect;
			}
		});

		findViewById(R.id.rectangle_1_ek1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonEtapa, colorOriginalPrecomp, R.drawable.rectangle_1_ek1_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonEtapa = v;
				colorOriginalPrecomp = !colorOriginalPrecomp;
			}
		});

		findViewById(R.id.rectangle_1_ek9).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonEtapa, colorOriginalComp, R.drawable.rectangle_1_ek9_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonEtapa = v;
				colorOriginalComp = !colorOriginalComp;
			}
		});

		// Asignación de listeners a los botones del grupo de Seleccion Deporte o Evento

		findViewById(R.id.rectangle_1_ek8).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonDeporteOEvento, colorOriginalResistencia, R.drawable.rectangle_1_ek8_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonDeporteOEvento = v;
				colorOriginalResistencia = !colorOriginalResistencia;
			}
		});

		findViewById(R.id.rectangle_1_ek2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonDeporteOEvento, colorOriginalPelotas, R.drawable.rectangle_1_ek2_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonDeporteOEvento = v;
				colorOriginalPelotas = !colorOriginalPelotas;
			}
		});

		findViewById(R.id.rectangle_1_ek3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonDeporteOEvento, colorOriginalCombateSelect, R.drawable.rectangle_1_ek3_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonDeporteOEvento = v;
				colorOriginalCombateSelect = !colorOriginalCombateSelect;
			}
		});

		findViewById(R.id.rectangle_1_ek4).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manageButtonSelection(v, lastSelectedButtonDeporteOEvento, colorOriginalVFA, R.drawable.rectangle_1_ek4_shape, R.drawable.rectangle_1_ek3_shapered);
				lastSelectedButtonDeporteOEvento = v;
				colorOriginalVFA = !colorOriginalVFA;
			}
		});
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

	