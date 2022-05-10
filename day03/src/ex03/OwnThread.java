package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class OwnThread extends Thread{
    private final Urls urls;
    private final int threadNumber;

    public OwnThread(Urls urls, int number) {
        this.threadNumber = number;
        this.urls = urls;
    }

    private void download(String[] arr) {
        String url = arr[1];
        int number = Integer.parseInt(arr[0]);
        byte[] buffer = new byte[4096];
        int pack;

        System.out.printf("Thread-%d start download file number %d%n", threadNumber, number);
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream out = new FileOutputStream(url.substring(url.lastIndexOf("/") + 1));
            while ((pack = in.read(buffer, 0, 4096)) != -1) {
                out.write(buffer, 0, pack);
            }
            System.out.printf("Thread-%d finish download file number %d%n", threadNumber, number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String[] arr  = urls.get().split(" ");
        while (arr.length == 2 && arr[0].matches("[0-9]+")) {

            download(arr);
            arr  = urls.get().split(" ");
        }
    }
}
