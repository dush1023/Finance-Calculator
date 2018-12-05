package Stock;

import exceptions.Finexception;


//  Represents a set of integers
public class NSDQFinStock extends NSDQStock {
    @Override
    public String stockcategory() throws Finexception {
        if (stockname.equals("fin0")){
            throw new Finexception();
        }

        return ("This is a Fin company");
    }
}