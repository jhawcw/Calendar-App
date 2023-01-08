import java.awt.*;

public class Settings {
    private Font font;
    private Color backgroundColor;
    private Color foregroundColor;

    public Settings(Font font) {
        this.font = font;
    }

    public Settings(Font font, Color backgroundColor, Color foregroundColor) {
        this.font = font;
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    public Font getFont() {
        return font;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public Settings(Color foregroundColor, Font font ) {
        this.font = font;
        this.foregroundColor = foregroundColor;
    }
}
