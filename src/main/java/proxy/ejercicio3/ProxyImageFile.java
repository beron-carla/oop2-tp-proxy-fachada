package proxy.ejercicio3;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ProxyImageFile implements ImageFileSubject {
    private static Map<String, BufferedImage> cache = new HashMap<>();
    RealImageFile realSubject;
    private String path;

    public ProxyImageFile(RealImageFile realSubject, String path) {
        this.realSubject = realSubject;
        this.path = path;
    }

    @Override
    public void display() {

        if (cache.containsKey(path)) {
            this.realSubject.image = cache.get(path);
            this.realSubject.display();
        } else {
            cache.put(path, realSubject.load(path));
            this.realSubject.load(path);
            this.realSubject.display();
            cache.put(path, realSubject.load(path));
        }

    }
}
