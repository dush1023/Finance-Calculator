package Stock;

import exceptions.Commonexception;

public class NSDQTechStock extends NSDQStock {

    @Override
    public String stockcategory() throws Commonexception {
        if (stockname.equals("tech0")){
            throw new Commonexception();
        }
        return ("This is a Tech company");
    }

}
