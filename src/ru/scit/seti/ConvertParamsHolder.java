package ru.scit.seti;

/**
 * Created with IntelliJ IDEA.
 * User: scit
 * Date: 12/11/13
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConvertParamsHolder {
    private boolean withTables;
    private boolean withImages;
    private String url;

    public ConvertParamsHolder() {

    }

    public ConvertParamsHolder(String url, boolean withTables, boolean withImages) {
        this.withTables = withTables;
        this.withImages = withImages;
        this.url = url;
    }

    public boolean withTables() {
        return withTables;
    }

    public void withTables(boolean withTables) {
        this.withTables = withTables;
    }

    public boolean withImages() {
        return withImages;
    }

    public void withImages(boolean withImages) {
        this.withImages = withImages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
