package NI_GUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadCSV {
	public void DownloadPages(String urlStr, String outPath)
    {
        int chByte = 0;
        URL url = null;
        HttpURLConnection httpConn = null;
        InputStream in = null;
        FileOutputStream out = null;
      
        try
        {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET"); 
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)"); 
           
            // logger.info(httpConn.getResponseMessage());
            in = httpConn.getInputStream();
            out = new FileOutputStream(new File(outPath));

            chByte = in.read();
            while (chByte != -1)
            {
                out.write(chByte);
                chByte = in.read();
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                out.close();
                in.close();
                httpConn.disconnect();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
