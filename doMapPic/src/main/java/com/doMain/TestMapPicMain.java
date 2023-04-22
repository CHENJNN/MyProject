package com.doMain;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.StaticMapsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.Size;
import com.google.maps.model.TravelMode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestMapPicMain {
    public static void main(String[] args) {
        try {
            String apiKey = "";
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
            DirectionsResult result = DirectionsApi.newRequest(context)
                    .mode(TravelMode.DRIVING)
                    .origin("新北市五股區凌雲路一段22號")
                    .destination("蘆洲捷運站")
                    .await();
            String distance = result.routes[0].legs[0].distance.humanReadable;
            StaticMapsRequest request = StaticMapsApi.newRequest(context, new Size(600, 600))
                    .path(result.routes[0].overviewPolyline)
                    .maptype(StaticMapsRequest.StaticMapType.roadmap)
                    .format(StaticMapsRequest.ImageFormat.jpg);
            ByteArrayInputStream bis = new ByteArrayInputStream(request.await().imageData);
            BufferedImage image = ImageIO.read(bis);

            // 獲取的 地圖檔再加工 填上距離
            Graphics2D g = image.createGraphics();
            g.setFont(new Font("Calibri Light", Font.BOLD, 50));
            g.setColor(Color.BLUE);
            g.drawString(distance, 450, 50);
            g.dispose();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            byte[] newByteArray = bos.toByteArray();
            try (FileOutputStream fos = new FileOutputStream("./"+"map.png")) {
                fos.write(newByteArray);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } catch (ApiException ex) {
            throw new RuntimeException(ex);
        }

    }




}
