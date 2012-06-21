package br.com.	android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.Toast;

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
        //1- Obtemos uma referencia ao NotificationManager do Android
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager)
        getSystemService(ns);
      //2- Vamos criar a notificação, damos a ela um ícone, um título e damos a hora em que a notificação ocorreu
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "MusicReeH";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon, tickerText, when);
        Context context = getApplicationContext();
        CharSequence contentTitle = "MusicReeH";
        CharSequence contentText = "Voce acaba de inicializar o aplicativo MusicReeH";
        //4- criamos os Intents de comunicação da notificação
        Intent notificationIntent = new Intent(this,
       	        MyFirstProgramActivity.class);
       	        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
       	        notificationIntent, 0);
       	        notification.setLatestEventInfo(context, contentTitle, contentText,
       	        contentIntent);
       	        //4 - Enviamos a notificação
       	        final int NOTIFICACAO_ID = 1;
       	        mNotificationManager.notify(NOTIFICACAO_ID, notification);
 
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
 		  Context context2 = getApplicationContext();
  		 CharSequence text2 = "Escolha uma musica!";
  		 int duration2 = Toast.LENGTH_SHORT;
  		 Toast toast2 = Toast.makeText(context2, text2, duration2);
  		 toast2.show();
 		break;
 	   case R.id.sobre_button:
 	      Intent i = new Intent(this, Sobre.class);
 	      startActivity(i);
 	      abrirMenuTocar();
 	   case R.id.parar_buttom:
 		  Context context = getApplicationContext();
 		 CharSequence text = "Voce acaba de parar a musica!";
 		 int duration = Toast.LENGTH_SHORT;
 		 Toast toast = Toast.makeText(context, text, duration);
 		 toast.show();

 		   	mp.stop();
 			   onDestroy();
 	   case R.id.sair_button:
 		   mp.stop();
 		   onDestroy();
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