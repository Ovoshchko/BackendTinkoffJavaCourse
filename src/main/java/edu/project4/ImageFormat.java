package edu.project4;

public enum ImageFormat {
    BMP("bmp"),
    JPEG("jpg"),
    PNG("png");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
