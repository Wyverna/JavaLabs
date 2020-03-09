package by.belstu.fit.isit.patsei.base.internet;

/**
 * Created by Наталья Пацей on 06.04.2016.
 */

    import java.awt.Graphics;
    import java.net.MalformedURLException;
    import java.net.URL;
    import javax.swing.JApplet;
    public class ShowDocument extends JApplet {
        private URL bstu = null;
        public String getBaseURL() {
            return "http://www.belstu.by";
        }
        public void paint(Graphics g) {
            int timer = 0;
            g.drawString("Загрузка страницы", 10, 10);

            try {
                bstu  =  new URL(getBaseURL());
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
            this.getAppletContext().showDocument(bstu, "_blank");
        }
    }
