import java.util.Arrays;

public class Test {


    public static void main(String[] args) {
        String name = "专项：幼儿园：体验费";

        String[] dataList = name.split("[:：]");

        System.out.println(Arrays.toString(dataList));

    }
}
