package oskar.wasiluk;

import oskar.wasiluk.repository.OskarWasilukFrosiRepsSpammerRepository;
import oskar.wasiluk.spammer.IOskarWasilukFrosiRepsSpammer;

public class OskarWasilukFrosiRepsSpammerMainClass {

    public static void main(String[] args) {
       OskarWasilukFrosiRepsSpammerRepository spammerRepository = new OskarWasilukFrosiRepsSpammerRepository();

       spammerRepository.getSpammers().forEach(IOskarWasilukFrosiRepsSpammer::run);
    }
}
