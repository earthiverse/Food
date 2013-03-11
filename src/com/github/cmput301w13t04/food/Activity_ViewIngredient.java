package com.github.cmput301w13t04.food;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/* The class that allows us to view ingredients in our UI */
public class Activity_ViewIngredient extends Activity {

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

/**
 * @uml.property  name="activity_EditIngredient"
 * @uml.associationEnd  inverse="activity_ViewIngredient:com.github.cmput301w13t04.food.Activity_EditIngredient"
 */
private Activity_EditIngredient activity_EditIngredient;

/**
 * Getter of the property <tt>activity_EditIngredient</tt>
 * @return  Returns the activity_EditIngredient.
 * @uml.property  name="activity_EditIngredient"
 */
public Activity_EditIngredient getActivity_EditIngredient()

{

	return activity_EditIngredient;
}

/**
 * Setter of the property <tt>activity_EditIngredient</tt>
 * @param activity_EditIngredient  The activity_EditIngredient to set.
 * @uml.property  name="activity_EditIngredient"
 */
public void setActivity_EditIngredient(
		Activity_EditIngredient activity_EditIngredient)

{

	this.activity_EditIngredient = activity_EditIngredient;
}

/**
 * @uml.property  name="cache"
 * @uml.associationEnd  inverse="activity_ViewIngredient:com.github.cmput301w13t04.food.Cache"
 */
private Cache cache;

/**
 * Getter of the property <tt>cache</tt>
 * @return  Returns the cache.
 * @uml.property  name="cache"
 */
public Cache getCache()
{

	return cache;
}

/**
 * Setter of the property <tt>cache</tt>
 * @param cache  The cache to set.
 * @uml.property  name="cache"
 */
public void setCache(Cache cache)
{

	this.cache = cache;
}

}
