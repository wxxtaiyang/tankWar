import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        String a = "";
        long strTime = new Date().getTime();
        for (int i = 0; i < 40000; i++) {
            a += i;
        }
        long strNewTime = new Date().getTime();
        System.out.println("String：" + (strNewTime - strTime));
        StringBuilder strB = new StringBuilder("");
        long strBTime = new Date().getTime();
        for (int i = 0; i < 40000; i++) {
            strB.append(i);
        }
        long strBNewTime = new Date().getTime();
        System.out.println("StringBuilder：" + (strBNewTime - strBTime));
        StringBuffer stringBuffer = new StringBuffer("");
        long sTime = new Date().getTime();
        for (int i = 0; i < 40000; i++) {
            stringBuffer.append(i);
        }
        long sNewTime = new Date().getTime();
        System.out.println("stringBuffer：" + (sNewTime - sTime));
    }
}
