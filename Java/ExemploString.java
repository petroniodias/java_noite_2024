public class ExemploString {
    public static void main(String[] args){
        
        String texto = " abcde FGHI ABC abc DEFG   ";

        String t01 = texto.toLowerCase();
        String t02 = texto.toUpperCase();
        String t03 = texto.trim();
        String t04 = texto.substring(2);
        String t05 = texto.substring(5,10);
        String t06 = texto.replace("A","x");
        int t07 = texto.indexOf("AB");
        int t08 = texto.lastIndexOf("F");
        
        System.out.println("*" + texto + "*");
        System.out.println("*" + t01 + "*");
        System.out.println("*" + t02 + "*");
        System.out.println("*" + t03 + "*");
        System.out.println("*" + t04 + "*");
        System.out.println("*" + t05 + "*");
        System.out.println("*" + t06 + "*");
        System.out.println("*" + t07 + "*");
        System.out.println("*" + t08 + "*");
    }
}
