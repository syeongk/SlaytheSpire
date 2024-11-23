package status;

import java.util.ArrayList;

public class Relic {

    private String name;
    private String relicContent;

    public Relic(String name, String relicContent) {
        this.name = name;
        this.relicContent = relicContent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelicContent() {
        return relicContent;
    }

    public void setRelicContent(String relicContent) {
        this.relicContent = relicContent;
    }


}
