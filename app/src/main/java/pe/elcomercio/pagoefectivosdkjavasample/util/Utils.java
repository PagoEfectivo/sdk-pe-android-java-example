package pe.elcomercio.pagoefectivosdkjavasample.util;

/**
 * Created by carlosleonardocamilovargashuaman on 10/25/17.
 */

public class Utils {
    public static String addZeroToNumber(String str){
        if(str.length()==1){
            str = "0" + str;
        }
        return str;
    }
}
