/**
 * Created by lpf on 2021/6/18.
 */
public class Test {
    public static void main(String[] args) {
        int num = 7;
        System.out.println(num &(-num));


        int a = 8;

        for (int i = 0; i < 32; i++) {

            // >>> 无符号右移
            int t = (a & 0x80000000 >>> i) >>> (31 - i);

            System.out.print(t);
        }

        System.out.println();

        int b = -a;

        for (int i = 0; i < 32; i++) {

            // >>> 无符号右移
            int t = (b & 0x80000000 >>> i) >>> (31 - i);

            System.out.print(t);
        }
    }
}
