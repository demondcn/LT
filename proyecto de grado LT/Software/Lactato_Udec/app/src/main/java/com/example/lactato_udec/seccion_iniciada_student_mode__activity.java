package com.example.lactato_udec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;



public class seccion_iniciada_student_mode__activity extends AppCompatActivity {

	private View _bg__seccion_iniciada_student_mode__ek2;
	private TextView selecciona_la_prueba_a_realizar;
	private TextView __ya_definiste_tus_datos_;
	private ImageView image_3;
	private ImageView image_5;
	private ImageView image_6;
	private ImageView image_7;
	private ImageView image_8;
	private ImageView image_9;
	private ImageView image_10;
	private ImageView image_11;
	private ImageView image_12;
	private TextView subacuatica;
	private TextView ciclismo;
	private TextView cicloerg_metro;
	private TextView nataccion;
	private TextView canotaje;
	private TextView carrera_en_pista;
	private TextView banda_sin_fin;
	private View rectangle_1;
	private TextView __definir_datos_;

	private int userId;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.seccion_iniciada_student_mode_);
		Intent intent = getIntent();
		if (intent != null && intent.hasExtra("user_id")) {
			userId = intent.getIntExtra("user_id", -1); // Asigna el userId globalmente
		} else {
			// Manejar el caso cuando no se proporciona userId
			userId = -1; // Asigna un valor por defecto o maneja el caso según tu lógica
		}

		_bg__seccion_iniciada_student_mode__ek2 = (View) findViewById(R.id._bg__seccion_iniciada_student_mode__ek2);
		selecciona_la_prueba_a_realizar = (TextView) findViewById(R.id.selecciona_la_prueba_a_realizar);
		__ya_definiste_tus_datos_ = (TextView) findViewById(R.id.__ya_definiste_tus_datos_);
		image_5 = (ImageView) findViewById(R.id.image_5);
		image_6 = (ImageView) findViewById(R.id.image_6);
		image_7 = (ImageView) findViewById(R.id.image_7);
		image_8 = (ImageView) findViewById(R.id.image_8);
		image_9 = (ImageView) findViewById(R.id.image_9);
		image_10 = (ImageView) findViewById(R.id.image_10);
		image_11 = (ImageView) findViewById(R.id.image_11);
		image_12 = (ImageView) findViewById(R.id.image_12);
		subacuatica = (TextView) findViewById(R.id.subacuatica);
		ciclismo = (TextView) findViewById(R.id.ciclismo);
		cicloerg_metro = (TextView) findViewById(R.id.cicloerg_metro);
		nataccion = (TextView) findViewById(R.id.nataccion);
		canotaje = (TextView) findViewById(R.id.canotaje);
		carrera_en_pista = (TextView) findViewById(R.id.carrera_en_pista);
		banda_sin_fin = (TextView) findViewById(R.id.banda_sin_fin);
		rectangle_1 = (View) findViewById(R.id.rectangle_1);
		__definir_datos_ = (TextView) findViewById(R.id.__definir_datos_);

		//custom code goes here
		__definir_datos_.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), definir_datos_deentrada_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
		image_6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_natacion_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
		image_7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_subacuatica_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		image_8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_ciclismo_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		image_9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_canotaje_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		image_10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_bandasinfin_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});

		image_11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_ciclo_activity.class);
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});
		image_12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(seccion_iniciada_student_mode__activity.this, prueba_de_carrera_de_pista_activity.class);
				//envio del id
				intent.putExtra("userId", userId);
				startActivity(intent);
			}
		});




	}
}
	
	