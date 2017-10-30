package pe.elcomercio.pagoefectivosdkjavasample.util;

/**
 * Created by carlosleonardocamilovargashuaman on 10/25/17.
 */

@SuppressWarnings("ALL")
public class Utils {
    public static String addZeroToNumber(String str){
        String newStr = str;
        if(str.length()==1){
            newStr = "0" + str;
        }
        return newStr;
    }
}
