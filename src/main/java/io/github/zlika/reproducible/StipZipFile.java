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

package io.github.zlika.reproducible;

import bisq.tools.Utils;

import java.io.File;

// ZipStripper calss is package private so we use the io.github.zlika.reproducible to get around that.
// io.github.zlika.reproducible is designed as maven plug in.
public class StipZipFile {
    public static void strip(String filePath) throws Throwable {
        File inFile = new File(filePath);
        File outFile = File.createTempFile("Bisq-stripped", null);
        outFile.deleteOnExit();
        new ZipStripper()
                .addFileStripper("META-INF/MANIFEST.MF", new io.github.zlika.reproducible.ManifestStripper())
                .addFileStripper("META-INF/\\S*/pom.properties", new io.github.zlika.reproducible.PomPropertiesStripper())
                .strip(inFile, outFile);
        Utils.renameFile(outFile, inFile);
        outFile.delete();
    }
}