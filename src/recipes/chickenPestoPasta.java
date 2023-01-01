package recipes;

public class chickenPestoPasta extends aaRecipeInterface {

  public chickenPestoPasta() {
    name = "Chicken and Pesto Pasta";
    time = 30;
    setIngredients();
    setDirections();
  }

  @Override
  public void setIngredients() {
    ingredients.put("Chicken", "1 breast");
    ingredients.put("Pasta", "8 oz");
    ingredients.put("Pesto", "1 tbsp");
  }

  @Override
  public void setDirections() {
    directions = "Cook pasta. Cook chicken. Combine with pesto.";
  }

}
