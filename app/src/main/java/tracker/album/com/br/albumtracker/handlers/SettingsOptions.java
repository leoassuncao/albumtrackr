package tracker.album.com.br.albumtracker.handlers;

/**
 * Created by Leonardo Assunção on 07/04/2016.
 */
public class SettingsOptions {
    private int option;

    public SettingsOptions() {
            }

    public SettingsOptions(int option) {
        this.option = option;
    }

    public int getOptions() {
        return option;
    }

    public void setOptions(int option) {
        this.option = option;
    }
}
