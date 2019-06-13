package Util;

import java.awt.*;

public class CenteredDisplay {

    public static Rectangle getDisplayArea(Dimension imageSize, Dimension panelSize) {

        if (getWidthHeightRatio(imageSize) <= getWidthHeightRatio(panelSize)) {
            int imageNewHeight = panelSize.height;
            int imageNewWidth = imageSize.width * panelSize.height / imageSize.height;
            return new Rectangle((panelSize.width - imageNewWidth) / 2, 0, imageNewWidth, imageNewHeight);
        }else  {
            int imageNewHeight = imageSize.height * panelSize.width / imageSize.width;
            int imageNewWidth = panelSize.width;
            return new Rectangle(0, (panelSize.height - imageNewHeight) / 2, imageNewWidth, imageNewHeight);
        }
    }

    private static float getWidthHeightRatio(Dimension imageSize) {
        return (float) imageSize.width / imageSize.height;
    }
}
