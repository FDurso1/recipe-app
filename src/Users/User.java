package Users;

import recipes.aaRecipeInterface;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class User {

  HashMap<String, Double[]> ingredientPreferences = new HashMap<>();
  HashMap<aaRecipeInterface, Double> recipeRatings = new HashMap<>();
  String name;

  public HashMap<String, Double[]> getIngredientPreferences(){return ingredientPreferences;}
  public String getName() {return name;};

  public aaRecipeInterface getRecipe(ArrayList<aaRecipeInterface> book) {
    double best = Double.NEGATIVE_INFINITY;
    aaRecipeInterface bestRecipe = null;

    for (aaRecipeInterface recipe : book) {
      double recipeVal = 0;
      for (String ingredient : recipe.getIngredients().keySet()) {
        try {
          recipeVal += ingredientPreferences.get(ingredient.toLowerCase())[0]-3;
        } catch (NullPointerException np) {
          //recipeVal += 0;
        }
   //     double preference = ingredientPreferences.get(ingredient.toLowerCase())[0];
    //    recipeVal += ingredientPreferences.get(ingredient.toLowerCase())[0] == null ? 0 : ingredientPreferences.get(ingredient.toLowerCase())[0]-3;
      }
      if (recipeVal > best) {
        best = recipeVal;
        bestRecipe = recipe;
      }
    }
    return bestRecipe;
  }

  public aaRecipeInterface getRecipe(ArrayList<aaRecipeInterface> book, ArrayList<String> ingredientInclusions, ArrayList<String> ingredientExclusions) {
    double best = Double.NEGATIVE_INFINITY;
    aaRecipeInterface bestRecipe = null;


    for (aaRecipeInterface recipe : book) {

      double recipeVal = 0;
      for (String ing : ingredientInclusions) {
        if (!recipe.getLowerIngredients().containsKey(ing.toLowerCase())) {
          recipeVal = Double.NEGATIVE_INFINITY;
          break;
        }
      }
      if (recipeVal == Double.NEGATIVE_INFINITY) {
        continue;
      }
      for (String ingredient : recipe.getLowerIngredients().keySet()) {
        if (ingredientExclusions != null && ingredientExclusions.contains(ingredient.toLowerCase())) {
          recipeVal = Double.NEGATIVE_INFINITY;
          break;
        }
        try {
          recipeVal += ingredientPreferences.get(ingredient.toLowerCase())[0]-3;
        } catch (NullPointerException np) {
          //recipeVal += 0;
        }
        //recipeVal += ingredientPreferences.get(ingredient.toLowerCase())[0] == null ? 0 : ingredientPreferences.get(ingredient.toLowerCase())[0]-3;
      }
      if (recipeVal > best) {
        best = recipeVal;
        bestRecipe = recipe;
      }
    }
    return best > Double.NEGATIVE_INFINITY ? bestRecipe : null;
  }

  public void setIngredientRating(String ing, Double rating) {
    String ingredient = ing.toLowerCase();
    if (ingredientPreferences.get(ingredient) == null) {
      ingredientPreferences.put(ingredient, new Double[]{rating, 1.0});
    } else {
      Double[] cur = ingredientPreferences.get(ingredient);
      double newRating = cur[0] * cur[1] + rating*10;
      cur[1] = cur[1]+10;
      newRating /= (cur[1]);
      cur[0] = newRating;
      ingredientPreferences.put(ingredient, cur);
    }
  }

  public void setRecipeRating(aaRecipeInterface recipe, Double rating) {
    recipeRatings.put(recipe, rating);
    for (String ingredient : recipe.getIngredients().keySet()) {
      if (ingredientPreferences.get(ingredient.toLowerCase()) == null) {
        ingredientPreferences.put(ingredient.toLowerCase(), new Double[]{rating, 1.0});
      } else {
        Double[] cur = ingredientPreferences.get(ingredient.toLowerCase());
        double newRating = cur[0] * cur[1] + rating;
        cur[1] = cur[1]+1;
        newRating /= (cur[1]);
        cur[0] = newRating;
        ingredientPreferences.put(ingredient.toLowerCase(), cur);
      }
    }
    recipe.updateRating(rating);
  }

  public void setRecipeRating(ArrayList<aaRecipeInterface> book, String name, Double rating) {
    for (recipes.aaRecipeInterface recipe : book) {
      if (recipe.getName().equals(name)) {
        setRecipeRating(recipe, rating);
        return;
      }
    }
  }

}
