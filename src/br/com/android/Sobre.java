package br.com.android;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
 
public class Sobre extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.sobre);
      TextView info = (TextView) findViewById(R.id.sobre_conteudo);
      Typeface typeFaceClaireHandRegular =Typeface.createFromAsset(getAssets(), "ClaireHandRegular.otf");
      info.setTypeface(typeFaceClaireHandRegular);

   }
}