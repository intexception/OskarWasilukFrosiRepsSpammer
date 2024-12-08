package oskar.wasiluk.repository;

import oskar.wasiluk.spammer.IOskarWasilukFrosiRepsSpammer;
import oskar.wasiluk.spammer.impl.OskarWasilukFrosiRepsSpammerImplementation;

import java.util.ArrayList;
import java.util.List;

public class OskarWasilukFrosiRepsSpammerRepository {
    private final List<IOskarWasilukFrosiRepsSpammer> spammers = new ArrayList<>();

    public OskarWasilukFrosiRepsSpammerRepository() {
        spammers.add(new OskarWasilukFrosiRepsSpammerImplementation());
    }

    public List<IOskarWasilukFrosiRepsSpammer> getSpammers() {
        return this.spammers;
    }
}
