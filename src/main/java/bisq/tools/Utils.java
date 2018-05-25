/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.tools;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class Utils {
    public static void renameFile(File oldFile, File newFile) throws IOException {
        if (isWindows()) {
            // Work around an issue on Windows whereby you can't rename over existing files.
            final File canonical = newFile.getCanonicalFile();
            if (canonical.exists() && !canonical.delete()) {
                throw new IOException("Failed to delete canonical file for replacement with save");
            }
            if (!oldFile.renameTo(canonical)) {
                throw new IOException("Failed to rename " + oldFile + " to " + canonical);
            }
        } else if (!oldFile.renameTo(newFile)) {
            throw new IOException("Failed to rename " + oldFile + " to " + newFile);
        }
    }

    public static boolean isWindows() {
        return getOSName().contains("win");
    }

    public static String getOSName() {
        return System.getProperty("os.name").toLowerCase(Locale.US);
    }
}