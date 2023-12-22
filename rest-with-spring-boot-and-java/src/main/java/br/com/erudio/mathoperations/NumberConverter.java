package br.com.erudio.mathoperations;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        //BR 10,25 US 10.25
        String number = strNumber.replaceAll(",", ".");

        if(isnumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isnumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }

}
