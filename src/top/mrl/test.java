package top.mrl;

public class test {
    public static void main(String[] args) {
        String[] strings = {"ABCD", "BCDEFG", "CDEFGHI"};
        System.out.println("The String:");
        for (String str:strings) {
            System.out.println(str);
        }
        String result = proc(strings);
        System.out.println("The A String: ");
        System.out.println(result);
    }
    public static String proc(String[] arr) {
        StringBuilder result = new StringBuilder();
        for (String item:arr) {
            result.append(item);
        }
        return result.toString();
    }
}
