package com.leovegasgroup.wallet;

import com.leovegasgroup.wallet.cache.service.AccountService;
import com.leovegasgroup.wallet.service.PlayerService;
import com.leovegasgroup.wallet.service.dto.PlayerDTO;
import org.h2.tools.RunScript;
import org.h2.tools.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

@Component
public class InitialConfigurationService {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private AccountService accountService;

    public void init() {
//        restoreDataBase();
        List<PlayerDTO> players = playerService.findAllWithBalance();
        accountService.initAccountForPlayers(players);
    }

    private void restoreDataBase() {
        try {
            RunScript.main("-url", "jdbc:h2:mem:walletdb", "-user", "sa", "-script", "h2.backup.zip", "-options", "compression", "zip");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void backupDataBase() {
        try {
            String backupFile = "h2.backup.zip";
            String tempOutputFileName = "out.zip";
            Script.main("-url", "jdbc:h2:mem:walletdb", "-user", "sa", "-script", tempOutputFileName, "-options", "compression", "zip");
            File f = new File(tempOutputFileName);
            ZipFile zipFile = new ZipFile(tempOutputFileName);
            final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(backupFile));
            for (Enumeration e = zipFile.entries(); e.hasMoreElements(); ) {
                ZipEntry entryIn = (ZipEntry) e.nextElement();
                zos.putNextEntry(new ZipEntry(entryIn.getName()));

                InputStream is = zipFile.getInputStream(entryIn);
                byte[] firstBytes = "DROP ALL OBJECTS ".getBytes();
                zos.write(firstBytes);
                byte[] buf = new byte[1024];
                int len;
                while ((len = (is.read(buf))) > 0) {
                    zos.write(buf, 0, (len < buf.length) ? len : buf.length);
                }
                zos.closeEntry();
            }
            zos.close();
            f.delete();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
