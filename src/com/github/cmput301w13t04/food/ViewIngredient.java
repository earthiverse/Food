package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewIngredient extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_ingredient);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.view_ingredient, menu);
    return true;
  }

}
