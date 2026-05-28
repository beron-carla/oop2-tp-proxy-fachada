package proxy.ejercicio3;

public class ImageGalery {

    public static void main(String[] args) {
        var image1 = new ProxyImageFile(new RealImageFile("src/main/resources/image1.jpeg"),
                "src/main/resources/image1.jpeg");
        image1.display();
        image1.display();


    }

}
