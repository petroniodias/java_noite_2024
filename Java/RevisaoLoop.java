public class RevisaoLoop {
    public static void main (String[] args){
        int i,j;
        
        System.out.println("Loop com For");
        for(i=2;i<10;i+=2){
            for(j=1;j<10;j+=2){
                System.out.println(i + " x " + j + " = " + i*j);
                if (j==5){
                    break;
                }
            }
            System.out.println(); // Insere uma linha em branco
        }
        
        System.out.println("Loop com While");
        i=2;
        while(i<10){
            j=1;
            while(j<10){
                System.out.println(i + " x " + j + " = " + i*j);
                if (j==5){
                    break;
                }
                j+=2;
            }
            System.out.println(); // Insere uma linha em branco
            i+=2;
        }
    }
}

