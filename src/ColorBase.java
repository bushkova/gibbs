import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anbu0414 on 5/5/2015.
 */
public class ColorBase {
    //private ArrayList<MyColor> colors;
    Map<String, MyColor> map;

    public ColorBase() {
        map = new HashMap<String, MyColor>();
        fill();
    }

    public void add(MyColor c) {
        map.put(c.getHexa(), c);
    }

    public MyColor getColor(String hex) {
        return map.get(hex);
    }

    public String getColorName(String hex) {
        return map.get(hex.toLowerCase()).getName();
    }

    public void fill() {
        add(new MyColor(128,0,0,"maroon"));
        add(new MyColor(139,0,0,"dark red"));
        add(new MyColor(165,42,42, "brown"));
        add(new MyColor(178,34,34, "firebrick"));
        add(new MyColor(220,20,60, "crimson"));
        add(new MyColor(255,0,0, "red")	);
        add(new MyColor(255,99,71, "tomato"));
        add(new MyColor(255,127,80, "coral"));
        add(new MyColor(205,92,92, "indian red"));
        add(new MyColor(240,128,128, "light coral"));
        add(new MyColor(233,150,122, "dark salmon"));
        add(new MyColor(250,128,114, "salmon"));
        add(new MyColor(255,160,122, "light salmon"));
        add(new MyColor(255,69,0, "orange red"));


    }
}
