package br.com.	android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.SyncStateContract.Helpers;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MyFirstProgramActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    private MediaPlayer mp = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //Cria os Botoes e ihabilita acao de toque
        //No Primeiro modifica o textView para outra fonte
        TextView titulo = (TextView)findViewById(R.id.titulo);
        Typeface typeFaceClaireHandRegular = Typeface.createFromAsset(getAssets(), "ClaireHandRegular.otf");
        titulo.setTypeface(typeFaceClaireHandRegular);
        //Botao com clique tocar
        View playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(this);
        //Botao com clique Sobre
        View sobreButton = findViewById(R.id.sobre_button);
        sobreButton.setOnClickListener(this);
        //Botao com clique sair
        View sairButton = findViewById(R.id.sair_button);
        sairButton.setOnClickListener(this);
        //Botao com clique parar
        View pararButton = findViewById(R.id.parar_buttom);
        pararButton.setOnClickListener(this);
        //para utilizar o botao de volume
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //Inicializa o Aplicativo tocando a musica do requiem
        tocarMusica(3);
    }
	
	//Metodo para abrir o menu do tocar
	private void abrirMenuTocar() {
		   new AlertDialog.Builder(this)
		   .setTitle(R.string.play_title)
		   .setItems(R.array.musicas,new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialoginterface, int i) {
		            tocarMusica(i);
		         }
		   }).show();
		}

	//Metodo de case Acoes a serem tomadas ao escolher uma opcao do menu tocar
		protected void tocarMusica(int i) {
			int resId = 0;
			   switch (i) {
			      case 0:
			         resId = R.raw.m1;
			         break;
			      case 1:
			         resId = R.raw.m2;
			         break;
			      case 2:
			         resId = R.raw.m3;
			      case 3:
			    	  resId = R.raw.m4;
			         break;
			   }
			 
			   if (mp!=null)
			      mp.stop();
			 
			   mp = MediaPlayer.create(this, resId);
			   mp.start();
		 }
		
		//Acoes a serem tomadas ao escolher uma opcao do menu tocar
	public void onClick(View v) {
 	   switch (v.getId()) {
 	  case R.id.play_button:
 		   abrirMenuTocar();
 		break;
 	   case R.id.sobre_button:
 	      Intent i = new Intent(this, Sobre.class);
 	      startActivity(i);
 	   case R.id.parar_buttom:
 		   	mp.stop();
 			   onDestroy();
 	   case R.id.sair_button:
 		   finish();
 		   break;
 	   }
 	}
	
	//Criacao do menu de configuracoes
	@Override	
	public boolean onCreateOptionsMenu(Menu menu) {
	   super.onCreateOptionsMenu(menu);
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.menu, menu);
	   return true;
	}
	//Metodo de interacao das opcoes
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	   case R.id.settings:
	      startActivity(new Intent(this, Preferencias.class));
	      return true;
	   }
	   return false;
	}
}